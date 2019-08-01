package com.blog.blog.utils;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.blog.blog.exception.BlogException;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Map;


/**
 * @author sunchao
 * @since 2019/7/26 11:24
 */
@NoArgsConstructor
public class PublicUtils {
    /**
     * 对象非空
     */
    public static<T extends Model> void checkEntityExist(Object entity){
        if(null == entity){
            throw new BlogException("不存在");
        }
    }

    /**
     * id非空
     */
    public static<T extends Model> void checkIdExist(String id){
        if(StringUtils.isEmpty(id)){
            throw new BlogException("id 为空");
        }
    }


}
