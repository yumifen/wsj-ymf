package com.xinwei.controller.test;

import com.xinwei.common.PageResult;
import com.xinwei.common.WSJResult;
import com.xinwei.common.base.controller.BaseController;
import com.xinwei.entity.SysUser;
import com.xinwei.service.TestService;
import com.xinwei.utils.JsonUtils;
import com.xinwei.utils.PasswordUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xinwei.service.impl.SysUserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/web")
public class WTestController extends BaseController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    TestService  testService;

    @GetMapping(value = "/test")
    public String test(Model model, HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        String username = (String) currentUser.getPrincipal();
        SysUser slife = sysUserService.getByLoginName(username);
        model.addAttribute("name",username);
        return "test/test";
    }

    @GetMapping(value = "/testMybatis")
    public void testMybatis(Model model, HttpServletRequest request) {
        SysUser user = sysUserService.getById("1");
        System.out.println(user);
    }

    @RequestMapping(value = "/md5")
    public void md5w() {
        String md5 = PasswordUtils.entryptPassword("111111");
        System.out.print(md5);
    }

}
