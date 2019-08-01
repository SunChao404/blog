package com.blog.blog.core.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.blog.core.article.article.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sunchao
 * @since 2019/7/26 16:08
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
