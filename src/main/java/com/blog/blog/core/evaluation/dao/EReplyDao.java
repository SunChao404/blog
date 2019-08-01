package com.blog.blog.core.evaluation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.blog.core.evaluation.evaluation.EReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sunchao
 * @since 2019/7/30 17:49
 */
@Mapper
public interface EReplyDao extends BaseMapper<EReply> {
    @Select("SELECT * FROM blog.b_evaluation_reply WHERE evacuation_id = #{id}")
    List<EReply> selectByEvaluationId(String id);

}
