package com.luo.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户修改密码请求体
 *
 * @author jj
 */
@Data
public class UserUpdatePasswordRequest implements Serializable {

    /**
     * 原密码
     */
    private String userPassword;

    /**
     * 新密码
     */
    private String newPassword;


    private static final long serialVersionUID = 1L;
}