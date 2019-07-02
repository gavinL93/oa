package org.my.oa.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.my.oa.sys.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录拦截器 
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/** 拦截到用户的请求了 */
		String requestUrl = request.getRequestURL().toString();
        /** */
		logger.info("requestUrl:"+requestUrl);
		/** 判断session是否存在用户,如果存在说明用户已经登录了,应该放行 */
		User user = (User) request.getSession().getAttribute(OaContants.USER_SESSION);
		if(user!=null){
		    logger.info("requestUrl:"+requestUrl+"->被放行！");
			/** 当前请求：每个请求是否都是一个线程   */
			UserHolder.addCurrentUser(user);
			return true;
		}else{
			// 重定向 
			response.sendRedirect(request.getContextPath()+"/oa/login");
			logger.info("requestUrl:"+requestUrl+"->被拦截！");
			return false;
		}
	}
	
	

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		UserHolder.removeCurrentUser();
	}


	
    
	
}
