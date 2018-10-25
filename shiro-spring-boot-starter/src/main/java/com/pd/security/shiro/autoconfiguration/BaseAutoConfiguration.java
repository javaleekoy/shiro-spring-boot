package com.pd.security.shiro.autoconfiguration;

import com.pd.security.shiro.annotation.EnableShiroConfiguration;
import com.pd.security.shiro.base.AbstractShiroFilterCustom;
import com.pd.security.shiro.config.SecurityConfig;
import com.pd.security.shiro.config.ShiroProperties;
import com.pd.security.shiro.config.UserInfo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author peramdy on 2018/10/23.
 */
@Configuration
@ConditionalOnBean(annotation = EnableShiroConfiguration.class)
@AutoConfigureAfter({AbstractShiroFilterCustom.class})
@EnableConfigurationProperties(ShiroProperties.class)
public class BaseAutoConfiguration implements ApplicationContextAware {

    protected ConfigurableApplicationContext applicationContext;

    public ShiroProperties shiroProperties;

    @Autowired
    public void setShiroProperties(ShiroProperties shiroProperties) {
        this.shiroProperties = shiroProperties;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    @Bean
    public SecurityConfig securityConfig() {
        return new SecurityConfig();
    }

    @Bean
    public UserInfo userInfo() {
        return new UserInfo();
    }

}
