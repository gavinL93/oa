package org.my.common.config;

import java.util.Arrays;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * 高并发的情况下，服务端，每次请求调用的时候，代码都自动加上一个唯一的id，日志流pattern配置加[%X{requestId}]都加上了这个唯一的id，这样再查找日志的时候，将会很方便。
 *
 */
@Aspect
@Configuration
public class SpringAOP {

	private static final Logger logger = LoggerFactory.getLogger(SpringAOP.class);

	/**
	 * 定义切点Pointcut execution() 表达式的主体；<br/>
	 * 第一个“*”符号表示返回值的类型任意；；<br/>
	 * cn.ybt.module表示AOP所切的服务的包名，即，需要进行横切的业务类；<br/>
	 * 包名后面的“..” 表示当前包及子包；<br/>
	 * 第二个“*”表示类名，*即所有类；<br/>
	 * .*(..) 表示任何方法名，括号表示参数，两个点表示任何参数类型
	 */
	@Pointcut("execution(*  cn.ybt.module..controller.*.*(..))")
	public void executionService() {
	}

	/**
	 * 方法调用之前调用
	 * 
	 * @param joinPoint
	 */
	@Before(value = "executionService()")
	public void doBefore(JoinPoint joinPoint) {
		String requestId = String.valueOf(UUID.randomUUID());
		MDC.put("requestId", requestId);
		logger.info("=====>@Before：请求参数为：{}", Arrays.toString(joinPoint.getArgs()));
	}

	/**
	 * 方法之后调用
	 * 
	 * @param joinPoint
	 * @param returnValue 方法返回值
	 */
	@AfterReturning(pointcut = "executionService()", returning = "returnValue")
	public void doAfterReturning(JoinPoint joinPoint, Object returnValue) {
		logger.info("=====>@AfterReturning：响应参数为：{}", returnValue);
		// 处理完请求，返回内容
		MDC.clear();
	}
}