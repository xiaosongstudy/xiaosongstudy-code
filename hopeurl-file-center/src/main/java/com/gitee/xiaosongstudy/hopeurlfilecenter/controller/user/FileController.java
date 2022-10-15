package com.gitee.xiaosongstudy.hopeurlfilecenter.controller.user;

import com.gitee.xiaosongstudy.hopeurlfilecenter.entity.FileChunk;
import com.gitee.xiaosongstudy.hopeurlfilecenter.service.FileService;
import com.gitee.xiaosongstudy.hopeurlfilecenter.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件控制器
 * @author hopeurl
 * @email myhopeurlife@foxmail
 * @since 2022/10/12 13:33
 */

@RestController
@RequestMapping("/api")
@Slf4j
public class FileController {
    @Resource(type = FileService.class)
    private FileService fileService;


    @PostMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("测试文件.jpg","utf-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(Files.newInputStream(Paths.get("E:\\认证图片\\1.jpg")),outputStream);
    }


    @GetMapping("/upload")
    public Result<Map<String, Object>> checkUpload(FileChunk param) {
        log.info("文件MD5:" + param.getIdentifier());
        List<FileChunk> list = fileService.findByIdentifier(param.getIdentifier(), "type");
        Map<String, Object> data = new HashMap<>(1);
        // 判断文件存不存在
        if (list.size() == 0) {
            data.put("uploaded", false);
            return new Result<>(200, "校验成功！", data);
        }
        // 处理单文件
        if (list.get(0).getTotalChunks() == 1) {
            data.put("uploaded", true);
            data.put("url", "");
            return new Result<>(200, "上传成功", data);
        }
        // 处理分片
        int[] uploadedFiles = new int[list.size()];
        int index = 0;
        for (FileChunk fileChunkItem : list) {
            uploadedFiles[index] = fileChunkItem.getChunkNumber();
            index++;
        }
        data.put("uploadedChunks", uploadedFiles);
        return new Result<>(200, "上传成功", data);
    }


    @PostMapping("/upload")
    public Result<Object> chunkUpload(FileChunk param) throws IOException {
        log.info("上传文件：{}", param);
        boolean flag = fileService.uploadFile(param);
        if (!flag) {
            return new Result<>(211, "上传失败");
        }
        return new Result<>(200, "上传成功");
    }





}
