package com.readfw.fw;

public class APIException extends RuntimeException {
	 
    private String ERR_CODE = APIResult.ERROR.getCode();
    private String ERR_MESSAGE = APIResult.ERROR.getMessage();
 
    public APIException()  {
        super();
    }
 
    public APIException(String errDesc) {
        super(errDesc);
    }
 
    public APIException(String code, String errDesc) {
        super(errDesc);
        this.ERR_CODE = code;
    }
    
    public APIException(APIResult e){
    	this.ERR_MESSAGE = e.getMessage(); 
        this.ERR_CODE = e.getCode(); 
    }
 
    public String getErrorMessage() {
        return this.ERR_MESSAGE;
    }
 
    public String getErrCode() {
        return this.ERR_CODE;
    }  
 
}