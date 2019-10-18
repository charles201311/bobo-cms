package com.bobo.cms.dao;

import java.util.List;

import com.bobo.cms.domain.Category;

public interface CategoryMapper {
	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 查询某个栏目下的分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}