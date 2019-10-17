package com.bobo.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

	
	//进入个人中心首页
	@RequestMapping(value = {"","/","index"})
	public String index() {
		
		return "my/index";
		
	}
	
}
