package com.test.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Aspect
@Component
public class ControllerAspect {
	
	private ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut("execution(public * com.test.controller..*.*(..))")
	public void webLog(){}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map<String, String[]> para = request.getParameterMap();
        String paraJson = para != null ? JSON.toJSONString(para) : "";
        String type = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		log.debug(type +" ***controller parameter*** " +paraJson);
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
		String type = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		log.debug(type +" ***controller return value*** " + JSON.toJSONString(ret));
		log.debug(type +" ***controller spend time*** " + (System.currentTimeMillis() - startTime.get())+"ms");
		
	}

}
