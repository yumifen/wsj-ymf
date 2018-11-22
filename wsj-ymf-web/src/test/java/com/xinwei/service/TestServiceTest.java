package com.xinwei.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @创建人 yumifen
 * @创建时间 2018/11/22
 * @描述
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestServiceTest {

    @Autowired
    TestService  testService;

    @Test
    public void findAllMenus() {
        testService.findAllMenus(1,2);
    }
}