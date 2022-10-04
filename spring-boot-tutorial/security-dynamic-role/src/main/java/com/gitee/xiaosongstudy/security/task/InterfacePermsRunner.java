package com.gitee.xiaosongstudy.security.task;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.gitee.xiaosongstudy.base.container.MapContainer;
import com.gitee.xiaosongstudy.base.core.RunnerLine;
import com.gitee.xiaosongstudy.security.constant.Globals;
import com.gitee.xiaosongstudy.security.service.SecInterfaceService;
import com.gitee.xiaosongstudy.security.vo.SecInterfaceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口权限初始化<br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/3 18:41
 */
@Service
@Slf4j
public class InterfacePermsRunner implements RunnerLine {

    public InterfacePermsRunner() {
        log.info("接口权限信息初始化...");
    }

    @Resource(type = MapContainer.class)
    private MapContainer mapContainer;

    @Resource(type = SecInterfaceService.class)
    private SecInterfaceService secInterfaceService;

    @Override
    public int order() {
        return 0;
    }

    @Override
    public void run() {
        SecInterfaceVo condition = new SecInterfaceVo();
        condition.setMenuUseFlag(Globals.Flag.TRUE);
        condition.setUseFlag(Globals.Flag.TRUE);
        List<SecInterfaceVo> secInterfacePermVoList = secInterfaceService.listAllInterfacePermsByCondition(condition);
        if (CollectionUtil.isNotEmpty(secInterfacePermVoList)) {
            Map<String, String> data = new HashMap<>();
            secInterfacePermVoList.forEach(secInterfaceVo -> {
                String key = secInterfaceVo.getModulePath().concat(secInterfaceVo.getPath());
                data.put(key, JSON.toJSONString(secInterfaceVo));
            });
            mapContainer.setValue(Globals.INTERFACE_PERMS, data);
        }
    }
}
