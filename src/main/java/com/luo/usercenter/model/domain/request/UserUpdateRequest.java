package com.luo.usercenter.model.domain.request;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员更新用户信息的请求体
 *
 * @author jj
 */
@Data
public class UserUpdateRequest implements Serializable {

    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;


    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 性别 男 女
     */
    private String gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态  0-正常 1-注销
     */
    private Integer userStatus;


    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除  逻辑删除
     */
    @TableLogic
    private Integer isDelete;


    /**
     * user-普通用户 admin-管理员
     */
    private String userRole;



    private static final long serialVersionUID = 1L;
}