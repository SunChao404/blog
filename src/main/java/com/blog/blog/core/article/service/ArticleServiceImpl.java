package com.blog.blog.core.article.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.blog.constants.PublicConstants;
import com.blog.blog.core.article.article.Article;
import com.blog.blog.core.article.mapper.ArticleMapper;
import com.blog.blog.core.community.community.Community;
import com.blog.blog.core.community.service.CommunityServiceImpl;
import com.blog.blog.core.evaluation.service.EvaluationServiceImpl;
import com.blog.blog.core.user.service.UserServiceImpl;
import com.blog.blog.core.user.user.User;
import com.blog.blog.exception.BlogException;
import com.blog.blog.handler.IResult;
import com.blog.blog.utils.DateTimeUtils;
import com.blog.blog.utils.PublicUtils;
import com.blog.blog.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author sunchao
 * @since 2019/7/26 11:04
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    private  UserServiceImpl userService;
    private CommunityServiceImpl communityService;
    private EvaluationServiceImpl evaluationService;
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCommunityService(CommunityServiceImpl communityService) {
        this.communityService = communityService;
    }

    @Autowired
    public void setEvaluationService(EvaluationServiceImpl evaluationService) {
        this.evaluationService = evaluationService;
    }

    /**
     * 条件分页查询
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return page
     */
    @Override
    public IResult<IPage<Article>> getByPage(Integer pageNum, Integer pageSize) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        IPage<Article> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        return new IResult<IPage<Article>>().success(page,0,"success");
    }

    /**
     * 文章列表查询
     * @return List
     */
    @Override
    public IResult<List<Article>> getList() {
        List<Article> articleList = this.list(new QueryWrapper<>());
        return new IResult<List<Article>>().success(articleList, 0, "success");
    }

    /**
     * id查询单个
     * @param id id
     * @return IResult
     */
    @Override
    public IResult<Article> getById(String id) {
        PublicUtils.checkIdExist(id);
        Article article = this.getOne(new QueryWrapper<Article>()
        .eq(Article.Columns.ID, id));
        PublicUtils.checkEntityExist(article);

        return new IResult<Article>().success(article,0, "success");
    }

    /**
     * 新增单个
     * @param article article实体
     * @return IResult
     */
    @Override
    public IResult<Boolean> addArticle(Article article) {

        PublicUtils.checkIdExist(article.getUserId());
        PublicUtils.checkEntityExist(article);
        checkColumns(article);
        User user = userService.getOne(new QueryWrapper<User>().eq(User.Columns.ID, article.getUserId()));
        PublicUtils.checkEntityExist(user);

        Article article1 = new Article();
        article1.setId(UUIDUtils.getUUID());
        article1.setAuthor(user.getUserName());
        article1.setContent(article.getContent());
        article1.setUserId(user.getId());
        article1.setCreatedTime(DateTimeUtils.now());
        article1.setTitle(article.getTitle());
        // 标记草稿 0是1否
        article1.setRemark(PublicConstants.DRAFT);
        article1.setIsDel(PublicConstants.IS__NOT_DELETE);
        article1.setIsPublish(PublicConstants.IS__NOT_PUBLISH);


        return new IResult<Boolean>().success(this.save(article1),0, "success");
    }

    /**
     * 发布文章
     * @param id 文章id
     * @return IResult
     */
    @Override
    public IResult<Boolean> publishArticle(String id) {
        PublicUtils.checkIdExist(id);
        Article article = this.getOne(new QueryWrapper<Article>().eq(Article.Columns.ID, id));
        PublicUtils.checkEntityExist(article);
        communityService.addArticle(id);
        return new IResult<Boolean>().success(this.updateById(article),0, "success");
    }

    /**
     * 修改文章
     * @param article article实体
     * @param id User id
     * @return IResult
     */
    @Override
        public IResult<Boolean> modifyArticle(String id, Article article) {
        PublicUtils.checkIdExist(id);
        PublicUtils.checkEntityExist(article);
        checkColumns(article);
        if(!(id.equals(article.getUserId()))) {
            throw new BlogException("非文章作者，不可修改");
        }
        return new IResult<Boolean>().success(this.updateById(article), 0, "success");
    }

    /**
     * 删除指定文章
     * @param id id
     * @param userId User id
     * @return IResult
     */
    @Override
    public IResult<Boolean> deleteArticle(String id, String userId) {
        PublicUtils.checkIdExist(id);
        PublicUtils.checkIdExist(userId);

        Article article = this.getOne(new QueryWrapper<Article>().eq(Article.Columns.ID, id));
        PublicUtils.checkEntityExist(article);
        if(!(id.equals(article.getUserId()))) {
            throw new BlogException("非文章作者，不可修改");
        }
        article.setIsDel(PublicConstants.IS_DELETE);
        // 同时删除社区上的文章
        communityService.deleteArticle(id);
        // 同时删除评论
        evaluationService.deleteEvaluation(id);
        return new IResult<Boolean>().success(this.removeById(id), 0, "success");
    }


    /**
     * 字段检测
     * @param article article实体
     */
    private void checkColumns(Article article){
        if(article.getContent().isEmpty() || null == article.getContent()){
            throw new BlogException("请输入内容");
        }
        if(article.getTitle().isEmpty() || null == article.getTitle()){
            throw new BlogException("请输入文章标题");
        }
    }
}
