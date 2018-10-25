package com.pd.security.shiro.security;

import com.pd.security.shiro.config.UserInfo;
import com.pd.security.shiro.utils.Encodes;
import lombok.Data;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author peramdy on 2018/10/23.
 */
public class PdAuthorizingRealm extends AuthorizingRealm {

    private UserInfo userInfo;

    @Override
    public String getName() {
        return "pdShiro";
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * 授权（权限管理）
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证 （登录）
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        PdUsernamePasswordToken token = (PdUsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        if (userName != userInfo.getPassword()) {
            throw new AccountException("用户名不正确");
        }
        byte[] salt = Encodes.decodeHex(userInfo.getPassword().substring(0, 16));
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                new Principal(userInfo, token.isMobileLogin()),
                "",
                ByteSource.Util.bytes(salt),
                getName());

        return simpleAuthenticationInfo;
    }


    @Data
    public static class Principal {

        private String id;
        private String loginName;
        private String name;
        private boolean mobileLogin;

        public Principal(UserInfo userInfo, boolean mobileLogin) {
            this.id = id;
            this.loginName = loginName;
            this.name = name;
            this.mobileLogin = mobileLogin;
        }
    }

}
