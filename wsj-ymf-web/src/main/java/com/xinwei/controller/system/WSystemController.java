package com.xinwei.controller.system;

import com.xinwei.common.WSJResult;
import com.xinwei.common.base.controller.BaseController;
import com.xinwei.config.shiro.auto.ShiroProperties;
import com.xinwei.config.shiro.auto.annotation.SessionUser;
import com.xinwei.controller.system.dto.DefaultDTO;
import com.xinwei.entity.SysUser;
import com.xinwei.service.ISysUserService;
import com.xinwei.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/web")
public class WSystemController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

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
