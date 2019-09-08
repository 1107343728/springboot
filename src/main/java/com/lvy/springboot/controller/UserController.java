package com.lvy.springboot.controller;

import com.lvy.springboot.config.PropertiesConfig;
import com.lvy.springboot.dto.ResDto;
import com.lvy.springboot.entity.User;
import com.lvy.springboot.service.UserService;
import com.lvy.springboot.validate.group.Create;
import com.lvy.springboot.validate.group.Update;
import com.lvy.springboot.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PropertiesConfig propertiesConfig;

    @GetMapping("/save")
    public String save() {
        User user = new User();
        user.setName("小明");
        user.setAge(12);
        user.setBirth(new Date());
        userService.insert(user);
        return "success";
    }

    @PostMapping("/add")
    public String add(@RequestBody @Validated(Create.class) UserVo userVo) {
        userService.add(userVo);
        return "success";
    }

    @PostMapping("/update")
    public String update(@RequestBody @Validated(Update.class) UserVo userVo) {
        userService.add(userVo);
        return "success";
    }

    @GetMapping("/get")
    public ResDto getUser(@RequestParam("name") @NotBlank(message = "用户名不能为空") String name) {
        List<User> user = userService.findByNameLike(name);
        if (user == null) {
            return new ResDto<User>().nonAbsent("用户不存在");
        }
        return new ResDto<User>().success(user.get(0));
    }

    @GetMapping("/getConfig")
    public String getConfig() {
        return propertiesConfig.getUserNameList().toString();
    }

}
