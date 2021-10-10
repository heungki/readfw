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
	    
	    // ���� ó�� ���۽ð�
	    start = System.currentTimeMillis();
	    allData = new DataSet(FrameworkUtil.params(joinPoint).get("param"));
	    logger.info("��û ��ü������ : " + allData.toString());	
	    
	    // ��ó��
	    try {
	    	// ������ ����
		    ValidationData.validationCommonData(allData);
		    ValidationData.validationBizData(allData);
		    
		    // ������ �и�	
		    commonData = new DataSet(allData.get("COMMONDATA"));
		    requestBizData = new DataSet(allData.get("BIZDATA"));
		    
		    
	    } catch(APIException e){
	    	// API ����
        	returnData = ResponseMessage.apiErrorResponseAssembly(commonData, e);
	    	
	    	return returnData;
        } catch(Exception e){
        	// ��Ÿ ����
        	returnData = ResponseMessage.errorResponseAssembly(commonData);
	    	
	    	return returnData;
        } finally {
        	// ��û�α� ����
		    url = request.getRequestURI();
		    frameworkController.insertRequestLog(url, allData);
	  	} 
	    
        // ���� ó��
		commonData.put("REQUEST_TYPE", "R");
	    try {
	        
	        // �����Ͻ� ���� ������
	        Object[] bizData_args = {requestBizData};  
	    	
	    	// ���� ����
	    	responseBizObject = joinPoint.proceed(bizData_args);

	    	// ���䵥���� ����
	    	returnData = ResponseMessage.responseAssembly(commonData, responseBizObject);
	    	
	    } catch(APIException e){
	    	// API ����
	    	returnData = ResponseMessage.apiErrorResponseAssembly(commonData, e);

        } catch(Exception e){
        	// ��Ÿ ����
        	returnData = ResponseMessage.errorResponseAssembly(commonData);
	    	
        } finally {
	    	// ���� ó�� ����ð�
	    	long end = System.currentTimeMillis();
	    	long processTime = end - start;	    	
	    	
	    	// ����α� ����
		    frameworkController.insertReponseLog(url, returnData, processTime);
	  	}
	    
	    logger.info("���� ��ü������ : " + returnData.toString());	

	    
	    // ����
	    return returnData;
	}
	
	

}