package com.lvy.springboot.controller;

import com.lvy.springboot.entity.User;
import com.lvy.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/rest/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/save")
    public String save() {
        User user = new User();
        user.setName("小明");
        user.setAge(12);
        user.setBirth(new Date());
        userService.insert(user);
        return "success";
    }

}
