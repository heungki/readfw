package com.readfw.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.readfw.fw.DataSet;
import com.readfw.fw.DataSetList;
import com.readfw.model.Customer;
import com.readfw.service.CustomerService;
import com.readfw.service.FrameworkService;

@Controller
public class FrameworkController {
	private static Logger logger = LoggerFactory.getLogger(FrameworkController.class);
	
	@Autowired
	private FrameworkService frameworkService;
	
	
	@GetMapping("/transactionlogview")
    public String loglist(Model model){		
		DataSetList result = frameworkService.getLogList();
        model.addAttribute("loglist", result);
        return "loglist.html";
    }	
	
	@GetMapping("/manualjsontestview")
    public String manualjsontest(Model model){	
		
        return "manualjsontest.html";
    }	
	
	@SuppressWarnings("unchecked")
	public void insertRequestLog(String url , DataSet allData){
	    
	    DataSet logData = new DataSet(allData.get("COMMONDATA"));
	    logData.put("URL", url);
	    logData.put("BIZDATA", allData.get("BIZDATA").toString());
	    logData.put("RESPONSE_TYPE", null);
	    logData.put("PROCESS_TIME", null);
	   	    
	    try {
	    	frameworkService.insertLog(logData);
	    }catch (Exception e) {
	    	logger.error("요청데이터 로그적재 실패 " + e);
	    }
	        
	}
	
	@SuppressWarnings("unchecked")
	public void insertReponseLog(String url , DataSet returnData, long processTime){
		
	    DataSet logData = new DataSet(returnData.get("COMMONDATA"));	
	    logData.put("URL", url);
	    logData.put("BIZDATA", returnData.get("BIZDATA").toString());		
		logData.put("PROCESS_TIME", processTime);
		
		if(logData.getString("REQUEST_TYPE").equals("")) {
    		logData.put("REQUEST_TYPE", "R");
    	}
    	if(logData.getString("RESPONSE_TYPE").equals("")) {
    		logData.put("RESPONSE_TYPE", "NM");
    	}
    	
    	try {
	    	frameworkService.insertLog(logData);
	    }catch (Exception e) {
	    	logger.error("응답데이터 로그적재 실패 " + e);
	    }
	        
	}

	
}

