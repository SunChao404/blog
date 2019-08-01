package com.blog.blog.core.evaluation.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.blog.constants.PublicConstants;
import com.blog.blog.core.evaluation.evaluation.EReply;
import com.blog.blog.core.evaluation.dao.EReplyDao;
import com.blog.blog.core.evaluation.evaluation.Evaluation;
import com.blog.blog.core.user.service.UserServiceImpl;
import com.blog.blog.core.user.user.User;
import com.blog.blog.exception.BlogException;
import com.blog.blog.handler.IResult;
import com.blog.blog.utils.DateTimeUtils;
import com.blog.blog.utils.PublicUtils;
import com.blog.blog.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunchao
 * @since 2019/7/30 17:50
 */
@Service
public class EReplyServiceImpl extends ServiceImpl<EReplyDao, EReply> implements EReplyService {
    private UserServiceImpl userService;
    private EvaluationServiceImpl evaluationService;
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Autowired
    public void setEvaluationService(EvaluationServiceImpl evaluationService) {
        this.evaluationService = evaluationService;
    }

    /**
     * 新增回复
     * @param eReply 回复实体
     * @param  id Evaluation的id
     * @param userId userId
     * @return IResult
     */
    @Override
    public IResult<Boolean> addEReply(String id, String userId, EReply eReply) {
        PublicUtils.checkEntityExist(eReply);
        PublicUtils.checkIdExist(id);
        PublicUtils.checkIdExist(userId);
        eReply.setId(UUIDUtils.getUUID());
        Evaluation evaluation = evaluationService.getById(id);
        PublicUtils.checkEntityExist(evaluation);
        User user = userService.getOne(new QueryWrapper<User>().eq(User.Columns.USERNAME, evaluation.getUserName()));
        PublicUtils.checkEntityExist(user);
        User user1 = userService.getById(userId);
        PublicUtils.checkEntityExist(user1);
        eReply.setUserName(user1.getUserName());
        eReply.setReplyUserId(user.getId());
        eReply.setUserName(eReply.getUserName());
        eReply.setEvacuationId(id);
        eReply.setReplyUserId(user.getId());
        eReply.setReplyUserName(evaluation.getUserName());
        eReply.setReplyContent(eReply.getReplyContent());
        checkColumns(eReply);

        return new IResult<Boolean>().success(this.save(eReply), 0, "success");
    }

    /**
     * 发表回复
     * @return IResult
     */
    @Override
    public IResult<Boolean> publishEReply(String id) {
        PublicUtils.checkIdExist(id);
        EReply eReply1 = this.getById(id);
        PublicUtils.checkEntityExist(eReply1);
        eReply1.setLikeCounts(0);
        eReply1.setStatus(PublicConstants.IS_PUBLISH);
        eReply1.setCreatedTime(DateTimeUtils.now());

        return  new IResult<Boolean>().success(this.updateById(eReply1), 0, "success");
    }


    /**
     * 评论关联删除
     * @param id evaluation的id
     * @return IResult
     */
    public IResult<Boolean> deleteEReply(String id) {
        PublicUtils.checkIdExist(id);
        EReply eReply = this.getOne(new QueryWrapper<EReply>().eq(EReply.Columns.EVALUATION_ID, id));
        PublicUtils.checkEntityExist(eReply);
        return new IResult<Boolean>().success(this.removeById(eReply.getId()), 0, "success");
    }

    /**
     * 字段检测
     * @param eReply eReply实体
     */
    private void checkColumns(EReply eReply){
        if(eReply.getReplyContent().isEmpty() || null == eReply.getReplyContent()){
            throw new BlogException("请填写评论内容");
        }

    }
}
