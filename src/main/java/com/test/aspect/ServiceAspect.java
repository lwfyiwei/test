package com.test.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Aspect
@Component
public class ServiceAspect {
	
	private ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut("execution(public * com.test.service..*.*(..))")
	public void serviceLog(){}

	@Before("serviceLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		String type = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		log.debug(type +" ***service parameter*** " +Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(returning = "ret", pointcut = "serviceLog()")
	public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
		String type = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		log.debug(type +" ***service return value*** " + JSON.toJSONString(ret));
		log.debug(type +" ***service spend time*** " + (System.currentTimeMillis() - startTime.get())+"ms");
		
	}
}
