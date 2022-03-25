package com.hxkj.admin.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

//@Configuration
public class ShiroConfig {

    /**
     * 拦截过滤器配置
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFactoryFilterBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        // 设置安全管理器
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        /*
         * 设置内置过滤器
         * anon:  无需认证就可以访问
         * authc: 必须认证了才能访问
         * perms: 拥有某个资源权限才能访问
         * role:  拥有某个角色权限才能访问
         */
//        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/user/add", "authc");
//        filterMap.put("/user/update", "authc");
//        bean.setFilterChainDefinitionMap(filterMap);

        // 未登录
//        bean.setLoginUrl("/toLogin");

        // 未授权
       // bean.setUnauthorizedUrl("/auth");

        return bean;
    }

    // 安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(RealmConfig realmConfig) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realmConfig);
        return defaultWebSecurityManager;
    }

    // 自定realm
    @Bean
    public RealmConfig userRealm() {
        return new RealmConfig();
    }

}
