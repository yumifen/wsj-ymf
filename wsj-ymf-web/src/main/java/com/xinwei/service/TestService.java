package com.xinwei.service;

import com.bjjdsy.utility.page.mybatis.Paging;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinwei.common.PageResult;
import com.xinwei.dao.SysMenuMapper;
import com.xinwei.entity.SysMenu;
import com.xinwei.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @创建人 yumifen
 * @创建时间 2018/11/13
 * @描述
 **/
@Service
public class TestService {

       @Autowired
       SysMenuMapper sysMenuMapper;

        /**
         * 遵从@Paging注解规范pageNum为当前页码，pageSize为当前条数
         *
         * @return
         */
       @Paging
       public PageResult findAllMenus(int pageNum,int pageSize){
           List<SysMenu> sysMenus = sysMenuMapper.querySysMenusAll();
           PageResult<SysMenu> pageInfo = new PageResult<SysMenu>(sysMenus);
           System.out.println(JsonUtils.object2Json(pageInfo));
           return pageInfo;
       }
}
