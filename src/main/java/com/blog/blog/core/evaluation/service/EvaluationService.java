package com.blog.blog.core.evaluation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.blog.core.evaluation.evaluation.Evaluation;
import com.blog.blog.handler.IResult;

/**
 * @author sunchao
 * @since 2019/7/30 15:28
 */
public interface EvaluationService {


    /**
     * 新增评论
     * @param evaluation evaluation实体
     * @param userId userId
     * @return IResult
     */
    IResult<Boolean> addEvaluation(Evaluation evaluation, String userId);

    /**
     * 发表评论
     * @param id id
     * @return IResult
     */
    IResult<Boolean>publishEvaluation(String id);

    /**
     * 点赞
     * @param evaluation evaluation实体
     * @return IResult
     */
    IResult<Boolean> likeEvaluation(Evaluation evaluation);

    /**
     * 删除单个评论
     * @param id 评论id
     * @param userId userId
     * @return IResult
     */
    IResult<Boolean> delOneEvaluation(String id, String userId);

    /**
     * 查询单个评论
     * @param id 评论id
     * @return IResult
     */
    IResult<Evaluation> findEvaluation(String id);

    /**
     * 查询单个评论,包括回复
     * @param id 评论id
     * @return IResult
     */
    IResult<Evaluation> findEvaluationReply(String id);

    /**
     * 条件分页查询
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return page
     */
    IResult<IPage<Evaluation>> getByPage(Integer pageNum, Integer pageSize);

}
