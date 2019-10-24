package com.bobo.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @ClassName:
 * @Description: 个人中心后台拦截器
 * @author: bobo
 * @date: 2019年10月日 上午9:58:13
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从session获取管理员对象

		HttpSession session = request.getSession(false);
		if (null != session) {
			Object object = session.getAttribute("user");
			if (null != object) {
				return true;// 放行
			}

		}
		// 没有登录.则转发到登录页面

		request.setAttribute("error", "请登陆后再试");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);

		return false;

	}

}
