package org.my.common.authorization.resolvers;

import org.my.common.authorization.annotation.CurrentUser;
import org.my.common.interceptor.AuthenticationInterceptor;
import org.my.oa.sys.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * 
 * @see cn.ybt.common.authorization.annotation.CurrentUser
 */
@Component
public class CurrentUserMethodArgumentResolver<T> implements HandlerMethodArgumentResolver {
	Logger logger = LoggerFactory.getLogger(CurrentUserMethodArgumentResolver.class);

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 如果参数类型是User并且有CurrentUser注解则支持
		return parameter.getParameterType().isAssignableFrom(User.class)
				&& parameter.hasParameterAnnotation(CurrentUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// 取出鉴权时存入的登录用户Id
		Object object = webRequest.getAttribute(AuthenticationInterceptor.REQUEST_CURRENT_KEY,
				RequestAttributes.SCOPE_REQUEST);
		logger.info("key:{}", object);
		if (object != null) {
			// 查询用户信息并返回
			User user = (User) webRequest.getAttribute(AuthenticationInterceptor.REQUEST_CURRENT_USER,
					RequestAttributes.SCOPE_REQUEST);
			if (user != null) {
				logger.info("{}", user);
				return user;
			}
			// 有key但是得不到用户，抛出异常
			throw new MissingServletRequestPartException(AuthenticationInterceptor.REQUEST_CURRENT_KEY);
		}
		// 没有key就直接返回null
		return null;
	}
}
