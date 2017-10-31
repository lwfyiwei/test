package com.test.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BaseController {
	@ExceptionHandler
	public String exception(HttpServletRequest request, HttpServletResponse res, Exception e) {
		log.error(e);
		res.setContentType("application/json; charset=utf-8");
		res.setCharacterEncoding("UTF-8");
		
		Map<String, Object> rst = new HashMap<>();
		rst.put("code", "99999");
		rst.put("info", "system error");
		rst.put("data", new HashMap<String,Object>());
		
		String buffer = JSON.toJSONString(rst);
		OutputStream out;
		try {
			out = res.getOutputStream();
			out.write(buffer.getBytes("UTF-8"));
			out.flush();
			out.close();
		} catch (IOException e1) {
			log.error("AbstractRestController error params:e.message:{}"+e.getMessage()+e);
		}
		return null;
	}
	

}
