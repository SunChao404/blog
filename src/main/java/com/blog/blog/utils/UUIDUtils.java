package com.blog.blog.utils;

import java.util.UUID;

/**
 * @author sunchao
 * @since 2019/7/29 9:37
 */
public class UUIDUtils {
    private UUIDUtils() {}

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
