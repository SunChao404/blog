package com.blog.blog.core.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.blog.core.user.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sunchao
 * @since 2019/7/29 14:43
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
