package com.pd.security.shiro.config;

import javax.servlet.Filter;
import lombok.Data;

import java.util.Map;

/**
 * @author peramdy on 2018/10/23.
 */
@Data
public class SecurityConfig {
    private String loginUrl;
    private String successUrl;
    private String unauthorizedUrl;
    private String FilterChainDefinitions;
    private Map<String, String> filterChainDefinitionMap;
    private Map<String, Filter> Filters;

}
