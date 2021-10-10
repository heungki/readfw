package com.readfw.fw;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.readfw.aspect.FrameworkAspect;

public class ValidationData {
	private static Logger logger = LoggerFactory.getLogger(ValidationData.class);

	// ���뵥���� ����
	public static void validationCommonData(DataSet allData) {
		
		try {
			
			// ���뵥���� ����
			if(!allData.containsKey("COMMONDATA")) {
				logger.error("���뵥���� ������ ���� : " + allData.toString());
				throw new APIException(APIResult.VALIDATIONERROR);
			}
			
			DataSet commonData = new DataSet(allData.get("COMMONDATA"));
						
			// ���뵥���� �׸� ����
			if(!commonData.containsKey("GUID")
					|| !commonData.containsKey("TRX_DATETIME")
					|| !commonData.containsKey("REQUEST_TYPE")) {
				logger.error("���뵥���� �׸� ���� : " + commonData.toString());
				throw new APIException(APIResult.VALIDATIONERROR);
			}
			
			// ���뵥���� ���� ����
			if(commonData.getString("GUID").length() != 31
					|| commonData.getString("TRX_DATETIME").length() != 14
					|| commonData.getString("REQUEST_TYPE").length() != 1) {
				logger.error("���뵥���� ���� ���� : " + commonData.toString());
				throw new APIException(APIResult.VALIDATIONERROR);
			}
			
		} catch(Exception e){
			logger.error("���뵥���� ���� : " + allData.toString());
			throw new APIException(APIResult.VALIDATIONERROR);
		}
	}
	
	// ���������� ����
	public static boolean validationBizData(DataSet allData) {
		boolean result = true;
		
		try {
			if(!allData.containsKey("BIZDATA")) {
				logger.error("���������� ������ ���� : " + allData.toString());
				throw new APIException(APIResult.VALIDATIONERROR);
			}
			
		} catch(Exception e){
			logger.error("���������� ���� : " + allData.toString());
			throw new APIException(APIResult.VALIDATIONERROR);
		}
		return result;
	}

}
