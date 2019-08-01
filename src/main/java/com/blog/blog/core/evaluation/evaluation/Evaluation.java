package com.blog.blog.core.evaluation.evaluation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sunchao
 * @since 2019/7/30 15:20
 */
@TableName("b_evaluation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "表主键", example = "UUID", required = true)
    private String id;

    @ApiModelProperty(value = "文章id", example = "UUID", required = true)
    private String articleId;

    @ApiModelProperty(value = "评论者名字", example = "李四", required = true)
    private String userName;

    @ApiModelProperty(value = "评论内容", example = "balabala", required = true)
    private String content;

    @ApiModelProperty(value = "评论时间", example = "2019-1-1", required = true)
    private String createdTime;

    @ApiModelProperty(value = "评论状态", example = "0", required = true)
    private String status;

    @ApiModelProperty(value = "点赞数", example = "99", required = true)
    private Integer likeCounts;

    @TableField(exist = false)
    private List<EReply> eReplyList;

    @NoArgsConstructor
    public static final class Columns{
        /** 评论 ID */
        public static final String ID = "id";
        /** 文章 ID */
        public static final String ARTICLE_ID = "article_id";

    }

}
