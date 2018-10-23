package com.xinwei.config.shiro;

import com.xinwei.common.Constants;
import com.xinwei.entity.SysUser;
import com.xinwei.service.ISysUserService;
import com.xinwei.utils.Encodes;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * MyShiroRealm
 *
 * @author   单红宇(365384722)
 * @myblog  http://blog.csdn.net/catoop/
 * @create    2016年1月13日
 */


public class MyShiroRealm extends AuthorizingRealm{

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Constants.HASH_ALGORITHM);
        matcher.setHashIterations(Constants.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
        logger.info("---------密码校验器  -matcher----------------");
    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * MyShiroRealm继承AuthorizingRealm，
     * 在其父类AuthenticatingRealm的getAuthenticationInfo方法中会调用credentialsMatcher的 doCredentialsMatch 来验证用户输入用户名密码是否匹配。
     * @see：本例中该方法的调用时机为需授权资源被访问时
     * @see ：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（系统提供三种cache：内存，ehcache ，redis，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection); 
        //到数据库查是否有此对象
        SysUser user= sysUserService.getByLoginName(loginName);// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        if(user!=null){
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            //用户的角色集合
//            info.setRoles(user.getRolesName());
//            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
//            List<ShiroRole> roleList=user.getRoleList();
//            for (ShiroRole role : roleList) {
//                info.addStringPermissions(role.getPermissionsName());
//                // 或者按下面这样添加
//                //  添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
//                 // info.addRole(role.getRoleName());
//                //添加权限
//                // info.addStringPermission("admin:manage");
//            }

            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;

        //查出是否有此用户
        SysUser user= sysUserService.getByLoginName(token.getUsername());
        
        if(user==null){
        	throw new UnknownAccountException();
        }
        
        if(user!=null&&user.getLoginFlag().equals(Constants.userStatus_2)){
        	
        	throw new  LockedAccountException();
        } 
        if(user!=null&&user.getLoginFlag().equals(Constants.userStatus_0)){
        	
        	throw new  DisabledAccountException();
        } 
        
        
        if(user!=null&&user.getLoginFlag().equals(Constants.userStatus_1)){
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
            return  new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword().substring(16), ByteSource.Util.bytes(salt),getName());
            //return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes("www"),getName());
        }
        return null;
    }
}