/**
 * 
 */
package com.revolver.survey.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

/**
 * @author REVOLVER2016年3月4日
 * 
 */
public class SurveyCacheKeyGenerate implements KeyGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.cache.interceptor.KeyGenerator#generate(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object generate(Object target, Method method, Object... params) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(target.getClass().getName()).append(".");
		sb.append(method.getName()).append(".");
		
		if(params!=null){
			for (Object object : params) {
				sb.append(object).append(".");
			}
		}
		
		return sb.substring(0, sb.lastIndexOf("."));
	}

}
