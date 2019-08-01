package com.blog.blog.core.community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.blog.constants.PublicConstants;
import com.blog.blog.core.article.article.Article;
import com.blog.blog.core.article.service.ArticleServiceImpl;
import com.blog.blog.core.community.community.Community;
import com.blog.blog.core.community.mapper.CommunityMapper;
import com.blog.blog.handler.IResult;
import com.blog.blog.utils.DateTimeUtils;
import com.blog.blog.utils.PublicUtils;
import com.blog.blog.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunchao
 * @since 2019/7/30 10:15
 */
@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {
    private ArticleServiceImpl articleService;
    @Autowired
    public void setArticleService(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    /**
     * 条件分页查询
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return page
     */
    @Override
    public IResult<IPage<Community>> getByPage(Integer pageNum, Integer pageSize) {
        QueryWrapper<Community> wrapper = new QueryWrapper<>();
        IPage<Community> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        return new IResult<IPage<Community>>().success(page, 0, "success");
    }

    /**
     * 新增文章
     * @param id 文章id
     * @return IResult
     */
    @Override
    public IResult<Boolean> addArticle(String id) {
        PublicUtils.checkIdExist(id);
        Article article = articleService.getOne(new QueryWrapper<Article>().eq(Article.Columns.ID, id));
        PublicUtils.checkEntityExist(article);

        Community community = new Community();
        community.setId(UUIDUtils.getUUID());
        PublicUtils.checkIdExist(community.getId());

        community.setArticleId(article.getId());
        community.setArticleTitle(article.getTitle());
        community.setArticleAuthor(article.getAuthor());
        community.setArticleCreatedTime(DateTimeUtils.now());

        article.setIsPublish(PublicConstants.IS_PUBLISH);
        article.setRemark(PublicConstants.NOT_DRAFT);
        return new IResult<Boolean>().success(this.save(community), 0, "success");
    }

    /**
     * 删除指定文章
     * @param id Article id
     * @return IResult
     */
    @Override
    public IResult<Boolean> deleteArticle(String id) {
        PublicUtils.checkIdExist(id);
        Community community = this.getOne(new QueryWrapper<Community>()
                .eq(Community.Columns.ARTICLE_ID, id));
        PublicUtils.checkEntityExist(community);

        return new IResult<Boolean>().success(this.removeById(community.getId()), 0, "success");
    }
}
