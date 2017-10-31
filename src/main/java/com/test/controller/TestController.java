package com.test.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController{
	
	@Autowired
	TestService testService;
	
	@Autowired
	Environment env;
	
	@RequestMapping(value = "/data", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
	public Map<String,Object> getTest(
			BigDecimal total_amount,
			String station_bn){
		
		Map<String, Object> params = new HashMap<>();
		params.put("para1", total_amount);
		params.put("para2", station_bn);
		Map<String, Object> rst = testService.getTestData(params);
		System.out.println(env.getProperty("server.port"));
		return rst;
	}

}
