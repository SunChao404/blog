package com.blog.blog.core.evaluation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.blog.core.evaluation.evaluation.Evaluation;
import com.blog.blog.core.evaluation.service.EvaluationService;
import com.blog.blog.handler.IResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sunchao
 * @since 2019/7/30 15:27
 */
@Api(tags = {"EvaluationController"})
@RestController
@RequestMapping("/api")
public class EvaluationController {
    private EvaluationService evaluationService;
    @Autowired
    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    /**
     * 新增评论
     * @param evaluation evaluation实体
     * @param id 文章id
     * @param userId userId
     * @return IResult
     */
    @ApiOperation(value = "新增评论", notes = "新增评论")
    @PostMapping("/addEvaluation/{id}/{userId}")
    public IResult<Boolean> add(@PathVariable("id") String id,
                                @PathVariable("userId") String userId, @RequestBody Evaluation evaluation){
        evaluation.setArticleId(id);
        return evaluationService.addEvaluation(evaluation, userId);
    }

    /**
     * 发表评论
     * @param id evaluation的id
     * @return IResult
     */
    @ApiOperation(value = "发表评论", notes = "发表评论")
    @PutMapping("/publishEvaluation/{id}")
    public IResult<Boolean> publish(@ApiParam(value = "evaluation的id") @PathVariable("id") String id){
        return evaluationService.publishEvaluation(id);
    }

    /**
     * 点赞
     * @param evaluation evaluation实体
     * @param id evaluation的id
     * @return IResult
     */
    @ApiOperation(value = "点赞", notes = "点赞")
    @PutMapping("/likeEvaluation/{id}")
    public IResult<Boolean> like(@PathVariable("id") String id, @RequestBody Evaluation evaluation){
        evaluation.setId(id);
        return evaluationService.likeEvaluation(evaluation);
    }

    /**
     * 删除单个评论
     * @param id 评论id
     * @param userId userId
     * @return IResult
     */
    @ApiOperation(value = "删除评论", notes = "删除评论")
    @DeleteMapping("/deleteEvaluation/{id}/{userId}")
    public IResult<Boolean> delEvaluation(@PathVariable("id") String id, @PathVariable("userId") String userId){
        return evaluationService.delOneEvaluation(id, userId);
    }

    /**
     * 查询单个评论
     * @param id 评论id
     * @return IResult
     */
    @ApiOperation(value = "查询单个评论", notes = "查询单个评论")
    @GetMapping("/getEvaluation/{id}")
    public IResult<Evaluation> getEvaluation(@PathVariable("id") String id){
        return evaluationService.findEvaluation(id);
    }

    /**
     * 查询单个评论,包括回复
     * @param id 评论id
     * @return IResult
     */
    @ApiOperation(value = "查询单个评论,包括回复", notes = "查询单个评论,包括回复")
    @GetMapping("/getEvaluationEr/{id}")
    public IResult<Evaluation> getEvaluationReply(@PathVariable("id") String id){
        return evaluationService.findEvaluationReply(id);
    }

    /**
     * 条件分页查询
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return page
     */
    @ApiOperation(value = "条件分页查询", notes = "条件分页查询")
    @GetMapping("/evaluation/page")
    public IResult<IPage<Evaluation>> queryByPage(@RequestParam Integer pageNum,
                                               @RequestParam Integer pageSize) {
        return evaluationService.getByPage(pageNum, pageSize);
    }

}
