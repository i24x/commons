package com.lsl.article.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.article.entity.Article;
import com.lsl.article.mapper.ArticleMapper;
import com.lsl.article.service.IArticleService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Chrow Yeung
 * @since 2020-02-06
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Override
    public String getServerName(String var1) {
        return var1;
    }

    @Override
    public String getServerName() {
        return "IArticleService";
    }
}
