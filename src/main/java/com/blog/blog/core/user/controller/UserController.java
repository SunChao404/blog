package com.blog.blog.core.user.controller;

import com.blog.blog.core.user.service.UserService;
import com.blog.blog.core.user.user.User;
import com.blog.blog.handler.IResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sunchao
 * @since 2019/7/29 14:41
 */
@Api(tags = {"UserController"})
@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 新增用户
     * @param user user实体
     * @return IResult
     */
    @ApiOperation(value = "新建用户User", notes = "新建用户")
    @PostMapping("/user")
    public IResult<Boolean> add(@ApiParam(value = "新建用户") @RequestBody User user){
        return userService.addUser(user);
    }

    /**
     * 登录
     * @param userName 用户名
     * @param passWord 登陆密码
     * @return IResult
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/user/login")
    public IResult<User>logIn(@ApiParam(value = "用户登陆") @RequestBody String userName,
                                 @RequestBody String passWord){
        return userService.logIn(userName, passWord);
    }

}
