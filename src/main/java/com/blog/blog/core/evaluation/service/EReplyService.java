package com.blog.blog.core.evaluation.service;

import com.blog.blog.core.evaluation.evaluation.EReply;
import com.blog.blog.handler.IResult;

/**
 * @author sunchao
 * @since 2019/7/30 17:49
 */
public interface EReplyService {

    /**
     * 新增回复
     * @param eReply 回复实体
     * @param  id Evaluation的id
     * @param userId userId
     * @return IResult
     */
    IResult<Boolean> addEReply(String id, String userId, EReply eReply);

    /**
     * 发表回复
     * @return IResult
     */
    IResult<Boolean> publishEReply(String id);


}
