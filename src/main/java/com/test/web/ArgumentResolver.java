package com.test.web;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * <p>
 * Title: ArgumentResolver
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:BrightOil
 * </p>
 * 
 * @author tz
 * @date 2017年7月27日
 */
public class ArgumentResolver implements HandlerMethodArgumentResolver {
	private final static Logger logger = LogManager.getLogger(ArgumentResolver.class);

	public boolean supportsParameter(MethodParameter parameter) {
		return true;
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		logger.info("ArgumentResolver resolveArgument");
		String pvalue= webRequest.getParameter(parameter.getParameterName());
		if (pvalue!=null){
			if (parameter.getParameterType().equals(String.class)) {
				return pvalue;
			} else if (parameter.getParameterType().equals(Integer.class)) {
				return Integer.parseInt(pvalue); 
			} else if (parameter.getParameterType().equals(Long.class)) {
				return Long.parseLong(pvalue);
			} else if (parameter.getParameterType().equals(BigDecimal.class)) {
				return new BigDecimal(pvalue);
			} else if (parameter.getParameterType().equals(Boolean.class)) {
				return "true".equals(pvalue);
			} else return null;
		} else {
			Object resultObj= parameter.getParameterType().newInstance();
			Map<String, String[]> parm_map= webRequest.getParameterMap();
			Field[] fields=resultObj.getClass().getDeclaredFields();
			for(Field field:fields){
				field.setAccessible(true);  //实体属性为private，此处必须写上
				String[] fieldValue= parm_map.get(field.getName());
				if (fieldValue!=null && fieldValue.length>0){
					if (field.getType().equals(String.class)) {
						field.set(resultObj, fieldValue[0]);
					} else if (field.getType().equals(Integer.class)) {
						field.set(resultObj, Integer.valueOf(fieldValue[0]));
					} else if (field.getType().equals(Long.class)) {
						field.set(resultObj, Long.valueOf(fieldValue[0]));
					} else if (field.getType().equals(BigDecimal.class)) {
						field.set(resultObj, new BigDecimal(fieldValue[0]));
					} else if (field.getType().equals(Boolean.class)) {
						field.set(resultObj, "true".equals(fieldValue[0]));
					}
				}  
			}
			return resultObj;
		}
	}


}