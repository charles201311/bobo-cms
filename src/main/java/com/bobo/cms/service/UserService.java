package com.bobo.cms.service;

import com.bobo.cms.domain.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

	/**
	 * 
	 * @Title: selects 
	 * @Description: 列表查询
	 * @param username
	 * @return
	 * @return: List<User>
	 */
	PageInfo<User> selects(String username,Integer page,Integer pageSize);

     
    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

}
