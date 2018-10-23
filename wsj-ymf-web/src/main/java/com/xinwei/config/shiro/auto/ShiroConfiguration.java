package com.xinwei.config.shiro.auto;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * This guy is lazy, nothing left.
 *
 * @author John Zhang
 */
public class ShiroConfiguration {

    @Bean(name = "lifecycleBeanPostProcessor")
    @ConditionalOnMissingBean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @ConditionalOnMissingBean
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    
    @Bean(name = "securityManager")
    @DependsOn(value = {"shiroCacheManager", "rememberMeManager", "mainRealm"})
    public DefaultSecurityManager securityManager(Realm realm, RememberMeManager rememberMeManager,
                                                  CacheManager cacheManager, SessionManager sessionManager) {
        DefaultSecurityManager sm = new DefaultWebSecurityManager();
        sm.setRealm(realm);
        sm.setCacheManager(cacheManager);
        sm.setSessionManager(sessionManager);
        sm.setRememberMeManager(rememberMeManager);
        return sm;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }
    
   
}
