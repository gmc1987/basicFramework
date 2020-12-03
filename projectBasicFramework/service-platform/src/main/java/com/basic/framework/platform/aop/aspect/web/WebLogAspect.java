/**
 * 
 */
package com.basic.framework.platform.aop.aspect.web;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author gmc
 * @see web请求日志切入
 */
@Aspect
@Component
public class WebLogAspect {

	private final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
	
	@Pointcut("execution(public * com.basic..controller..*.*(..))")
	public void weblog(){}
	
	@Before("weblog()")
	public void beforeLog(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("--------------------controller request begin--------------------");
        logger.info("controller---》请求地址---》URL: {}", request.getRequestURL().toString());
        logger.info("controller---》请求地址---》URL: {}", request.getRequestURI().toString());
        logger.info("controller---》请求方法---》HTTP_METHOD: {}", request.getMethod());
        logger.info("controller---》请求ip地址---》IP: {}", request.getRemoteAddr());
        logger.info("controller---》请求参数---》params: {}", joinPoint.getArgs());
        logger.info("controller---》请求类方法--》CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("controller---》参数---》ARGS : " + Arrays.toString(joinPoint.getArgs()));
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            logger.info("controller---》name: {},value: {}", name, request.getParameter(name));
        }
	}
	
    @AfterReturning(returning = "result", pointcut = "weblog()")
    public void doAfterReturning(Object result){
    	logger.info("controller---》Result: {}", result);
    	logger.info("--------------------controller request end--------------------");
    }

    @AfterThrowing(pointcut = "weblog()", throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        //目标方法名：
    	logger.info("controller---》method name: {}", joinPoint.getSignature().getName());
    	logger.error("发生异常，异常信息：{}", exception);
    }
	
}
