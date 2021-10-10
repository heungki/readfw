package com.readfw.fw.util;

import com.readfw.fw.APIException;
import com.readfw.fw.APIResult;
import com.readfw.fw.DataSet;

public class ResponseMessage {
	
	// 응답 조합
	@SuppressWarnings("unchecked")
	public static DataSet responseAssembly(DataSet commonData, Object responseBizObject) {
		DataSet errorResponse = new DataSet();
    	commonData.put("TRX_DATETIME", FrameworkUtil.getTrxDateTime());
        commonData.put("RESPONSE_TYPE", APIResult.SUCCESS.getCode()); 
        
    	errorResponse.put("COMMONDATA", commonData);
    	errorResponse.put("BIZDATA", responseBizObject);
		
		return errorResponse;
	}
	
	// 에러응답 조합
	@SuppressWarnings("unchecked")
	public static DataSet errorResponseAssembly(DataSet commonData) {
		DataSet errorResponse = new DataSet();
		commonData.put("TRX_DATETIME", FrameworkUtil.getTrxDateTime());
    	commonData.put("RESPONSE_TYPE", APIResult.ERROR.getCode());	
    	
		errorResponse.put("COMMONDATA", commonData);
    	
		DataSet errData = new DataSet();
    	errData.setString("ERROR_INFO", APIResult.ERROR.getMessage());
    	errorResponse.put("BIZDATA", errData);	
		
		return errorResponse;
	}
		
	
	// API 에러응답 조합
	@SuppressWarnings("unchecked")
	public static DataSet apiErrorResponseAssembly(DataSet commonData, APIException e) {
		DataSet errorResponse = new DataSet();
    	commonData.put("TRX_DATETIME", FrameworkUtil.getTrxDateTime());    		
    	commonData.put("RESPONSE_TYPE", e.getErrCode());

    	errorResponse.put("COMMONDATA", commonData);
    	
    	DataSet errData = new DataSet();
    	errData.setString("ERROR_INFO", e.getErrorMessage());
    	errorResponse.put("BIZDATA", errData);
		
		return errorResponse;
	}

}
