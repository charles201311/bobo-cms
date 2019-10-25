package com.bobo.cms.service;

import java.util.List;

import com.bobo.cms.domain.Special;

public interface SpecialService {

	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加专题
	 * @param special
	 * @return
	 * @return: int
	 */
	int insert(Special special); 
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改专题
	 * @param special
	 * @return
	 * @return: int
	 */
	int update(Special special);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 专题列表
	 * @return
	 * @return: List<Special>
	 */
	List<Special> selects();
	/**
	 * 
	 * @Title: insertSpecialArticle 
	 * @Description: 向专题增加文章
	 * @param sid
	 * @param aid
	 * @return
	 * @return: int
	 */
	int insertSpecialArticle(Integer sid ,Integer aid);
	
	/**
	 * 根据主键查询专题
	 * @Title: select 
	 * @Description: TODO
	 * @param sid
	 * @return
	 * @return: Special
	 */
	Special select(Integer sid);
	
}
