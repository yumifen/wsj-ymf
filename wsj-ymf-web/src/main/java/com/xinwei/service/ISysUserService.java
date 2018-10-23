package com.xinwei.service;

import com.xinwei.entity.SysUser;

/**
 *
 * @author chen
 * @date 2017/11/6
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public interface ISysUserService {


     SysUser getById(String id);



     int addUser(SysUser sysUser);


     SysUser login(String username, String password);

    /**
     * 登录
     *
     * @param username
     * @return
     */
     SysUser getByLoginName(String username);

    /**
     * 查询某个用户的所有信息
     *
     * @param id
     * @return
     */
     SysUser selectUserAllInfoById(Long id) ;



}
