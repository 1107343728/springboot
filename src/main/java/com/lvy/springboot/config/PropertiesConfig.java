package com.lvy.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description :     配置属性信息（需要用的地方直接注入这个bean就可以取到配置信息）
 * Author：          liweixiong
 * Create Date：     2019/7/30 11:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class PropertiesConfig {
    /**
     * 是否启用
     */
    private boolean isEanable;
    /**
     * 用户列表
     */
    private List userNameList;
}
