package com.blog.blog.core.community.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.blog.core.community.community.Community;
import com.blog.blog.core.community.service.CommunityService;
import com.blog.blog.handler.IResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sunchao
 * @since 2019/7/30 10:14
 */
@Api(tags = {"CommunityController"})
@RestController
@RequestMapping("/api")
public class CommunityController {
    private CommunityService communityService;

    @Autowired
    public void setCommunityService(CommunityService communityService) {
        this.communityService = communityService;
    }

    /**
     * 条件分页查询
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return page
     */
    @ApiOperation(value = "条件分页查询", notes = "条件分页查询")
    @GetMapping("/community/page")
    public IResult<IPage<Community>> queryByPage(@RequestParam Integer pageNum,
                                                 @RequestParam Integer pageSize) {
        return communityService.getByPage(pageNum, pageSize);
    }

    /**
     * 新增文章
     * @param id 文章id
     * @return IResult
     */
    @ApiOperation(value = "新增文章", notes = "新增文章")
    @PostMapping("/article_community/{id}")
    public IResult<Boolean>add(@PathVariable("id") String id){
        return communityService.addArticle(id);
    }

    /**
     * 删除指定文章
     * @param id id
     * @return IResult
     */
    @ApiOperation(value = "删除指定文章", notes = "删除指定文章")
    @DeleteMapping("/article_community/{id}")
    public IResult<Boolean>delete(@PathVariable("id") String id){
        return communityService.deleteArticle(id);
    }
}
