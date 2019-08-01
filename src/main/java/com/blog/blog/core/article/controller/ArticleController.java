package com.blog.blog.core.article.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.blog.core.article.article.Article;
import com.blog.blog.core.article.service.ArticleService;
import com.blog.blog.core.user.user.User;
import com.blog.blog.handler.IResult;
import com.blog.blog.utils.PublicUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sunchao
 * @since 2019/7/26 11:03
 */
@Api(tags = {"ArticleController"})
@RestController
@RequestMapping("/api")
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 条件分页查询
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return page
     */
    @ApiOperation(value = "条件分页查询", notes = "条件分页查询")
    @GetMapping("/article/page")
    public IResult<IPage<Article>> queryByPage(@RequestParam Integer pageNum,
                                               @RequestParam Integer pageSize) {
        return articleService.getByPage(pageNum, pageSize);
    }

    /**
     * 文章列表查询
     * @return List
     */
    @ApiOperation(value = "文章列表查询", notes = "文章列表查询")
    @GetMapping("/articles")
    public IResult<List<Article>> queryList() {
        return articleService.getList();
    }

    /**
     * id查询单个
     * @param id id
     * @return IResult
     */
    @ApiOperation(value = "查询单篇文章", notes = "查询单篇文章")
    @GetMapping("/article/{id}")
    public IResult<Article>getArticle(@ApiParam(value = "article的id") @PathVariable("id") String id){
        return articleService.getById(id);
    }

    /**
     * 新增单个
     * @param article article实体
     * @param id User的id
     * @return IResult
     */
    @ApiOperation(value = "新建单篇文章", notes = "新建单篇文章")
    @PostMapping("/article/{id}")
    public IResult<Boolean> add(@PathVariable("id") String id, @RequestBody Article article){
        article.setUserId(id);
        return articleService.addArticle(article);
    }

    /**
     * 发布文章
     * @param id 文章id
     * @return IResult
     */
    @ApiOperation(value = "发布指定文章", notes = "发布指定文章")
    @PutMapping("/article_community/{id}")
    public IResult<Boolean>publish(@PathVariable("id") String id){
        return articleService.publishArticle(id);
    }

    /**
     * 修改文章
     * @param article article实体
     * @param id User id
     * @return IResult
     */
    @ApiOperation(value = "修改文章", notes = "修改文章")
    @PutMapping("/article/{id}")
    public IResult<Boolean>update(@PathVariable("id") String id, @RequestBody Article article){
        return articleService.modifyArticle(id, article);
    }

    /**
     * 删除指定文章
     * @param id id
     * @param userId User id
     * @return IResult
     */
    @ApiOperation(value = "删除指定文章", notes = "删除指定文章")
    @DeleteMapping("/article/{id}/{userId}")
    public IResult<Boolean>delete(@PathVariable("id") String id ,@PathVariable("userId") String userId){
        return articleService.deleteArticle(id, userId);
    }



}
