package com.bobo.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bobo.cms.domain.Article;
import com.bobo.cms.domain.ArticleWithBLOBs;
import com.bobo.cms.service.ArticleService;
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
	@RequestMapping("selectsByUser")
	public String selectsByUser(Model model,Article article) {
		
		//查询自己的文章.   .//
		article.setUserId(152);
		
		
		PageInfo<Article> info = articleService.selects(article, 1, 100);
		model.addAttribute("articles", info.getList());
		return "my/articles";
		
	}
	
	@ResponseBody
	@PostMapping("publish")
	public  boolean publish(ArticleWithBLOBs article,MultipartFile file) {
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
		article.setUserId(152);//先写死//文章作者
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
	 * @Description: 去发布文章页面
	 * @return
	 * @return: String
	 */
	@GetMapping("/article/publish")
	public String publish() {
		
		return "my/publish";
		
	}
	
}
