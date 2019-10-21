package com.bobo.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bobo.cms.domain.Category;
import com.bobo.cms.domain.Channel;
import com.bobo.cms.service.CategoryService;
import com.bobo.cms.service.ChannelService;

@RequestMapping("channel")
@Controller
public class ChannelController {
	
	@Resource
	private ChannelService channelService;
	@Resource
	private CategoryService categoryService;
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询所有栏目
	 * @return
	 * @return: List<Channel>
	 */
	@ResponseBody
	@RequestMapping("channels")
	public List<Channel> selects(){
		return  channelService.selects();
		
	}
	/**
	 * 根据栏目查询分类
	 * @Title: selectCategorysByCid 
	 * @Description: TODO
	 * @return
	 * @return: List<Category>
	 */
	@ResponseBody
	@RequestMapping("selectCategorysByCid")
	public List<Category> selectCategorysByCid(Integer cid){
		return categoryService.selectsByChannelId(cid);
	}

}
