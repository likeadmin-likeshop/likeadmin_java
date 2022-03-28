package com.hxkj.admin.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 安全管理器
     *
     * @param realmConfig realmConfig
     * @return DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager doDefaultWebSecurityManager(ShiroRealm realmConfig) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realmConfig);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultWebSecurityManager.setSubjectDAO(subjectDAO);

        return defaultWebSecurityManager;
    }

    /**
     * 拦截过滤器配置
     *   anon:  无需认证就可以访问
     *   authc: 必须认证了才能访问
     *   perms: 拥有某个资源权限才能访问
     *   role:  拥有某个角色权限才能访问
     * @return ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean doShiroFactoryFilterBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        // 设置安全管理器
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        // 设置内置过滤器
        Map<String, String> filterChainDefinitionMap  = new LinkedHashMap<>();
        filterChainDefinitionMap .put("/user/add", "authc");
        filterChainDefinitionMap .put("/user/update", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap );

        // 设置自定过滤器
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", jwtFilter());
        bean.setFilters(filterMap);

        // 返回构建配置
        return bean;
    }

    /**
     * SpringShiroFilter首先注册到spring容器
     *   然后被包装成FilterRegistrationBean
     *   最后通过FilterRegistrationBean注册到servlet容器
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy(){
        FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<>();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    /**
     * 设置加密次数
     *
     * @return HashedCredentialsMatcher
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);// 设置加密次数
        return hashedCredentialsMatcher;
    }


    // 自定realm
    @Bean
    public ShiroRealm userRealm() {
        return new ShiroRealm();
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

}
