package com.hxkj.admin.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
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
     * 过滤器注册
     */
    @Bean
    public FilterRegistrationBean<Filter> doFilterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/");
        return filterRegistration;
    }

    /**
     * 生命周期对象
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor doLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 添加注解支持
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator doDefaultAdvisorAutoProxyCreator() {
        // // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        DefaultAdvisorAutoProxyCreator dap = new DefaultAdvisorAutoProxyCreator();
        dap.setProxyTargetClass(true);
        return dap;
    }

    /**
     * 自定realm
     */
    @Bean
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager doDefaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.shiroRealm());
        securityManager.setCacheManager(new MemoryConstrainedCacheManager());

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(evaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * 权限源切面
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor doAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
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
    public ShiroFilterFactoryBean doShiroFactoryFilterBean(DefaultWebSecurityManager securityManager) {
        // 设定认证管理
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        // 添加自定过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JwtFilter());
        bean.setFilters(filterMap);

        // 添加授权链接
        Map<String,String> chainMap = new LinkedHashMap<>();

        chainMap.put("/user/add", "authc");
//        chainMap.put("/user/update", "jwt");
        chainMap.put("/user/login", "anon");
        chainMap.put("/**", "jwt");
        bean.setFilterChainDefinitionMap(chainMap);

        // 返回构建配置
        return bean;
    }

}
