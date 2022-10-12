package com.gitee.xiaosongstudy.request.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件控制器
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 13:33
 */

@RestController
@RequestMapping("/file")
@CrossOrigin("*")
public class FileController {


    @PostMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("测试文件.jpg","utf-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(Files.newInputStream(Paths.get("E:\\认证图片\\1.jpg")),outputStream);
    }

    @PostMapping("/downloadFileAndReturnStr")
    public String downloadFileAndReturnStr(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("测试文件.jpg","utf-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(Files.newInputStream(Paths.get("E:\\认证图片\\1.jpg")),outputStream);
        return "success";
    }
}
