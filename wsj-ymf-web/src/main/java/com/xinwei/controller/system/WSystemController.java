package com.xinwei.controller.system;

import com.xinwei.common.base.controller.BaseController;
import com.xinwei.config.shiro.auto.annotation.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xinwei.service.impl.SysUserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/web")
public class WSystemController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping(value = "/index")
    public String index(@SessionUser String username, Model model, HttpServletRequest request) {
        //SysUser slife = sysUserService.getByLoginName(username);
        //model.addAttribute("slife",slife);
        return "system/index";
    }

    @GetMapping(value = "/head")
    public String head(@SessionUser String username, Model model, HttpServletRequest request) {
        //SysUser slife = sysUserService.getByLoginName(username);
        //model.addAttribute("slife",slife);
        return "system/head";
    }
}
