package com.luo.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 * @author 13436
 */
@Data
public class UserRegisterRequest implements Serializable {


    /**
     * 防止序列化出现冲突
     */
    public static final long serialVersionUID = 319124716373120793L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 校验密码
     */
    private String checkPassword;

    /**
     * 星球编号
     */
    private String planetCode;

}
