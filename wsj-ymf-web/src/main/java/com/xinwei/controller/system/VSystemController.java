package com.xinwei.controller.system;

import com.xinwei.common.WSJResult;
import com.xinwei.config.shiro.auto.annotation.SessionUser;
import com.xinwei.controller.system.dto.DefaultDTO;
import com.xinwei.entity.SysUser;
import com.xinwei.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xinwei.service.impl.SysUserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 yumifen
 * @创建时间 2018/10/16
 * @描述
 **/
@RestController
@RequestMapping("/data/system")
public class VSystemController {

    @Autowired
    private SysUserService sysUserService;
    /**
     *
     *@描述
     *@参数  [username, model, request]
     *@返回值  java.lang.String
     *@创建人
     *@创建时间  2018/10/15
     *@修改人和其它信息
     */
    @PostMapping(value = "/initUMenu")
    public String initUMenu(@SessionUser String username, Model model, HttpServletRequest request) {
        SysUser slife = sysUserService.getByLoginName(username);
        DefaultDTO dto = new DefaultDTO();
        dto.setPhotoUrl(slife.getPhoto());
        dto.setUserName(slife.getName());
        return JsonUtils.object2Json(WSJResult.success(dto));
    }
}
