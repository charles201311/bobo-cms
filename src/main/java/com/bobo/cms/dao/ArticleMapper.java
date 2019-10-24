package com.bobo.cms.dao;

import java.util.List;

import com.bobo.cms.domain.Article;
import com.bobo.cms.domain.ArticleWithBLOBs;

public interface ArticleMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 文章列表查询
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	List<ArticleWithBLOBs> selects(Article article);
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
}