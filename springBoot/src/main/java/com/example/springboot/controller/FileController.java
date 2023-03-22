package com.example.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.FileVO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;


@RestController
@RequestMapping("/files")
public class FileController {

    /**
     * 单文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        String filePath = System.getProperty("user.dir") + "/springBoot/src/main/resources/static/file/";
        System.out.println(filePath);
        String flag = System.currentTimeMillis() + "";
        String fileName = file.getOriginalFilename();
        try {
            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
            System.out.println(fileName + "--上传成功");
            Thread.sleep(1L);
        } catch (Exception e) {
            System.err.println(fileName + "--文件上传失败");
        }
        return Result.success(flag);
    }

    /**
     * 多文件上传
     * @return
     */
    @PostMapping("/upload/multiple")
    public Result multipleUpload(MultipartFile[] files) {
        String filePath = System.getProperty("user.dir") + "/springBoot/src/main/resources/static/file/";
        List<FileVO> fileVOS = Lists.newArrayList();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            String flag = System.currentTimeMillis() + "";
            String fileName = file.getOriginalFilename();
            FileVO fileVO = new FileVO();
            fileVO.setUrl("http://localhost:9090/files/" + flag);
            fileVO.setName(file.getOriginalFilename());
            fileVOS.add(fileVO);
            try {
                FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
                System.out.println(fileName + "--上传成功");
                Thread.sleep(1L);
            } catch (Exception e) {
                System.err.println(fileName + "--文件上传失败");
            }

        }
        return Result.success(fileVOS);
    }

    /**
     * 获取文件
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}")
    public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;
        String basePath = System.getProperty("user.dir") + "/springBoot/src/main/resources/static/file/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String avatar = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(avatar)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + avatar);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }

}
