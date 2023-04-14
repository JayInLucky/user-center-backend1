package com.luo.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * 用户登录请求体
 *
 *
 * @author 13436
 */
@Data
public class UserLoginRequest implements Serializable {

    public static final long serialVersionUID = 319124716373120793L;

    private String userAccount;
    private String userPassword;


}
