package com.blog.blog.core.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.blog.core.community.community.Community;
import com.blog.blog.handler.IResult;

/**
 * @author sunchao
 * @since 2019/7/30 10:15
 */
public interface CommunityService {

    /**
     * 条件分页查询
     *
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return page
     */
    IResult<IPage<Community>> getByPage(Integer pageNum, Integer pageSize);

    /**
     * 新增文章
     * @param id 文章id
     * @return IResult
     */
    IResult<Boolean> addArticle(String id);

    /**
     * 删除指定文章
     * @param id id
     * @return IResult
     */
    IResult<Boolean>deleteArticle(String id);

}
