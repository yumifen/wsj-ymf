package com.xinwei.config.shiro.auto;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 管理 连续最大登录失败次数以及时间间隔，当达到最大登录失败次数后，系统提示用户N分钟内无法继续重试。
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);

    private Cache<String, AtomicInteger> passwordRetryCache;
    private int retryMax;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws ExcessiveAttemptsException {
        String username = (String)token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        
      
        if(retryCount.incrementAndGet() > retryMax) {
            throw new ExcessiveAttemptsException("您已连续错误达" + retryMax + "次！请N分钟后再试");
        }
        
        /**
         *  使用redis 管理登录次数时， 需要此段代码（注意：只有redis需要，内存和ehcache不需要）。
         */
        if( passwordRetryCache.getClass().getName().contains("RedisCache")){
     	   passwordRetryCache.put(username, retryCount);
        }
        
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            passwordRetryCache.remove(username);
        }else {
            throw new IncorrectCredentialsException("密码错误，已错误" + retryCount.get() + "次，最多错误" + retryMax + "次");
        }
        return true;
    }

    public int getRetryMax() {
        return retryMax;
    }

    public void setRetryMax(int retryMax) {
        this.retryMax = retryMax;
    }
}
