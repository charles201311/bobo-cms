package com.bobo.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.bobo.cms.service.UserService;
import com.bobo.cms.util.ArticleEnum;
import com.bobo.cms.util.PageUtil;
import com.bobo.cms.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

/**
 * 
 * @ClassName: MyController
 * @Description: 个人中心
 * @author: bobo
 * @date: 2019年10月17日 下午4:11:24
 */
@Controller
@RequestMapping("my")
public class MyController {

	@Resource
	private ArticleService articleService;
	@Resource
	private UserService userService;

	// 进入个人中心首页
	@RequestMapping(value = { "", "/", "index" })
	public String index() {

		return "my/index";

	}
	
	/**
	 * 去修改用户信息页面
	 * @Title: update 
	 * @Description: TODO
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@GetMapping("user/update")
	public String update(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session==null) {
			return "redirect:/passport/login";
		}
		User user = (User) session.getAttribute("user");
		User user2 = userService.selectByPrimaryKey(user.getId());
		request.setAttribute("user", user2);
		return "my/user";
	}

	
	/**
	 * 修改用户信息
	 * @Title: update 
	 * @Description: TODO
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("user/update")
	public boolean update(User user) {
		return userService.updateByPrimaryKeySelective(user)>0;
	}

	/**
	 * 发布图片
	 * 
	 * @Title: publishPic
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("/articlepic/publish")
	public String publishPic() {

		return "my/publishpic";
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
	@PostMapping("publishpic")
	public boolean publishpic(HttpServletRequest request, ArticleWithBLOBs article, MultipartFile[] files,String descr[]) {

		
		List<ArticleVO> list =new ArrayList<ArticleVO>();
		if (files.length > 0) {
			int i =0;
			for (MultipartFile file : files) {

				if (!file.isEmpty()) {
					String path = "d:/pic/";// 文件上传地址
					
					// 为了防止文件重名,需要使用uuid改变文件的上传名称
					// 先获取文件的原始名称.
					// 1.jpg
					String oldFilename = file.getOriginalFilename();
					String filename = UUID.randomUUID() + oldFilename.substring(oldFilename.lastIndexOf("."));

					try {
						file.transferTo(new File(path + filename));// 把文件写入硬盘

						// 设置文章标题的图片
						article.setPicture(filename);
						//封装图片的地址和描述
						ArticleVO vo  = new ArticleVO();
						vo.setDescr(descr[i]);//描述
						vo.setUrl(filename);
						list.add(vo);
						i++;

					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} // 文章的默认属性
			// 从session获取当前登录人的信息

		HttpSession session = request.getSession(false);
		if (null == session)
			return false;

		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());// 文章作者
		article.setStatus(0);// 未审核
		article.setDeleted(0);// 未删除
		article.setHits(0);// 点击量默认0
		article.setHot(0);// 默认非热门
		article.setCreated(new Date());
		article.setUpdated(new Date());
		
		//设置文章类型
		article.setContentType(ArticleEnum.IMAGE.getCode());
		Gson gson = new Gson();
		System.out.println(gson.toJson(list));
				//把对象转为json
		article.setContent(gson.toJson(list));
		// 保存文章
		return articleService.insertSelective(article) > 0;

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
	public String selectsByUser(HttpServletRequest request, Model model, Article article,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
		// 默认查询条件
		if (null == article.getStatus()) {
			article.setStatus(0);
		}

		// 查询自己的文章. .//

		HttpSession session = request.getSession(false);
		if (null == session)// session过期
			return "redirect:/passport/login";

		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());// 文章作者
		article.setContentType(ArticleEnum.HTML.getCode());//普通文章
		PageInfo<ArticleWithBLOBs> info = articleService.selects(article, 1, 100);

		String pages = PageUtil.page(page, info.getPages(), "/my/articles", pageSize);

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
	public String article(Model model, Integer id) {
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
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
	public boolean publish(HttpServletRequest request, ArticleWithBLOBs article, MultipartFile file) {
		// 文件上传
		if (!file.isEmpty()) {
			//String path = "d:/pic/";// 文件上传地址
			String path=request.getSession().getServletContext().getRealPath("/")+"/pic/";
			
			// 为了防止文件重名,需要使用uuid改变文件的上传名称
			// 先获取文件的原始名称.
			// 1.jpg
			String oldFilename = file.getOriginalFilename();
			String filename = UUID.randomUUID() + oldFilename.substring(oldFilename.lastIndexOf("."));

			try {
				file.transferTo(new File(path + filename));// 把文件写入硬盘

				// 设置文章标题的图片
				article.setPicture(filename);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 文章的默认属性
		// 从session获取当前登录人的信息

		HttpSession session = request.getSession(false);
		if (null == session)
			return false;

		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());// 文章作者
		article.setStatus(0);// 未审核
		article.setDeleted(0);// 未删除
		article.setHits(0);// 点击量默认0
		article.setHot(0);// 默认非热门
		article.setCreated(new Date());
		article.setUpdated(new Date());
		article.setContentType(ArticleEnum.HTML.getCode());//文章类型
		// 保存文章
		return articleService.insertSelective(article) > 0;

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
	public boolean publishUpdate(HttpServletRequest request, ArticleWithBLOBs article, MultipartFile file) {
		// 文件上传
		if (!file.isEmpty()) {
			String path = "d:/pic/";// 文件上传地址
			// 为了防止文件重名,需要使用uuid改变文件的上传名称
			// 先获取文件的原始名称.
			// 1.jpg
			String oldFilename = file.getOriginalFilename();
			String filename = UUID.randomUUID() + oldFilename.substring(oldFilename.lastIndexOf("."));

			try {
				file.transferTo(new File(path + filename));// 把文件写入硬盘

				// 设置文章标题的图片
				article.setPicture(filename);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 文章的默认属性
		article.setUpdated(new Date());
		// 保存文章
		return articleService.updateByPrimaryKeySelective(article) > 0;

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
