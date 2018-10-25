package com.pd.security.shiro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author peramdy on 2018/10/23.
 */
@Data
@ConfigurationProperties(prefix = ShiroProperties.PREFIX)
public class ShiroProperties {

    public static final String PREFIX = "spring.shiro";

    private Redis redis = new Redis();

    @Data
    public static class Redis {
        private String url;
        private Integer port;
        private String password;
    }
}
