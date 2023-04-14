package com.luo.usercenter.service;

import com.luo.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户服务测试
 */
@SpringBootTest
class UserServiceTest {
    //
    // @Resource
    // private UserService userService;
    //
    // @Test
    // void testAddUser(){
    //     User user = new User();
    //     user.setUsername("dogj");
    //     user.setUserAccount("123");
    //     user.setAvatarUrl("https://www.baidu.com/");
    //     user.setGender(0);
    //     user.setUserPassword("xxx");
    //     user.setPhone("123");
    //     user.setEmail("456");
    //     boolean result = userService.save(user);
    //     System.out.println(user.getId());
    //     assertTrue(result);
    // }
    //
    //
    //
    // @Test
    // void userRegister() {
    //     String userAccount = "yupi";
    //     String userPassword = "";
    //     String checkPassword = "123456";
    //     String planetCode = "1";
    //     long result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
    //     Assertions.assertEquals(-1, result);
    //     userAccount = "yu";
    //     result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
    //     Assertions.assertEquals(-1, result);
    //     userAccount = "yupi";
    //     userPassword = "123456";
    //     result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
    //     Assertions.assertEquals(-1, result);
    //     userAccount = "yu pi";
    //     userPassword = "12345678";
    //     result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
    //     Assertions.assertEquals(-1, result);
    //     checkPassword = "123456789";
    //     result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
    //     Assertions.assertEquals(-1, result);
    //     userAccount = "dogYupi";
    //     checkPassword = "12345678";
    //     result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
    //     Assertions.assertEquals(-1, result);
    //     userAccount = "yupi";
    //     result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
    //     Assertions.assertEquals(-1, result);
    // }
}