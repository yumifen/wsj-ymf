package com.xinwei.service.impl;


import com.xinwei.common.base.service.impl.BaseService;
import com.xinwei.dao.SysUserDao;
import com.xinwei.entity.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chen
 * @date 2017/4/21
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统用户service
 */
@Service
public class SysUserService extends BaseService<SysUserDao, SysUser> {

    public SysUser getById(String id) {

        return this.baseMapper.selectById(id);
    }

    public SysUser login(String username, String password) {
        return new SysUser();
    }

    public SysUser getByLoginName(String username) {
        return this.baseMapper.selectByLoginName(username);
    }

    public SysUser selectUserAllInfoById(Long id) {
        return this.baseMapper.selectUserAllInfoById(id);
    }

    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public int addUser(SysUser sysUser) {
        return this.baseMapper.insert(sysUser);
    }
}
