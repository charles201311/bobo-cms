package com.bobo.cms.dao;

import org.apache.ibatis.annotations.Param;

import com.bobo.cms.domain.Term;

public interface TermMapper {
	
	/**
	 * 
	 * @Title: selectByName 
	 * @Description: 根据标签查询标签对象
	 * @param name
	 * @return
	 * @return: Term
	 */
  Term selectByName(String name);
	
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 保存标签
	 * @param term
	 * @return
	 * @return: int
	 */
	int insert(Term term);
	/**
	 * 
	 * @Title: insertArticleTerm 
	 * @Description: 向中间表插入数据
	 * @param aid
	 * @return
	 * @return: int
	 */
	int insertArticleTerm(@Param("aid")Integer aid ,@Param("tid")Integer tid);

}
