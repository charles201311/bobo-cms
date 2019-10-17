package com.bobo.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bobo.cms.exception.CMSException;
import com.bobo.cms.service.UserService;
import com.bobo.cms.vo.UserVO;

/**
 * 
 * @ClassName: PassportController 
 * @Description: 登录注册
 * @author: charles
 * @date: 2019年10月17日 上午10:05:12
 */

@RequestMapping("passport")
@Controller
public class PassportController {
	
	
	@Resource
	private UserService userService;
   
	/***
	 * 
	 * @Title: reg 
	 * @Description: 去注册页面
	 * @return
	 * @return: String
	 */
	@GetMapping("reg")
	public String reg() {
		
		return "passport/reg";
		
	}
	/**
	 * 
	 * @Title: reg 
	 * @Description: 执行注册
	 * @param user
	 * @return
	 * @return: String
	 */
	@PostMapping("reg")
	public String reg(Model model,UserVO userVO) {
		try {
			userService.insertSelective(userVO);
			return "redirect:login";
		}catch (CMSException cms) {
			cms.printStackTrace();
			//封装service抛出异常
			model.addAttribute("error", cms.getMessage());
		}
		catch (Exception e) {
			
			e.printStackTrace();
			//封装没有处理异常
			model.addAttribute("error", "系统异常,请与管理员联系!");
		}
		
	
		
		return "passport/reg";
		
	}
	
	/**
	 * 去登录
	 * @Title: login 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public String login() {
		
		return "passport/login";
		
	}
}
