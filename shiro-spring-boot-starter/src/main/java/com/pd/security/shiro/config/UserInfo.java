package com.pd.security.shiro.config;

import lombok.Data;

/**
 * @author peramdy on 2018/10/25.
 */
@Data
public class UserInfo {
    private String id;
    private String name;
    private String loginName;
    private String password;
}
