package com.readfw.fw;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.readfw.aspect.FrameworkAspect;

public class ValidationData {
	private static Logger logger = LoggerFactory.getLogger(ValidationData.class);

	// 공통데이터 점검
	public static void validationCommonData(DataSet allData) {
		
		try {
			
			// 공통데이터 점검
			if(!allData.containsKey("COMMONDATA")) {
				logger.error("공통데이터 미존재 오류 : " + allData.toString());
				throw new APIException(APIResult.VALIDATIONERROR);
			}
			
			DataSet commonData = new DataSet(allData.get("COMMONDATA"));
						
			// 공통데이터 항목 점검
			if(!commonData.containsKey("GUID")
					|| !commonData.containsKey("TRX_DATETIME")
					|| !commonData.containsKey("REQUEST_TYPE")) {
				logger.error("공통데이터 항목 오류 : " + commonData.toString());
				throw new APIException(APIResult.VALIDATIONERROR);
			}
			
			// 공통데이터 길이 점검
			if(commonData.getString("GUID").length() != 31
					|| commonData.getString("TRX_DATETIME").length() != 14
					|| commonData.getString("REQUEST_TYPE").length() != 1) {
				logger.error("공통데이터 길이 오류 : " + commonData.toString());
				throw new APIException(APIResult.VALIDATIONERROR);
			}
			
		} catch(Exception e){
			logger.error("공통데이터 오류 : " + allData.toString());
			throw new APIException(APIResult.VALIDATIONERROR);
		}
	}
	
	// 업무데이터 점검
	public static boolean validationBizData(DataSet allData) {
		boolean result = true;
		
		try {
			if(!allData.containsKey("BIZDATA")) {
				logger.error("업무데이터 미존재 오류 : " + allData.toString());
				throw new APIException(APIResult.VALIDATIONERROR);
			}
			
		} catch(Exception e){
			logger.error("업무데이터 오류 : " + allData.toString());
			throw new APIException(APIResult.VALIDATIONERROR);
		}
		return result;
	}

}
