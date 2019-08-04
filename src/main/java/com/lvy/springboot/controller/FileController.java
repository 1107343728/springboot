package com.lvy.springboot.controller;

import com.google.common.io.Files;
import com.lvy.springboot.utils.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;

@Controller
@RequestMapping("/rest/file")
public class FileController {

    @GetMapping("/index")
    public String index() {
        return "upload_file";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile files[]) {
        try {
            if(files != null && files.length > 0) {
                String tmpdir = System.getProperty("java.io.tmpdir");
                for(MultipartFile file : files) {
                    String destImgPath = tmpdir + File.separator + file.getOriginalFilename();
                    ImageUtil.pressText(file.getInputStream(), destImgPath, "测试20190805");
                    System.out.println(files[0].getBytes());
                    byte[] bytes = Files.toByteArray(new File(destImgPath));
                    System.out.println(bytes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
