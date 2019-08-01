package com.blog.blog.core.user.service;

import com.blog.blog.core.user.user.User;
import com.blog.blog.handler.IResult;

/**
 * @author sunchao
 * @since 2019/7/29 14:44
 */
public interface UserService {
    /**
     * 新增用户
     * @param user user实体
     * @return IResult
     */
    IResult<Boolean> addUser(User user);

    /**
     * 登录
     * @param userName 用户名
     * @param passWord 登陆密码
     * @return IResult
     */
    IResult<User>logIn(String userName, String passWord);

}
