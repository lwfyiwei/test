/**
 * ScriptFilter.java
 * 
 * Version 1.0
 * 
 * 2013-7-23
 * 
 * Copyright www.wangjiu.com
 */
package com.test.web;

import java.util.Map;


/**
* <p>Title: CharacterFilter</p>
* <p>Description: 拦截器防止xss攻击</p>
* <p>Company:BrightOil</p> 
* @author tz
* @date 2017年7月20日
*/
public class CharacterFilter {

	public static String validScriptFilter(Map<String, String> param) {
		for(String key : param.keySet()) {
//			System.out.println(key);
			if(param.get(key).toLowerCase().indexOf("<") != -1) return key;
			if(param.get(key).toLowerCase().indexOf(">") != -1) return key;
			if(param.get(key).toUpperCase().indexOf("%3C") != -1) return key;
			if(param.get(key).toUpperCase().indexOf("%3E") != -1) return key;
			if(param.get(key).toLowerCase().indexOf("script") != -1) return key; 
			if(param.get(key).toLowerCase().indexOf("javascript:") != -1) return key;
			if(param.get(key).toLowerCase().indexOf("alert") != -1) return key;
			if(param.get(key).toLowerCase().indexOf("onerror") != -1) return key;
		}
		return null;
	}

	
}
