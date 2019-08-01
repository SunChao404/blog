package com.blog.blog.core.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.blog.core.article.article.Article;
import com.blog.blog.handler.IResult;

import java.util.List;

/**
 * @author sunchao
 * @since 2019/7/26 11:04
 */
public interface ArticleService {
    /**
     * 条件分页查询
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return page
     */
    IResult<IPage<Article>> getByPage(Integer pageNum, Integer pageSize);

    /**
     * 文章列表查询
     * @return List
     */
    IResult<List<Article>> getList();

    /**
     * id查询单个
     * @param id id
     * @return IResult
     */
    IResult<Article>getById(String id);

    /**
     * 新增单个
     * @param article article实体
     * @return IResult
     */
    IResult<Boolean> addArticle(Article article);

    /**
     * 发布文章
     * @param id 文章id
     * @return IResult
     */
    IResult<Boolean>publishArticle(String id);

    /**
     * 修改文章
     * @param article article实体
     * @param id User id
     * @return IResult
     */
    IResult<Boolean>modifyArticle(String id, Article article);

    /**
     * 删除指定文章
     * @param id id
     * @param userId User id
     * @return IResult
     */
    IResult<Boolean>deleteArticle(String id, String userId);

}
