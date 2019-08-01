package com.blog.blog.core.evaluation.evaluation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunchao
 * @since 2019/7/30 17:36
 */
@TableName("b_evaluation_reply")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EReply {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "表主键", example = "UUID", required = true)
    private String id;
    @ApiModelProperty(value = "评论id", example = "UUID", required = true)
    private String evacuationId;
    @ApiModelProperty(value = "用户名", example = "张三", required = true)
    private String userName;
    @ApiModelProperty(value = "被回复人id", example = "UUID", required = true)
    private String replyUserId;
    @ApiModelProperty(value = "被回复人name", example = "UUID", required = true)
    private String replyUserName;
    @ApiModelProperty(value = "回复内容", example = "dilidili", required = true)
    private String replyContent;
    @ApiModelProperty(value = "点赞数", example = "99", required = true)
    private Integer likeCounts;
    @ApiModelProperty(value = "回复日期", example = "2019-12-12", required = true)
    private String createdTime;
    @ApiModelProperty(value = "回复状态", example = "0", required = true)
    private String status;

    @NoArgsConstructor
    public static final class Columns{
        /** 回复评论 ID */
        public static final String ID = "id";
        /** 评论 ID */
        public static final String EVALUATION_ID = "evaluation_id";

    }

}
