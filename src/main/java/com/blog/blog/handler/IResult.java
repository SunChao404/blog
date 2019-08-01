package com.blog.blog.handler;

import com.blog.blog.i18n.IMessageKey;
import lombok.AllArgsConstructor;


/**
 * @author sunchao
 * @since 2019/7/29 10:53
 */
@AllArgsConstructor
public class IResult<T> implements Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public T getData() {
        return this.data;
    }

    public IResult() {
    }

    public IResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public IResult<T> success(T data, Integer code, String msg) {
        this.setData(data);
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }


    public IResult<T> error(T data, Integer code, String msg) {
        this.setData(data);
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }
    public IResult<T> error(IMessageKey messageKey, Object... params) {
        return error(null, messageKey.getCode(), params);
    }
}
