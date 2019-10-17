package com.bobo.cms.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.bobo.cms.domain.User;
import com.bobo.cms.service.UserService;

public class UserServiceImplTest extends JunitParent {
	@Resource
	private UserService userService;

	@Test
	public void testSelects() {
		
		
	}

	@SuppressWarnings("resource")
	@Test
	public void testInsertSelective() {
		User user = new User();
		user.setUsername("王四");
		user.setPassword("456");
		//userService.insertSelective(user);
		
		
	}

	@Test
	public void testSelectByPrimaryKey() {
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
	}

}
