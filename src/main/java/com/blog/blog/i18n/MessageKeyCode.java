package com.blog.blog.i18n;

/**
 * @author sunchao
 * @since 2019/7/29 11:17
 */
public enum MessageKeyCode implements IMessageKey{

    ID_IS_NULL("ID_IS_NULL", 210001),
    ENTITY_IS_NULL("ENTITY_IS_NULL", 210002)
    ;
    String key;
    Integer code;

    MessageKeyCode(String key, Integer code) {
        this.key = key;
        this.code = code;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
