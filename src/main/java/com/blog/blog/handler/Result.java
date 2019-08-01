package com.blog.blog.handler;

import java.io.Serializable;

/**
 * @author sunchao
 * @since 2019/7/29 10:52
 */
public interface Result<T> extends Serializable {
    Integer getCode();
    String getMsg();
    T getData();

    default String string(){
        return "{code:" +getCode()+",msg:"+getMsg()+"}";
    }
}
