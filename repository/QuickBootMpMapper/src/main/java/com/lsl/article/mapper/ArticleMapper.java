package com.lsl.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lsl.article.entity.Article;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Chrow Yeung
 * @since 2020-02-06
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> selectAll();

    @Select("select ${ew.SqlSelect} from article ${ew.customSqlSegment}")
    List<Article> getAllArticles(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select ${ew.SqlSelect} from ${tableName} ${ew.customSqlSegment}")
    List<Article> listArticleByCondition(@Param("tableName") String tableName, @Param("ew") Wrapper wrapper);

}
