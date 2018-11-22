package com.xinwei.controller.system;


import com.xinwei.common.ResponseJson;
import com.xinwei.config.shiro.auto.ShiroProperties;
import com.xinwei.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import com.xinwei.service.impl.SysUserService;


/**
 * Shiro测试Controller
 *
 * @create 2016年1月13日
 */
@Controller
public class SignController {

	private static final Logger logger = LoggerFactory.getLogger(SignController.class);

	@Autowired(required = true)
    private ShiroProperties shiroProperties;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        Subject s = SecurityUtils.getSubject();
        return s.isRemembered() || s.isAuthenticated() ? "redirect:web/index" : "sign/login";
    }

    @RequestMapping(value = "/")
    public String login() {
        Subject s = SecurityUtils.getSubject();
        return s.isRemembered() || s.isAuthenticated() ? "redirect:web/index" : "sign/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Model model,@Valid SysUser user,RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response,boolean rememberMe) {
        // if(bindingResult.hasErrors()){
        // return "login";
        // }
        model.addAttribute("base", request.getContextPath());
        ResponseJson responseJson = new ResponseJson();
        String username = user.getLoginName();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword());
        token.setRememberMe(rememberMe);
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();


        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);

            logger.info("对用户[" + username + "]进行登录验证..验证通过");

            //begin 添加记录日志功能
            //String logOperatorName = (String) currentUser.getPrincipal();
            //end   添加记录日志功能

        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "账户不存在");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (DisabledAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户未审核");
            redirectAttributes.addFlashAttribute("message", "账户未审核");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        // 验证是否登录成功
        if (currentUser.isAuthenticated()) {
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");

            SavedRequest re = WebUtils.getAndClearSavedRequest(request);

            logger.info("redirect:" + ((re == null || re.getRequestUrl() == null) ? shiroProperties.getSuccessUrl()
                    : re.getRequestUrl()));
            String url = ((re == null || re.getRequestUrl() == null) ? shiroProperties.getSuccessUrl(): re.getRequestUrl());

            SysUser shiroUser = sysUserService.getByLoginName(username);

            responseJson.setMsg("success");
            responseJson.setUrl(url);
        } else {
            token.clear();
            Map<String, String> map = (Map) redirectAttributes.getFlashAttributes();
            String reason = map.get("message");

            responseJson.setMsg("false");
            responseJson.setReturnStr(reason);
            //return JsonUtils.object2Json(responseJson);// "{\"success\":true,\"msg\":false,\"returnStr\":\""+reason+"\"}"
            // ;
        }
        model.addAttribute("info",responseJson);
        return "sign/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        // 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        // redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }
}