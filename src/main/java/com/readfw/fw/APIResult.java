package com.readfw.fw;

public enum APIResult {
    SUCCESS ("NM", "success"),
    QUERYERROR ("EQ", "query fail"),
    QUERYNODATAERROR ("EN", "query no data fail"),
    VALIDATIONERROR ("EV", "validation fail"),
    ERROR ("ER", "fail"); 
    
    private String code;
    private String message;
    
    private APIResult(String _code, String _message) {
        this.code = _code;
        this.message = _message;
    }
    
    public static APIResult search(String code) {
        for (APIResult status : APIResult.values()) {
            if(status.getCode() == code) {
                return status;
            }
        }
        return null;
    }
  
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
