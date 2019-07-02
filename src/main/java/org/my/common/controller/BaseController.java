package org.my.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.my.common.interceptor.AuthenticationInterceptor;
import org.my.oa.sys.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 当前用户
	 */
	protected User currentUser;

	@JsonIgnore
	@XmlTransient
	public User getCurrentUser() {
		  HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// 取出鉴权时存入的登录用户Id
		Object object =  request.getAttribute(AuthenticationInterceptor.REQUEST_CURRENT_KEY);
		logger.info("getCurrentUser key:{}", object);
		if (object != null) {
			// 查询用户信息并返回
			User user = (User)  request.getAttribute(AuthenticationInterceptor.REQUEST_CURRENT_USER);
			if (user != null) {
				logger.info("{}", user);
				currentUser = user;
			}
		}
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * 添加Model消息
	 * 
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		model.addAttribute("message", sb.toString());
	}

}
