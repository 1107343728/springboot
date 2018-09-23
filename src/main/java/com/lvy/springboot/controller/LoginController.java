package com.lvy.springboot.controller;

import com.lvy.springboot.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/login")
public class LoginController {
    @GetMapping("/inex")
    public String index() {
        System.out.println("test...........");
        return new User().toString();
    }
}
