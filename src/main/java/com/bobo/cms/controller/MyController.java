package com.bobo.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bobo.cms.domain.Article;
import com.bobo.cms.domain.ArticleWithBLOBs;
import com.bobo.cms.domain.User;
import com.bobo.cms.service.ArticleService;
import com.bobo.cms.util.PageUtil;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心
 * @author: charles
 * @date: 2019年10月17日 下午4:11:24
 */
@Controller
@RequestMapping("my")
public class MyController {

	@Resource
	private ArticleService articleService;
	
	//进入个人中心首页
	@RequestMapping(value = {"","/","index"})
	public String index( ) {
		
		return "my/index";
		
	}

	/**
	 * 
	 * @Title: selectsByUser 
	 * @Description: 显示个人文章
	 * @param article
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String selectsByUser(HttpServletRequest request,Model model,Article article,@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10")Integer pageSize) {
		//默认查询条件
		if(null==article.getStatus()) {
			article.setStatus(0);	
		}
		
		
		//查询自己的文章.   .//
		
		HttpSession session =request.getSession(false);
		if(null==session)//session过期
			return "redirect:/passport/login";
		
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());//文章作者
		
		PageInfo<Article> info = articleService.selects(article, 1, 100);
		
		String pages = PageUtil.page(page, info.getPages(),"/my/articles", pageSize);
		
		model.addAttribute("articles", info.getList());
		model.addAttribute("article", article);
		model.addAttribute("pages", pages);
		
		return "my/articles";
		
	}
	
	
	/**
	 * 
	 * @Title: article 
	 * @Description: 单个文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("article")
	public String article(Model model,Integer id) {
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article",article);
		return "my/article";
		
	}
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 跳转到修改页面
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish(Model model, Integer id) {
		
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "/my/articleupdate";
	}
	
/**
 * 	
 * @Title: publish 
 * @Description: 发布文章
 * @param request
 * @param article
 * @param file
 * @return
 * @return: boolean
 */
	@ResponseBody
	@PostMapping("publish")
	public  boolean publish(HttpServletRequest request,ArticleWithBLOBs article,MultipartFile file) {
		//文件上传
		if(!file.isEmpty()) {
		  String path ="d:/pic/";//文件上传地址
		  //为了防止文件重名,需要使用uuid改变文件的上传名称
		  //先获取文件的原始名称.
		  //1.jpg
		  String oldFilename = file.getOriginalFilename();
		  String filename =UUID.randomUUID()+ oldFilename.substring(oldFilename.lastIndexOf("."));
		  
		  try {
			file.transferTo(new File(path+filename));//把文件写入硬盘
			
			//设置文章标题的图片
			article.setPicture(filename);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		
		
		  //文章的默认属性
		//从session获取当前登录人的信息
		
		HttpSession session =request.getSession(false);
		if(null==session)
			return false;
		
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());//文章作者
		article.setStatus(0);//未审核
		article.setDeleted(0);//未删除
		article.setHits(0);//点击量默认0
		article.setHot(0);//默认非热门
		article.setCreated(new Date());
		article.setUpdated(new Date());
		 	//保存文章
		return	articleService.insertSelective(article)>0;
		
		
	}
	
	
	
/**
 * 	
 * @Title: publish 
 * @Description: 修改文章
 * @param request
 * @param article
 * @param file
 * @return
 * @return: boolean
 */
	@ResponseBody
	@PostMapping("publishUpdate")
	public  boolean publishUpdate(HttpServletRequest request,ArticleWithBLOBs article,MultipartFile file) {
		//文件上传
		if(!file.isEmpty()) {
		  String path ="d:/pic/";//文件上传地址
		  //为了防止文件重名,需要使用uuid改变文件的上传名称
		  //先获取文件的原始名称.
		  //1.jpg
		  String oldFilename = file.getOriginalFilename();
		  String filename =UUID.randomUUID()+ oldFilename.substring(oldFilename.lastIndexOf("."));
		  
		  try {
			file.transferTo(new File(path+filename));//把文件写入硬盘
			
			//设置文章标题的图片
			article.setPicture(filename);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		
		
		  //文章的默认属性
		article.setUpdated(new Date());
		 	//保存文章
		return	articleService.updateByPrimaryKeySelective(article)>0;
		
		
	}
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 去发布文章页面
	 * @return
	 * @return: String
	 */
	@GetMapping("/article/publish")
	public String publish() {
		
		return "my/publish";
		
	}
	
}
