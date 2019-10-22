package com.bobo.cms.controller;

import java.util.Calendar;
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
	public String index(Model model,Article article,@RequestParam(defaultValue ="1")Integer page,@RequestParam(defaultValue ="5")Integer pageSize) {
		
		//只查询文章状态审核过的
		article.setStatus(1);
		article.setDeleted(0);//未删除
	   //1.显示左侧栏目
		List<Channel> channels = channelService.selects();	
		model.addAttribute("channels", channels);
		//2.查询栏目下的分类
		if(null!=article.getChannelId()) {
		  List<Category> categorys = categoryService.selectsByChannelId(article.getChannelId());
		  model.addAttribute("categorys", categorys);
		  
		//3. 如果栏目不为空,并且分类也不为空则查询分类下的文章
		//	if(null!=article.getCategoryId()) {
				PageInfo<Article> info = articleService.selects(article, page, pageSize);
				
				String url="/?channelId="+article.getChannelId();
				if(null!=article.getCategoryId()) {
					url+="&categoryId="+article.getCategoryId();
				}
				
				String pages = PageUtil.page(page, info.getPages(), url, pageSize);
				
				
				model.addAttribute("articles", info.getList());
				model.addAttribute("pages", pages);
			//}
			
			
		}
		
		//4 如果栏目为空则默认查询热点文章
		if(null==article.getChannelId()) {
			
			article.setHot(1);//热点文章
			PageInfo<Article> info = articleService.selects(article, page, pageSize);
            String pages = PageUtil.page(page, info.getPages(), "/", pageSize);
			model.addAttribute("hotArticles", info.getList());
			model.addAttribute("pages", pages);
		}
		//封装查询条件
		model.addAttribute("article", article);
		return "index/index";
		
	}
	
	@GetMapping("select")
	public String select(Model model ,Integer id) {
		
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "index/article";
	}

}
