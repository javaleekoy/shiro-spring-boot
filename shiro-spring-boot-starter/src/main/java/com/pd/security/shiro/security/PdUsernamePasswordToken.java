package com.pd.security.shiro.security;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author peramdy on 2018/10/25.
 */
@Data
public class PdUsernamePasswordToken extends UsernamePasswordToken {

    private String captcha;
    private boolean mobileLogin;

    public PdUsernamePasswordToken(String username, String password,
                                   boolean rememberMe, String host,
                                   String captcha, boolean mobileLogin) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
        this.mobileLogin = mobileLogin;
    }
}
