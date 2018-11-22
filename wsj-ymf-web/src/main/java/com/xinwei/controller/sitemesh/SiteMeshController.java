package com.xinwei.controller.sitemesh;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xinwei.service.impl.SysUserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chen on 2017/7/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:SiteMesh 操作
 */
@Controller
@RequestMapping("/web")
public class SiteMeshController  {

    @Autowired
    private SysUserService sysUserService;
    /**
     * 查询系统用户 侧边栏菜单
     * @param model
     * @param request
     * @return
     */
    @GetMapping(value = "layouts")
    public String getIndex(Model model, HttpServletRequest request) {
//        Subject currentUser = SecurityUtils.getSubject();
//
//        String username = (String) currentUser.getPrincipal();
//        SysUser slife = sysUserService.getByLoginName(username);
        //model.addAttribute("slife", slife);
        //model.addAttribute("menus", sysMenuService.CaseMenu(SlifeSysUser.id()));
        System.out.print(request.getRequestURI());
        return "layouts/default";
    }

    @PostMapping(value = "layouts")
    public String postIndex(Model model, HttpServletRequest request) {
        return getIndex(model,  request);
    }
}
