package com.bobo.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bobo.cms.domain.Article;
import com.bobo.cms.domain.User;
import com.bobo.cms.service.ArticleService;
import com.bobo.cms.service.UserService;
import com.bobo.cms.util.PageUtil;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @ClassName: AdminController 
 * @Description: 管理员后台
 * @author: charles
 * @date: 2019年10月15日 下午4:04:22
 */
@RequestMapping("admin")
@Controller
public class AdminController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private ArticleService articleService;
	
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入后台首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"index","","/"})
	public String index() {
		
		return "admin/index";
		
	}
	/**
	 *  
	 * @Title: users 
	 * @Description: 文章列表
	 * @param model
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@GetMapping("articles")
	public String users(Model model,Article article ,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "3")Integer pageSize) {
		 //默认待审
		if(article.getStatus()==null)
		 article.setStatus(0);//待审
		
		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		//调用分页工具
		String pages = PageUtil.page(page, info.getPages(), "/admin/articles", pageSize);
		model.addAttribute("articles", info.getList());
		model.addAttribute("article", article);
		model.addAttribute("pages", pages);
		return "admin/articles";
		
	}
	
	
   /**
    * 
    * @Title: users 
    * @Description: 用户列表
    * @param model
    * @param username
    * @param page
    * @param pageSize
    * @return
    * @return: String
    */
	@GetMapping("users")
	public String users(Model model,@RequestParam(defaultValue = "")String username ,@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "3")Integer pageSize) {
		
		PageInfo<User> info = userService.selects(username, page, pageSize);
		//调用分页工具
		String pages = PageUtil.page(page, info.getPages(), "/admin/users?username="+username, pageSize);
		model.addAttribute("users", info.getList());
		model.addAttribute("username", username);
		model.addAttribute("pages", pages);
		return "admin/users";
		
	}
	
	/**
	 * 修改用户状态
	 * @Title: updateUser 
	 * @Description: TODO
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("updateUser")
	public boolean updateUser(User user) {
		
		return userService.updateByPrimaryKeySelective(user)>0;
	}
}
