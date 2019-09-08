package com.lvy.springboot.controller;

import com.lvy.springboot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/rest/login")
public class LoginController {
    @GetMapping("/index")
    public String index(String userName) {
        System.out.println("userName=" + userName);
        return "index";
    }
}
