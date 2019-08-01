package com.blog.blog.utils;

import com.blog.blog.handler.IResult;
import com.blog.blog.i18n.MessageKeyCode;
import lombok.NoArgsConstructor;

/**
 * @author sunchao
 * @since 2019/7/26 13:22
 */
@NoArgsConstructor
public final class MessageUtils {
    public static void throwMsg(MessageKeyCode messageKeyCode){
        new IResult<>().error(messageKeyCode);
    }
    public static void throwMsg(MessageKeyCode messageKeyCode, Object... args){
        new IResult<>().error(messageKeyCode, args);
    }

}
