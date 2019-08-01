package com.blog.blog.core.evaluation.controller;

import com.blog.blog.core.evaluation.evaluation.EReply;
import com.blog.blog.core.evaluation.service.EReplyService;
import com.blog.blog.handler.IResult;
import com.blog.blog.utils.PublicUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sunchao
 * @since 2019/7/30 17:51
 */
@Api(tags = {"EReplyController"})
@RestController
@RequestMapping("/api")
public class EReplyController {
    private EReplyService eReplyService;
    @Autowired
    public void setEReplyService(EReplyService eReplyService) {
        this.eReplyService = eReplyService;
    }
    /**
     * 新增回复
     * @param eReply 回复实体
     * @param  id Evaluation的id
     * @param userId userId
     * @return IResult
     */
    @ApiOperation(value = "新增回复", notes = "新增回复")
    @PostMapping("addERply/{id}/{userId}")
    public IResult<Boolean> add(@PathVariable("id") String id,
                                @PathVariable("userId") String userId, @RequestBody EReply eReply){

        return eReplyService.addEReply(id, userId, eReply);
    }

    /**
     * 发表回复
     * @param id   eReply实体id
     * @return IResult
     */
    @ApiOperation(value = "发表回复", notes = "发表回复")
    @PutMapping("/publishERply/{id}")
    public IResult<Boolean> publish(@PathVariable("id") String id){
        return eReplyService.publishEReply(id);
    }


}
