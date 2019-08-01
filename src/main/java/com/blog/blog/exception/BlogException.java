package com.blog.blog.exception;

import lombok.Data;

/**
 * @author sunchao
 * @since 2019/7/26 14:17
 */
@Data
public class BlogException extends RuntimeException {
    private int code;
    private String msg;
    private Object data;

    public BlogException() {
        super();
        this.printStackTrace();
    }

    public BlogException(int code) {
        super();
        this.code = code;
        this.msg = "";
        this.data = null;
        this.printStackTrace();
    }

    public BlogException(String msg) {
        super(msg);
        this.code = 500;
        this.msg = msg;
        this.data = null;
        this.printStackTrace();
    }

    public BlogException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = null;
        this.printStackTrace();
    }

    public BlogException(int code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.printStackTrace();
    }

}
