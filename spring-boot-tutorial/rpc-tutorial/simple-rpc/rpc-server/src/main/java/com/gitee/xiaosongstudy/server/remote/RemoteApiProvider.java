package com.gitee.xiaosongstudy.server.remote;

import com.gitee.xiaosongstudy.sdk.vo.Invocation;
import com.gitee.xiaosongstudy.sdk.vo.Result;
import com.gitee.xiaosongstudy.server.handler.RemoteInterfaceHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 远程接口服务提供方
 */
@RequestMapping("/remote")
@RestController
public class RemoteApiProvider {

    @Resource(type = RemoteInterfaceHandler.class)
    private RemoteInterfaceHandler remoteInterfaceHandler;

    @PostMapping("/resolve")
    public Result resolveRequest(@RequestBody Invocation invocation) {
        return remoteInterfaceHandler.resolve(invocation);
    }
}
