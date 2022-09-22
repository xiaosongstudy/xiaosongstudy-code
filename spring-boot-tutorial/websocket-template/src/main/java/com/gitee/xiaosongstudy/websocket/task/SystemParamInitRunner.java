package com.gitee.xiaosongstudy.websocket.task;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gitee.xiaosongstudy.websocket.container.MapContainer;
import com.gitee.xiaosongstudy.websocket.core.RunnerLine;
import com.gitee.xiaosongstudy.websocket.entity.SystemParam;
import com.gitee.xiaosongstudy.websocket.service.SystemParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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
        String systemParamTag = "systemParam";
        log.info("正在初始化系统参数....");
        List<SystemParam> systemParamList = systemParamService.list(Wrappers.emptyWrapper());
        systemParamList.forEach(systemParam -> {
            mapContainer.setValue(systemParamTag, String.valueOf(systemParam.getId()), systemParam);
        });
    }
}
