package com.pd.security.shiro.autoconfiguration;

import com.pd.security.shiro.annotation.ShiroSecurity;
import com.pd.security.shiro.base.AbstractShiroFilterCustom;
import com.pd.security.shiro.config.SecurityConfig;
import com.pd.security.shiro.config.UserInfo;
import com.pd.security.shiro.security.PdAuthorizingRealm;
import com.pd.security.shiro.security.PdRedisCacheManager;
import com.pd.security.shiro.security.PdSecurityManager;
import com.pd.security.shiro.security.PdSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author peramdy on 2018/10/23.
 */
@Configuration
public class ShiroAutoConfiguration extends BaseAutoConfiguration {


    /**
     * ShiroFilterFactoryBean 过滤
     *
     * @param securityManager
     * @param securityConfig
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean filter(PdSecurityManager securityManager, SecurityConfig securityConfig) {
        List<AbstractShiroFilterCustom> list = parseShiroSecurity(true);
        AbstractShiroFilterCustom custom = list.get(0);
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        custom.shiroFilterFactoryBean(securityConfig, filter);
        filter.setSecurityManager(securityManager);
        return filter;
    }

    /**
     * securityManager
     *
     * @param pdAuthorizingRealm
     * @return
     */
    @Bean
    public PdSecurityManager securityManager(PdAuthorizingRealm pdAuthorizingRealm,
                                             PdSessionManager pdSessionManager,
                                             PdRedisCacheManager pdRedisCacheManager) {
        PdSecurityManager securityManager = new PdSecurityManager();
        securityManager.setRealm(pdAuthorizingRealm);
        securityManager.setSessionManager(pdSessionManager);
        securityManager.setCacheManager(pdRedisCacheManager);
        return securityManager;
    }

    /**
     * 校验认证，授权
     *
     * @param userInfo
     * @return
     */
    @Bean
    public PdAuthorizingRealm pdAuthorizingRealm(UserInfo userInfo) {
        PdAuthorizingRealm realm = new PdAuthorizingRealm();
        List<AbstractShiroFilterCustom> list = parseShiroSecurity(true);
        AbstractShiroFilterCustom custom = list.get(0);
        UserInfo user = custom.getUserInfo(userInfo);
        realm.setUserInfo(user);
        return realm;
    }

    @Bean
    public PdSessionManager pdSessionManager() {
        return new PdSessionManager();
    }

    @Bean
    public PdRedisCacheManager pdRedisCacheManager() {
        return new PdRedisCacheManager();
    }


    /**
     * parse shiroSecurity annotation
     *
     * @param onlyOne
     * @return
     */
    private List<AbstractShiroFilterCustom> parseShiroSecurity(boolean onlyOne) {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(ShiroSecurity.class);
        if (map.isEmpty()) {
            throw new SecurityException("no annotation @ShiroSecurity");
        }
        if (onlyOne) {
            if (map.size() > 1) {
                throw new SecurityException("@ShiroSecurity must be one");
            }
        }
        List<AbstractShiroFilterCustom> list = map.keySet().stream().map(clzName -> {
            Object clz = map.get(clzName);
            if (!AbstractShiroFilterCustom.class.isAssignableFrom(clz.getClass())) {
                throw new SecurityException(clzName + " must extends AbstractShiroFilterCustom");
            }
            return (AbstractShiroFilterCustom) clz;
        }).collect(Collectors.toList());
        return list;
    }


}
