package com.luo.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luo.usercenter.common.BaseResponse;
import com.luo.usercenter.common.ErrorCode;
import com.luo.usercenter.common.ResultUtils;
import com.luo.usercenter.exception.BusinessException;
import com.luo.usercenter.model.domain.User;
import com.luo.usercenter.model.domain.request.*;
import com.luo.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.luo.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.luo.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 *
 * 用户接口
 *
 * @author 13436
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户 注册
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest==null){
           throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode=userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result=userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request  ){
        if(userLoginRequest==null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User user=userService.userLogin(userAccount, userPassword,request);
        return ResultUtils.success(user);
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        if(request==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result=userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        User safeUser=userService.getLoginUser(request);
        return ResultUtils.success(safeUser);
    }

    /**
     * 查询用户
     * @param searchRequest
     * @param request
     * @return
     */
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(UserSearchRequest searchRequest, HttpServletRequest request){
        if(!isAdmin(request)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String username = searchRequest.getUsername();
        String userAccount = searchRequest.getUserAccount();
        String gender = searchRequest.getGender();
        String phone = searchRequest.getPhone();
        String email = searchRequest.getEmail();
        Integer userStatus = searchRequest.getUserStatus();
        String userRole = searchRequest.getUserRole();
        String userCode = searchRequest.getUserCode();


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Date updateTime = searchRequest.getUpdateTime();
        Date createTime = searchRequest.getCreateTime();
        // username
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        // userAccount
        if (StringUtils.isNotBlank(userAccount)) {
            queryWrapper.like("userAccount", userAccount);
        }
        // gender
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.eq("gender", gender);
        }
        // phone
        if (StringUtils.isNotBlank(phone)) {
            queryWrapper.like("phone", phone);
        }
        // email
        if (StringUtils.isNotBlank(email)) {
            queryWrapper.like("email", email);
        }
        // userStatus
        if (userStatus != null) {
            queryWrapper.eq("userStatus", userStatus);
        }

        if (StringUtils.isNotBlank(userRole)) {
            queryWrapper.eq("userRole", userRole);
        }

        if (StringUtils.isNotBlank(userCode)) {
            queryWrapper.eq("userCode", userCode);
        }

        if (updateTime != null) {
            queryWrapper.like("updateTime", updateTime);
        }
        if (createTime != null) {
            queryWrapper.like("createTime", createTime);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> users = userList.stream().map(userService::getSafteyUser).collect(Collectors.toList());
        return ResultUtils.success(users);
    }

    /**
     * 删除用户
     * @param userDeleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUsers(@RequestBody UserDeleteRequest userDeleteRequest, HttpServletRequest request){
       if(!isAdmin(request)){
           throw new BusinessException(ErrorCode.NO_AUTH);
       }
       if (userDeleteRequest == null || userDeleteRequest.getId()<=0){
           throw new BusinessException(ErrorCode.PARAMS_ERROR);
       }
       boolean removeUser =userService.removeById(userDeleteRequest.getId());
       if (!removeUser){
           throw new BusinessException(ErrorCode.SYSTEM_ERROR,"操作数据库出错");
       }
       return ResultUtils.success(true);
    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request){
        //仅管理员可查询
        Object userObj=request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
//        管理员 校验
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }


    /**
     * 新增用户
     * @param userAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest,HttpServletRequest request){
        if (!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (userAddRequest ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userAddRequest,user);
        boolean result = userService.save(user);
        if (!result){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(user.getId());
    }


    /**
     * 用户更新自己的个人信息
     * @param userUpdateMyRequest
     * @param request
     * @return
     */
    @PostMapping("/update/my")
    public BaseResponse<Boolean> updateMyUser(@RequestBody UserUpdateMyRequest userUpdateMyRequest, HttpServletRequest request){

        if (userUpdateMyRequest ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        User user=new User();
        BeanUtils.copyProperties(userUpdateMyRequest,user);
        user.setId(loginUser.getId());
        boolean result = userService.updateById(user);
        if (!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"操作数据库数值返回为false");
        }
        return ResultUtils.success(true);
    }


    /**
     * 管理员更新用户信息
     * @param userUpdateRequest
     * @param request
     * @return
     */
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest,HttpServletRequest request){

        if (!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (userUpdateRequest == null || userUpdateRequest.getId() == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest,user);
        boolean result = userService.updateById(user);
        if (!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"操作数据库出错");
        }
        return ResultUtils.success(true);


    }

    /**
     * 修改密码
     *
     * @param updatePasswordRequest
     * @param request
     * @return
     */
    @PostMapping("/update/password")
    public BaseResponse<Boolean> updateUserPassword(@RequestBody UserUpdatePasswordRequest updatePasswordRequest,
                                                    HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "无权限");
        }
        boolean updateUserPassword = userService.updateUserPassword(updatePasswordRequest, request);
        if (updateUserPassword) {
            return ResultUtils.success(true);
        } else {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
    }









}
