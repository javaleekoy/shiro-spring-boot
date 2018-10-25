package com.pd.security.shiro.base;

import com.pd.security.shiro.config.SecurityConfig;
import com.pd.security.shiro.config.UserInfo;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

/**
 * @author peramdy on 2018/10/23.
 */
public abstract class AbstractShiroFilterCustom {


    public abstract SecurityConfig securityConfig(SecurityConfig securityConfig);

    public abstract UserInfo userInfo(UserInfo userInfo);

    /**
     * @param config
     * @param bean
     * @return
     */
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityConfig config, ShiroFilterFactoryBean bean) {
        SecurityConfig securityConfig = securityConfig(config);
        bean.setUnauthorizedUrl(securityConfig.getUnauthorizedUrl());
        bean.setLoginUrl(securityConfig.getLoginUrl());
        bean.setSuccessUrl(securityConfig.getSuccessUrl());
        bean.setFilters(securityConfig.getFilters());
        bean.setFilterChainDefinitions(securityConfig.getFilterChainDefinitions());
        bean.setFilterChainDefinitionMap(securityConfig.getFilterChainDefinitionMap());
        return bean;
    }

    public UserInfo getUserInfo(UserInfo userInfo) {
        return userInfo(userInfo);
    }

}
