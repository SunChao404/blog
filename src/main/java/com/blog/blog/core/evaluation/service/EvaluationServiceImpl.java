package com.blog.blog.core.evaluation.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.blog.constants.PublicConstants;
import com.blog.blog.core.article.article.Article;
import com.blog.blog.core.article.service.ArticleServiceImpl;
import com.blog.blog.core.evaluation.evaluation.Evaluation;
import com.blog.blog.core.evaluation.dao.EvaluationDao;

import com.blog.blog.core.user.service.UserServiceImpl;
import com.blog.blog.core.user.user.User;
import com.blog.blog.exception.BlogException;
import com.blog.blog.handler.IResult;
import com.blog.blog.utils.DateTimeUtils;
import com.blog.blog.utils.PublicUtils;
import com.blog.blog.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;

/**
 * @author sunchao
 * @since 2019/7/30 15:29
 */
@Service
public class EvaluationServiceImpl extends ServiceImpl<EvaluationDao, Evaluation> implements EvaluationService {

    private  EReplyServiceImpl eReplyService;
    private UserServiceImpl userService;
    private ArticleServiceImpl articleService;
    @Autowired
    public void setEReplyService(EReplyServiceImpl eReplyService) {
        this.eReplyService = eReplyService;
    }
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Autowired
    public void setArticleService(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    /**
     * 指定文章删除评论
     * @param id id
     * @return IResult
     */
    public IResult<Boolean> deleteEvaluation(String id) {
        PublicUtils.checkIdExist(id);
        Evaluation evaluation = this.getOne(new QueryWrapper<Evaluation>()
        .eq(Evaluation.Columns.ARTICLE_ID, id));
        return new IResult<Boolean>().success(this.removeById(evaluation.getId()), 0, "success");
    }

    /**
     * 新增评论
     * @param evaluation evaluation实体
     * @return IResult
     */
    @Override
    public IResult<Boolean> addEvaluation(Evaluation evaluation, String userId) {
        PublicUtils.checkIdExist(userId);
        PublicUtils.checkIdExist(evaluation.getArticleId());
        PublicUtils.checkEntityExist(evaluation);
        evaluation.setId(UUIDUtils.getUUID());
        User user = userService.getById(userId);
        PublicUtils.checkEntityExist(user);
        evaluation.setUserName(user.getUserName());
        evaluation.setContent(evaluation.getContent());
        checkColumns(evaluation);
        evaluation.setStatus(PublicConstants.IS__NOT_PUBLISH);

        return new IResult<Boolean>().success(this.save(evaluation), 0, "success");
    }

    /**
     * 发表评论
     * @param id id
     * @return IResult
     */
    @Override
    public IResult<Boolean> publishEvaluation(String id) {
        PublicUtils.checkIdExist(id);
        Evaluation evaluation = this.getOne(new QueryWrapper<Evaluation>().eq(Evaluation.Columns.ID, id));
        PublicUtils.checkEntityExist(evaluation);
        evaluation.setLikeCounts(0);
        evaluation.setStatus(PublicConstants.IS_PUBLISH);
        evaluation.setCreatedTime(DateTimeUtils.now());

        return  new IResult<Boolean>().success(this.updateById(evaluation), 0, "success");
    }

    /**
     * 点赞
     * @param evaluation evaluation实体
     * @return IResult
     */
    @Override
    public IResult<Boolean> likeEvaluation(Evaluation evaluation) {
        PublicUtils.checkEntityExist(evaluation);
        Evaluation evaluation1 = this.getOne(new QueryWrapper<Evaluation>()
                .eq(Evaluation.Columns.ID, evaluation.getId()));
        PublicUtils.checkEntityExist(evaluation1);
        evaluation1.setLikeCounts(evaluation.getLikeCounts()+1);
        return new IResult<Boolean>().success(this.updateById(evaluation1), 0, "success");
    }

    /**
     * 删除单个评论
     * @param id 评论id
     * @param userId userId
     * @return IResult
     */
    @Override
    public IResult<Boolean> delOneEvaluation(String id, String userId) {
        PublicUtils.checkIdExist(id);
        PublicUtils.checkIdExist(userId);
        Evaluation evaluation = this.getOne(new QueryWrapper<Evaluation>().eq(Evaluation.Columns.ID, id));
        PublicUtils.checkEntityExist(evaluation);

        User user = userService.getById(userId);
        PublicUtils.checkEntityExist(user);
        Article article = articleService.getOne(new QueryWrapper<Article>()
                .eq(Evaluation.Columns.ARTICLE_ID, evaluation.getArticleId()));
        PublicUtils.checkEntityExist(article);
        if(!(user.getUserName().equals(evaluation.getUserName()) || user.getUserName().equals(article.getAuthor()))){
            throw new BlogException("非文章作者或该条评论评论人，不可修改");
        }
        // 删除该评论下所有回复
        eReplyService.deleteEReply(id);
        return new IResult<Boolean>().success(this.removeById(id), 0, "success");
    }

    /**
     * 查询单个评论
     * @param id 评论id
     * @return IResult
     */
    @Override
    public IResult<Evaluation> findEvaluation(String id) {
        PublicUtils.checkIdExist(id);
        return new IResult<Evaluation>().success(getById(id), 0, "success");
    }

    /**
     * 查询单个评论,包括回复
     * @param id 评论id
     * @return IResult
     */
    @Override
    public IResult<Evaluation> findEvaluationReply(String id) {
        PublicUtils.checkIdExist(id);
        return new IResult<Evaluation>().success(this.baseMapper.queryErById(id), 0, "success");
    }

    /**
     * 条件分页查询
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return page
     */
    @Override
    public IResult<IPage<Evaluation>> getByPage(Integer pageNum, Integer pageSize) {
        QueryWrapper<Evaluation> wrapper = new QueryWrapper<>();
        IPage<Evaluation> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        return new IResult<IPage<Evaluation>>().success(page,0,"success");
    }


    /**
     * 字段检测
     * @param evaluation evaluation实体
     */
    private void checkColumns(Evaluation evaluation){
        if(evaluation.getContent().isEmpty() || null == evaluation.getContent()){
            throw new BlogException("请填写评论内容");
        }

    }
}
