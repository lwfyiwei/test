package com.test.web;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;


/**
 * <p>Title: CharacterValidInterceptors</p>
 * <p>Description: 拦截器防止xss攻击</p>
 * <p>Company:BrightOil</p> 
 */
public class CharacterValidInterceptors extends HandlerInterceptorAdapter  {
	private final static Logger logger = LogManager.getLogger(CharacterValidInterceptors.class);

	public static Map<String, String> getParameters(ServletRequest request) {
		Enumeration<String> paramNames = request.getParameterNames();
		Map<String, String> params = new TreeMap<String, String>();
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] values = request.getParameterValues(paramName);
			if (values == null || values.length == 0) {
			} else if (values.length > 1) {
				params.put(paramName,  values[0]);
			} else {
				params.put(paramName, values[0]);
			}
		}
		return params;
	}

	/**
	 * 执行方法前
	 */
	 @Override
	 public boolean preHandle(HttpServletRequest request,
			 HttpServletResponse response, Object handler) throws Exception {
		 logger.info("CharacterValidInterceptors preHandle...");
		 Map<String, String> param = getParameters(request);
		 String rst = CharacterFilter.validScriptFilter(param);
		 if (rst != null) {
			 logger.error("Request parameter containing SCRIPT injection");

			 response.setCharacterEncoding("utf8");
			 response.setContentType("application/json; charset=utf8");
			 response.setStatus(200);

			 PrintWriter writer = response.getWriter();
			 Map<String, Object> error = new HashMap<>();
			 writer.write(JSON.toJSONString(error));
			 writer.close();          

			 return false;
		 }
		 return super.preHandle(request, response, handler);
	 }

}
