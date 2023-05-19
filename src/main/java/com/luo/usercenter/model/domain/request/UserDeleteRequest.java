package com.luo.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户创建请求
 *
 * @author jj
 */
@Data
public class UserDeleteRequest implements Serializable {

    private Long id;


    private static final long serialVersionUID = 1L;
}