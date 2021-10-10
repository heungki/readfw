package com.readfw.fw.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.readfw.fw.APIException;
import com.readfw.fw.APIResult;
import com.readfw.fw.DataSet;

public class FrameworkUtil {

	// 파라미터 파싱
	public static Map params(JoinPoint joinPoint) {
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		String[] parameterNames = codeSignature.getParameterNames();
		Object[] args = joinPoint.getArgs();
		Map<String, Object> params = new HashMap<>();
		
		for (int i = 0; i < parameterNames.length; i++) {
			params.put(parameterNames[i], args[i]);
		}

		return params;
	}
		
	// 거래시간
	public static String getTrxDateTime() {
    	LocalDateTime now = LocalDateTime.now();
    	String trx_datetime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		
    	return trx_datetime;
	}
	
	
}
