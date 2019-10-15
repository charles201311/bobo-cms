package com.bobo.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bobo.cms.domain.User;
import com.bobo.cms.service.UserService;
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
		
		model.addAttribute("users", info.getList());
		model.addAttribute("username", username);
		return "admin/users";
		
	}
}
