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
            String destImgPath = "d:\\test.jpg";
            String pressText = "您好，2019";
            String fontName = "黑体";
            int fontStype = Font.BOLD + Font.ITALIC;
            int fontSize = 20;
            ImageUtil.Location location = ImageUtil.Location.RIGHT_TOP;
            Color color = Color.WHITE;
            float aph = 1f;
            ImageUtil.pressText(files[0].getInputStream(), destImgPath, pressText, fontName, fontStype, fontSize,location,color,aph);
            System.out.println(files[0].getBytes());
            byte[] bytes = Files.toByteArray(new File(destImgPath));
            System.out.println(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
