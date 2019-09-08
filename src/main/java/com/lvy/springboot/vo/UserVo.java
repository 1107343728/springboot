package com.lvy.springboot.vo;

import com.lvy.springboot.validate.group.Create;
import com.lvy.springboot.validate.group.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UserVo {
    @NotNull(message = "用户id不能为空", groups = Update.class)
    private Long id;
    @NotBlank(message = "用户名不能为空",groups = {Update.class, Create.class})
    @Length(max = 20, message = "用户名不能超过20个字符")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String name;
    @NotNull(message = "年龄不能为空")
    private Integer age;
    private Date birth;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误", groups = {Create.class, Update.class})
    private String phone;
    @NotBlank(message = "性别不能为空")
    private String sex;
}
