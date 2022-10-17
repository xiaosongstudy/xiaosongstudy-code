package com.gitee.xiaosongstudy.request.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

/**
 * 文件控制器
 *
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 13:33
 */

@RestController
@RequestMapping("/file")
@CrossOrigin("*")
@Slf4j
public class FileController {


    @PostMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("测试文件.jpg", "utf-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(Files.newInputStream(Paths.get("E:\\认证图片\\1.jpg")), outputStream);
    }

    @PostMapping("/downloadFileAndReturnStr")
    public String downloadFileAndReturnStr(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("测试文件.jpg", "utf-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(Files.newInputStream(Paths.get("E:\\认证图片\\1.jpg")), outputStream);
        return "success";
    }

    /**
     * form表单方式上传文件同时携带表单参数
     *
     * @param file     文件名称
     * @param username 用户名称
     * @return
     * @author shiping.song
     * @date 2022/10/17 11:27
     */
    @PostMapping("/formUploadFileItem")
    public String formUploadFileItem(HttpServletRequest request, MultipartFile file, String username) throws IOException {
        log.info(file.getOriginalFilename());
        log.info("username{}", username);
        String servletPath = request.getServletPath();
        log.info("servletPath" + servletPath + "-----");
        String contextPath = request.getServletContext().getContextPath();
        log.info("contextPath=>" + contextPath);
        String path = Objects.requireNonNull(FileController.class.getResource(contextPath)).getPath().substring(1);
        log.info("path===>{}", path);
        String parentDir = path + UUID.randomUUID();
        File parentFile = new File(parentDir);
        if (!parentFile.exists()) {
            parentFile.mkdir();
        }
        path = parentDir + File.separator + file.getOriginalFilename();
        file.transferTo(Files.createFile(Paths.get(path)));
        return username;
    }
}
