package com.xinwei.controller.test;

import com.xinwei.common.PageResult;
import com.xinwei.service.TestService;
import com.xinwei.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @创建人 yumifen
 * @创建时间 2018/11/23
 * @描述
 **/
@RestController
@RequestMapping("/data")
public class VTestController {
    @Autowired
    TestService testService;

    @RequestMapping(value = "/testGrid",method = RequestMethod.POST)
    public String testGrid(Model model, HttpServletRequest request, HttpServletResponse response, int pageNum, int pageSize){
        PageResult allMenus = testService.findAllMenus(pageNum, pageSize);
        return JsonUtils.object2Json(allMenus);
    }
}

