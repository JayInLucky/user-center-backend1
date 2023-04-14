package com.luo.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luo.usercenter.model.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
* @author 13436
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-03-01 09:35:07
*/
public interface UserService extends IService<User> {



    /**
     * 用户注册
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @param planetCode 星球编号
     * @return id 新用户
     */
    long userRegister(String userAccount,String userPassword ,String checkPassword,String planetCode);


    /**
     *  用户登录
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request
     * @return  脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafteyUser(User originUser);


    /**
     * 用户注销
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);

}
