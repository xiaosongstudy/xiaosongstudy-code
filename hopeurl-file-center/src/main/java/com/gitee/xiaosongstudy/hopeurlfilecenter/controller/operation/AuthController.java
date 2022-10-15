package com.gitee.xiaosongstudy.hopeurlfilecenter.controller.operation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 全局鉴权控制器 .<br>
 *
 * @author shiping.song
 * @date 2022/10/15 22:10
 */
@Controller
public class AuthController {

    /**
     * TODO  首页
     *
     * @return
     * @author shiping.song
     * @date 2022/10/15 22:31
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * TODO 登录
     *
     * @return
     * @author shiping.song
     * @date 2022/10/15 22:31
     */
    @GetMapping("/login")
    public String login() {
        return "user/auth/login";
    }

    /**
     * TODO 文件预览
     *
     * @return
     * @author shiping.song
     * @date 2022/10/15 22:32
     */
    @GetMapping("/file-view")
    public String fileView() {
        return "user/file/file-view";
    }

    /**
     * TODO 上传文件
     *
     * @return
     * @author shiping.song
     * @date 2022/10/15 22:34
     */
    @GetMapping("/upload-file")
    public String uploadFile() {
        return "user/file/upload-file";
    }
}
