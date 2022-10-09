package com.gitee.xiaosongstudy.canal.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.gitee.xiaosongstudy.canal.config.CanalCacheConfig;
import com.gitee.xiaosongstudy.canal.core.CanalBean;
import com.gitee.xiaosongstudy.canal.core.CanalSyncHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CanalClient implements InitializingBean {

    @Resource(type = CanalSyncHandler.class)
    private CanalSyncHandler canalSyncHandler;

    @Resource(type = CanalCacheConfig.class)
    private CanalCacheConfig canalCacheConfig;

    public CanalClient() {
        log.info("canal客户端初始化成功.....");
    }

    @Override
    public void afterPropertiesSet() {
        CanalCacheConfig.Canal canal = canalCacheConfig.getCanal();
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(), 11111), "example", "", "");
        try {
            // 打开连接
            connector.connect();
            // 订阅数据库表,全部表
            connector.subscribe(canal.getSubscribe());
            // 回滚到未进行ack的地方，下次fetch的时候，可以从最后一个没有ack的地方开始拿
            connector.rollback();
            while (true) {
                // 获取指定数量的数据
                Message message = connector.getWithoutAck(Integer.parseInt(canal.getBatchSize()));
                // 获取批量ID
                long batchId = message.getId();
                // 获取批量的数量
                int size = message.getEntries().size();
                // 如果没有数据
                if (batchId == -1 || size == 0) {
                    try {
                        // 线程休眠2秒
                        Thread.sleep(Long.parseLong(canal.getInterval()));
                    } catch (InterruptedException e) {
                        log.error(CanalClient.class.getSimpleName(), e);
                        break;
                    }
                } else {
                    // 如果有数据,处理数据
                    resolveAndAsync(message.getEntries());
                }
                // 进行 batch id 的确认。确认之后，小于等于此 batchId 的 Message 都会被确认。
                connector.ack(batchId);
            }
        } catch (Exception e) {
            log.error(CanalClient.class.getSimpleName(), e);
        } finally {
            connector.disconnect();
        }
    }

    /**
     * 解析canal数据并实现数据同步
     *
     * @param entrys
     */
    private void resolveAndAsync(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                // 开启/关闭事务的实体类型，跳过
                continue;
            }
            // RowChange对象，包含了一行数据变化的所有特征
            // 比如isDdl 是否是ddl变更操作 sql 具体的ddl sql beforeColumns afterColumns 变更前后的数据字段等等
            CanalEntry.RowChange rowChage;
            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                log.error("ERROR ## parser of eromanga-event has an error , data:" + entry, e);
                return;
            }
            CanalBean canalBean = new CanalBean();
            // 获取操作类型：insert/update/delete类型
            CanalEntry.EventType eventType = rowChage.getEventType();
            // 打印Header信息
            log.info(" binlog[{}:{}] , name[{},{}}] , eventType : {}}",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType);
            canalBean.setSchema(entry.getHeader().getSchemaName());
            canalBean.setTableName(entry.getHeader().getTableName());
            // 判断是否是DDL语句
            if (rowChage.getIsDdl()) {
                log.info("sDdl: true,sql:{}", rowChage.getSql());
                canalBean.setDdlSql(rowChage.getSql());
            }
            System.out.println(rowChage.getSql());
            // 获取RowChange对象里的每一行数据，打印出来
            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                // 如果是删除语句
                if (eventType == CanalEntry.EventType.DELETE) {
                    canalBean.setData(integerColumn(rowData.getBeforeColumnsList()));
                    canalSyncHandler.deleteOperation(canalBean);
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    canalBean.setData(integerColumn(rowData.getAfterColumnsList()));
                    canalSyncHandler.insertOperation(canalBean);
                } else {
                    // 变更前的数据
                    System.out.println("------->; before");
                    integerColumn(rowData.getBeforeColumnsList());
                    // 变更后的数据
                    System.out.println("------->; after");
                    canalBean.setData(integerColumn(rowData.getAfterColumnsList()));
                    canalSyncHandler.updateOperation(canalBean);
                }
            }
        }
    }

    /**
     * 组装列值
     *
     * @param columns 待封装的列
     */
    private Map<String, String> integerColumn(List<CanalEntry.Column> columns) {
        final Map<String, String> data = new HashMap<>(columns.size() / 2);
        columns.forEach(column -> {
            data.put(column.getName(), column.getValue());
        });
        return data.isEmpty() ? null : data;
    }
}