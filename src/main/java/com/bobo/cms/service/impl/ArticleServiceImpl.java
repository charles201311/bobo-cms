package com.bobo.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bobo.cms.dao.ArticleMapper;
import com.bobo.cms.dao.TermMapper;
import com.bobo.cms.domain.Article;
import com.bobo.cms.domain.ArticleWithBLOBs;
import com.bobo.cms.domain.Term;
import com.bobo.cms.exception.CMSException;
import com.bobo.cms.service.ArticleService;
import com.bobo.common.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleMapper articleMapper;
	
	@Resource
	private TermMapper termMapper;

	@Override
	public PageInfo<ArticleWithBLOBs> selects(Article article,Integer page,Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		List<ArticleWithBLOBs> list = articleMapper.selects(article);
		
		return new PageInfo<ArticleWithBLOBs>(list);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ArticleWithBLOBs record) {
		try {
			//1.发布文章.返回文章的自增主键值
			articleMapper.insertSelective(record);
			//2. 判断有没有标签.如果有则进行标签处理
			
			//获取文章标签
			String terms = record.getTerms();
			
			if(StringUtil.hasText(terms)) {
				
				String[] split = terms.split(",");
				for (String str : split) {
					//判断该标签在数据库是否存在
					Term term2 = termMapper.selectByName(StringUtil.toUniqueTerm(str));
					if(null==term2) {//如果不存在,则插入该标签,并且返回该标签的自增主键值
					     term2 = new Term();
					     term2.setUniqueName(str);
						termMapper.insert(term2);//插入标签
					}
					//向中间表
					termMapper.insertArticleTerm(record.getId(), term2.getId());
					
				}
				
			}
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("发布失败");
		} 
		
	}

	@Override
	public ArticleWithBLOBs selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ArticleWithBLOBs record) {
		// TODO Auto-generated method stub
		return articleMapper.updateByPrimaryKeySelective(record);
	}

}
