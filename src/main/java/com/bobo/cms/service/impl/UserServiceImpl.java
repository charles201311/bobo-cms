package com.bobo.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bobo.cms.dao.UserMapper;
import com.bobo.cms.domain.User;
import com.bobo.cms.exception.CMSException;
import com.bobo.cms.service.UserService;
import com.bobo.cms.util.Md5Util;
import com.bobo.cms.vo.UserVO;
import com.bobo.common.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public PageInfo<User> selects(String username, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<User> list = userMapper.selects(username);
		return new PageInfo<User>(list);
	}

	@Override
	public int insertSelective(UserVO userVO) {
		// 如果用户为null. 说明没有传值
		if (null == userVO) {

			throw new CMSException("用户名或密码必须输入");
		}

		// 其他情况判断
		if (!StringUtil.hasText(userVO.getUsername())) {
			throw new CMSException("用户名必须输入");
		}
		if (!(userVO.getUsername().length() >= 2 && userVO.getUsername().length() <= 5)) {
			throw new CMSException("用户名长度在[2-5]之间");
		}

		if (!StringUtil.hasText(userVO.getPassword())) {
			throw new CMSException("密码必须输入");
		}
		if (!(userVO.getPassword().length() >= 6 && userVO.getPassword().length() <= 10)) {
			throw new CMSException("密码长度在[6-10]之间");
		}
		
		if (!(userVO.getPassword().equals(userVO.getRepassword()))) {
			throw new CMSException("两次密码不一致");
		}

		//对密码进行加密
		userVO.setPassword(Md5Util.md5Encoding(userVO.getPassword()));
		return userMapper.insertSelective(userVO);
	}

	
	
	
	@Override
	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public User login(User user) {
		if(null==user) {
			throw new CMSException("用户名和密码必须输入");
		}
		if(!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名必须输入");
		}
		if(!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码必须输入");
		}
		//检查用户名是否存在
		List<User> list = userMapper.selects(user.getUsername());
		if(null==list||list.size()==0) {
			throw new CMSException("无此用户");
		}
		//比较密码
		if(!(list.get(0).getPassword().equals(Md5Util.md5Encoding(user.getPassword())))) {
			throw new CMSException("密码不正确");
		}
		
		return list.get(0);
	}

}
