package com.bobo.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bobo.cms.domain.Article;
import com.bobo.cms.domain.ArticleWithBLOBs;
import com.bobo.cms.domain.Category;
import com.bobo.cms.domain.Channel;
import com.bobo.cms.service.ArticleService;
import com.bobo.cms.service.CategoryService;
import com.bobo.cms.service.ChannelService;
import com.bobo.cms.util.PageUtil;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: IndecController 
 * @Description: cms首页
 * @author: charles
 * @date: 2019年10月18日 上午10:18:37
 */

@Controller
public class IndexController {
	
	@Resource
	private ChannelService channelService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private ArticleService articleService;
	/**
	 * 
	 * @Title: index 
	 * @Description: 首页
	 * @return
	 * @return: String
	 */
	
	@RequestMapping(value = {"","/","index"})
	public String index(Model model,Article article,@RequestParam(defaultValue ="1")Integer page,@RequestParam(defaultValue ="10")Integer pageSize) {
		
	   //1.显示左侧栏目
		List<Channel> channels = channelService.selects();	
		model.addAttribute("channels", channels);
		//2.查询栏目下的分类
		if(null!=article.getChannelId()) {
		  List<Category> categorys = categoryService.selectsByChannelId(article.getChannelId());
		  model.addAttribute("categorys", categorys);
		}
		//3.查询分类下的文章
		if(null!=article.getCategoryId()) {
			PageInfo<Article> info = articleService.selects(article, page, pageSize);
			PageUtil.page(page, info.getPages(), "/channelId="+article.getChannelId()+"&categoryId="+article.getCategoryId(), pageSize);
			
			model.addAttribute("articles", info.getList());
		}
		
		return "index/index";
		
	}
	
	@GetMapping("select")
	public String select(Model model ,Integer id) {
		
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "index/article";
	}

}
