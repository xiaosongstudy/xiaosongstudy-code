package com.gitee.xiaosongstudy.hopeurlfilecenter.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.gitee.xiaosongstudy.hopeurlfilecenter.constant.FieldConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 数据持久化前置处理器
 *
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/13 14:34
 */
@Service
@Slf4j
public class BeforePersistenceHandler implements MetaObjectHandler {

    public BeforePersistenceHandler() {
        log.info("数据持久化前置处理器初始化完成~~~~");
    }

    /**
     * 默认编辑者
     */
    public static final String DEFAULT_EDITOR = "auto-generator";

    @Override
    public void insertFill(MetaObject metaObject) {
        setMetaObjectValue(metaObject, FieldConstants.Public.CREATE_TIME, LocalDateTime.now());
        setMetaObjectValue(metaObject, FieldConstants.Public.UPDATE_TIME, LocalDateTime.now());
        setMetaObjectValue(metaObject, FieldConstants.Public.CREATE_BY, DEFAULT_EDITOR);
        setMetaObjectValue(metaObject, FieldConstants.Public.UPDATE_BY, DEFAULT_EDITOR);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setMetaObjectValue(metaObject, FieldConstants.Public.UPDATE_TIME, LocalDateTime.now());
        setMetaObjectValue(metaObject, FieldConstants.Public.UPDATE_BY, DEFAULT_EDITOR);
    }

    private void setMetaObjectValue(MetaObject metaObject, String fieldName, Object value) {
        if (findByFieldName(metaObject, fieldName)) {
            setFieldValByName(fieldName, value, metaObject);
        }
    }

    /**
     * 通过属性名称查询属性是否存在且有没有值
     *
     * @param metaObject 当前操作媒体对象
     * @param fieldName  待校验的属性值
     * @return 查询结果
     */
    private boolean findByFieldName(MetaObject metaObject, String fieldName) {
        return metaObject.hasGetter(fieldName) && null == metaObject.getValue(fieldName);
    }
}
