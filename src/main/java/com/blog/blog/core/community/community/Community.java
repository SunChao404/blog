package com.blog.blog.core.community.community;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunchao
 * @since 2019/7/30 10:07
 */
@TableName("b_community")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Community {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "表主键", example = "UUID", required = true)
    private String id;
    @ApiModelProperty(value = "文章id", example = "UUID", required = true)
    private String articleId;
    @ApiModelProperty(value = "文章标题", example = "1999-9-9", required = true)
    private String articleTitle;
    @ApiModelProperty(value = "文章作者", example = "张三", required = true)
    private String articleAuthor;
    @ApiModelProperty(value = "文章发表时间", example = "1999-9-9", required = true)
    private String articleCreatedTime;

    @NoArgsConstructor
    public static final class Columns{
        /** 社区id */
        public static final String ID = "id";
        /** 文章id */
        public static final String ARTICLE_ID = "article_id";

    }
}
