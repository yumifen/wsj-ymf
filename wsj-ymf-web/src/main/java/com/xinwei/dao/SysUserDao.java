package com.xinwei.dao;



import com.xinwei.common.base.CrudDao;
import com.xinwei.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chenjianan on 2016/12/14-20:11.
 * <p>
 * Describe: 系统用户dao
 */
@Mapper
public interface SysUserDao extends CrudDao<SysUser> {


    /**
     * 根据用户登录名查找用户
     * @param loginName
     * @param delFlag
     * @return
     */
    SysUser selectByLoginName(@Param("loginName") String loginName, @Param("delFlag") String delFlag);

    /**
     * 根据部门id获取该部门的所有用户
     * @param sysOfficeId
     * @param delFlag
     * @return
     */

    List<SysUser> selectSysUserBySysOfficeId(@Param("sysOfficeId") String sysOfficeId, @Param("delFlag")
            String
            delFlag);


    /**
     * 根据  LoginName 查询用户
     * @param loginName
     * @return
     */
    SysUser selectByLoginName(@Param("loginName") String loginName);


    /**
     * 查询某个用户的所有信息
     * @param id
     * @return
     */
    SysUser selectUserAllInfoById(@Param("id") Long id);



}
