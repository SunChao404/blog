package com.blog.blog.core.user.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunchao
 * @since 2019/7/29 14:27
 */
@TableName("b_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "表主键", example = "UUID", required = true)
    private String id;
    @ApiModelProperty(value = "用户名", example = "张三", required = true)
    private String userName;
    @ApiModelProperty(value = "用户密码", example = "1234", required = true)
    private String passWord;
    @ApiModelProperty(value = "用户密码", example = "1234", required = true)
    private String status;

    @NoArgsConstructor
    public static final class Columns {
        /**
         * 用户id
         */
        public static final String ID = "id";
        /**
         * 用户名
         */
        public static final String USERNAME = "user_name";
        /**
         * 用户密码
         */
        public static final String PASSWORD = "pass_word";
    }

}
