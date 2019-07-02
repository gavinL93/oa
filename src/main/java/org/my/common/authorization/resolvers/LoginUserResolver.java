package org.my.common.authorization.resolvers;

import org.my.common.authorization.annotation.LoginUser;
import org.my.oa.sys.domain.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginUserResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 如果该参数注解有@Logined
		// 如果该参数的类型为User
		if (parameter.getParameterAnnotation(LoginUser.class) != null && parameter.getParameterType() == User.class) {// 支持解析该参数
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter p, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		return webRequest.getAttribute(p.getParameterName(), NativeWebRequest.SCOPE_SESSION);
	}
}