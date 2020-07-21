package com.lsl.article.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsl.article.entity.Article;
import com.lsl.article.service.IArticleService;

/**
 *
 * @author Chrow Yeung
 * @since 2020-02-06
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    public IArticleService articleService;

    /**
     * 跳转列表页面
     * 
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/article/index"})
    public String index(HttpServletRequest request) {
        return "articleIndex";
    }

    /**
     * 保存和修改
     * 
     * @param article
     *            实体
     * @return 0 失败 1 成功
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public int articleSave(Article article) {
        return articleService.saveOrUpdate(article) ? 1 : 0;
    }

    /**
     * 根据id删除对象
     * 
     * @param id
     *            实体ID
     * @return 0 失败 1 成功
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/article/{id}")
    public int articleDelete(Long id) {
        return articleService.removeById(id) ? 1 : 0;
    }

    /**
     * 查询所有用户
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public List<Article> selectAll() {
        return articleService.list();
    }
}