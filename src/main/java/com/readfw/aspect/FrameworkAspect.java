package com.readfw.aspect;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.readfw.controller.FrameworkController;
import com.readfw.fw.APIException;
import com.readfw.fw.APIResult;
import com.readfw.fw.DataSet;
import com.readfw.fw.ValidationData;
import com.readfw.fw.util.FrameworkUtil;
import com.readfw.fw.util.ResponseMessage;

@Aspect  
@Component 
public class FrameworkAspect {

	private static Logger logger = LoggerFactory.getLogger(FrameworkAspect.class);
		
	@Autowired
	private FrameworkController frameworkController;
	
	//@Pointcut("execution(public com.readfw.controller.*RestController.*(..))") 
	@Pointcut("execution(* com.readfw.controller.*RestController.*(..))")
	public void onRequest() { }
	
	@SuppressWarnings("unchecked")
	@Around("onRequest()")
	public Object doProcess(ProceedingJoinPoint joinPoint) throws Throwable {
	    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	    DataSet allData = new DataSet();
	    DataSet commonData = new DataSet();
	    DataSet requestBizData = new DataSet();
	    DataSet returnData = new DataSet();
	    Object responseBizObject = "";     
	    String url = "";
	    long start = 0;
	    
	    // 서비스 처리 시작시간
	    start = System.currentTimeMillis();
	    allData = new DataSet(FrameworkUtil.params(joinPoint).get("param"));
	    logger.info("요청 전체데이터 : " + allData.toString());	
	    
	    // 전처리
	    try {
	    	// 데이터 검증
		    ValidationData.validationCommonData(allData);
		    ValidationData.validationBizData(allData);
		    
		    // 데이터 분리	
		    commonData = new DataSet(allData.get("COMMONDATA"));
		    requestBizData = new DataSet(allData.get("BIZDATA"));
		    
		    
	    } catch(APIException e){
	    	// API 에러
        	returnData = ResponseMessage.apiErrorResponseAssembly(commonData, e);
	    	
	    	return returnData;
        } catch(Exception e){
        	// 기타 에러
        	returnData = ResponseMessage.errorResponseAssembly(commonData);
	    	
	    	return returnData;
        } finally {
        	// 요청로그 적재
		    url = request.getRequestURI();
		    frameworkController.insertRequestLog(url, allData);
	  	} 
	    
        // 서비스 처리
		commonData.put("REQUEST_TYPE", "R");
	    try {
	        
	        // 비지니스 전달 데이터
	        Object[] bizData_args = {requestBizData};  
	    	
	    	// 서비스 수행
	    	responseBizObject = joinPoint.proceed(bizData_args);

	    	// 응답데이터 세팅
	    	returnData = ResponseMessage.responseAssembly(commonData, responseBizObject);
	    	
	    } catch(APIException e){
	    	// API 에러
	    	returnData = ResponseMessage.apiErrorResponseAssembly(commonData, e);

        } catch(Exception e){
        	// 기타 에러
        	returnData = ResponseMessage.errorResponseAssembly(commonData);
	    	
        } finally {
	    	// 서비스 처리 종료시간
	    	long end = System.currentTimeMillis();
	    	long processTime = end - start;	    	
	    	
	    	// 응답로그 적재
		    frameworkController.insertReponseLog(url, returnData, processTime);
	  	}
	    
	    logger.info("응답 전체데이터 : " + returnData.toString());	

	    
	    // 응답
	    return returnData;
	}
	
	

}