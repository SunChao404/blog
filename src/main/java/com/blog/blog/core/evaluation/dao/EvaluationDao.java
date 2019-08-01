package com.blog.blog.core.evaluation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.blog.core.evaluation.evaluation.Evaluation;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

/**
 * @author sunchao
 * @since 2019/7/30 15:27
 */
@Mapper
public interface EvaluationDao extends BaseMapper<Evaluation> {

    @Results({
            @Result(column = "id", property = "eReplyList",
                    many = @Many(
                            select="com.blog.blog.core.evaluation.dao.EReplyDao.selectByEvaluationId",
                            fetchType = FetchType.EAGER
                    )

            )
    })
    @Select("SELECT * FROM blog.b_evaluation WHERE id = #{id}")
    Evaluation queryErById(String id);

}
