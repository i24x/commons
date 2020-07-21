package com.lsl.article.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lsl.QuickstartApplication;
import com.lsl.article.entity.Article;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuickstartApplication.class})
public class ArticleMapperTest {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void queryArticle() {
        List<Article> articles = articleMapper.selectAll();
        articles.stream().forEach(x -> System.out.println(x));
    }

    @Test
    public void updateArticle() {

        // Article art = new Article();
        // art.setId(29L);
        // art.setName("小鱼儿");
        // int m = articleMapper.updateById(art);
        // Article article = articleMapper.selectById(29L);
        // System.out.println(article);

        QueryWrapper<Article> wp = Wrappers.<Article>query().select("id", "name").likeRight("name", "小鱼");

        Wrappers.update().set(true, "", "");
        List<Article> articles = articleMapper.getAllArticles(wp);
        articles.stream().forEach(x -> {
            System.out.println(x);
        });

    }
}