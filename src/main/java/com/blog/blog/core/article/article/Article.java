package com.blog.blog.core.article.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunchao
 * @since 2019/7/26 10:41
 */
@TableName("b_article")
@Data
@ApiModel(value = "文章表", description = "文章表")
public class Article {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "表主键", example = "UUID", required = true)
    private String id;
    @ApiModelProperty(value = "文章名", example = "大话西游", required = true)
    private String title;
    @ApiModelProperty(value = "作者", example = "张三", required = true)
    private String author;
    @ApiModelProperty(value = "创建日期", example = "2000/12/12", required = true)
    private String createdTime;
    @ApiModelProperty(value = "文章内容", example = "blabla", required = true)
    private String content;
    @ApiModelProperty(value = "用户ID", example = "UUID", required = true)
    private String userId;
    @ApiModelProperty(value = "标记是否可评论", example = "0", required = true)
    private String remark;
    @ApiModelProperty(value = "是否删除", example = "0", required = true)
    private String isDel;
    @ApiModelProperty(value = "是否发布", example = "0", required = true)
    private String isPublish;


    @NoArgsConstructor
    public static final class Columns{
        /** 文章id */
        public static final String ID = "id";
        /** 用户id */
        public static final String USR_ID = "user_id";
        /** 评论id */
        public static final String EVALUATION_ID = "evaluation_id";
        /** 是否发布*/
        public static final String IS_PUBLISH = "is_publish";
    }

}
