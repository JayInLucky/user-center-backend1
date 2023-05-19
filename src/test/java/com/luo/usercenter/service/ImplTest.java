package com.luo.usercenter.service;

import com.luo.usercenter.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ImplTest {

    @Resource
    private UserMapper userMapper;

    private long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode){
        //1.校验
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){

        }

    }

}
