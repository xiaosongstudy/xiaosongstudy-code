package com.gitee.xiaosongstudy.websocket.task;

import com.gitee.xiaosongstudy.base.container.MapContainer;
import com.gitee.xiaosongstudy.base.core.RunnerLine;
import com.gitee.xiaosongstudy.websocket.service.SystemParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 系统参数初始化任务<br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/9/22 21:08
 */
@Component
@Slf4j
public class SystemParamInitRunner implements RunnerLine {

    @Resource(type = SystemParamService.class)
    private SystemParamService systemParamService;

    @Resource(type = MapContainer.class)
    private MapContainer mapContainer;

    @Override
    public int order() {
        return 0;
    }

    @Override
    public void run() {
//        String systemParamTag = "systemParam";
//        log.info("正在初始化系统参数....");
//        List<SystemParam> systemParamList = systemParamService.list(Wrappers.emptyWrapper());
//        if (CollectionUtil.isNotEmpty(systemParamList)) {
//            Map<String, Object> idToSystemParam = new HashMap<>();
//            systemParamList.forEach(systemParam -> {
//                idToSystemParam.put(String.valueOf(systemParam.getId()), systemParam);
//            });
//            mapContainer.setValue(systemParamTag, idToSystemParam);
//        }
    }
}
