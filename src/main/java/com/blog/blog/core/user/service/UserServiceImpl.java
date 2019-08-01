package com.blog.blog.core.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.blog.constants.PublicConstants;
import com.blog.blog.core.user.mapper.UserMapper;
import com.blog.blog.core.user.user.User;
import com.blog.blog.exception.BlogException;
import com.blog.blog.handler.IResult;
import com.blog.blog.utils.PublicUtils;
import com.blog.blog.utils.UUIDUtils;

import org.springframework.stereotype.Service;

/**
 * @author sunchao
 * @since 2019/7/29 14:45
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    /**
     * 新增用户
     * @param user user实体
     * @return IResult
     */
    @Override
    public IResult<Boolean> addUser(User user) {
        PublicUtils.checkEntityExist(user);
        checkColumns(user);
        if(this.getOne(new QueryWrapper<User>().eq(User.Columns.USERNAME, user.getUserName())) !=null){
            throw new BlogException("用户名重复,请重新输入");
        }
        User user1 = new User();
        String userId = UUIDUtils.getUUID();
        user1.setId(userId);
        PublicUtils.checkIdExist(userId);
        user1.setPassWord(user.getPassWord());
        user1.setUserName(user.getUserName());
        user1.setStatus(PublicConstants.IS__NOT_LOGIN);

        return new IResult<Boolean>().success(this.save(user1),0, "注册成功");
    }
    /**
     * 登录
     * @param userName 用户名
     * @param passWord 登陆密码
     * @return IResult
     */
    @Override
    public IResult<User> logIn(String userName, String passWord) {
        if(null == userName || "" .equals(userName)){
            throw new BlogException("请输入用户名");
        }
        if(null == passWord || "" .equals(passWord)){
            throw new BlogException("请输入密码");
        }
        User user = getOne(new QueryWrapper<User>()
        .eq(User.Columns.USERNAME, userName)
        .eq(User.Columns.PASSWORD, passWord));
        if(PublicConstants.IS_LOGIN.equals(user.getStatus())){
            throw new BlogException("账号已登录，请勿重复操作");
        }
        checkColumns(user);
        if(user.getId().isEmpty()){
            throw new BlogException("登陆失败，请检查用户名和密码");
        }
        return new IResult<User>().success(user, 0, "Login Successful");
    }



    /**
     * 字段检测
     * @param user user实体
     */
    private void checkColumns(User user){
        if(null == user.getUserName() || user.getUserName().isEmpty() || user.getUserName() == ""){
            throw new BlogException("昵称不可为空");
        }
        if(null == user.getPassWord() || user.getPassWord().isEmpty() || user.getPassWord() == ""){
            throw new BlogException("密码不可为空");
        }

    }
}
