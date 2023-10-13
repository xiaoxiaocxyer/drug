package cn.tedu.drug.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
/**
 * 登录拦截器
 * @author 
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if( session.getAttribute("user") == null && !request.getRequestURL().toString().endsWith(".jpg")){
			response.sendRedirect("/login.html");
			return false;	//重定向到登录页面
		}else{
			return true;
		}
	}

}
