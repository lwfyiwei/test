package com.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.test.Main;

import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * Title: AuthInterceptor
 * </p>
 * <p>
 * Description: 拦截器对每一个请求的用户头部进行效验，验证通过将用户信息放入request.att
 * </p>
 * <p>
 * Company:BrightOil
 * </p>
 * 
 */

@Log4j2
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("AuthInterceptor preHandle....");
		String url = request.getRequestURI();
		log.info("AuthInterceptor preHandle url: "+url);
		return super.preHandle(request, response, handler);
	}
	
	

}
