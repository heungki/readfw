package com.readfw.fw;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class frameworkQuery {
	private static Logger logger = LoggerFactory.getLogger(CommonQuery.class);

	static EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
	static NamedParameterJdbcTemplate jdbcTemplate =new NamedParameterJdbcTemplate(db);
	
	public static DataSet queryForSingle(String sql, DataSet input) {    	
		DataSet result = new DataSet();
		try {
	    	Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql, input);
	    	
	    	for (String key : resultMap.keySet()) {
	    		String value = (String) resultMap.get(key);
	    		result.put(key, value);
	    	    //System.out.println("[key]:" + key + ", [value]:" + value);
	    	}    
		}catch(EmptyResultDataAccessException e) {
			logger.error("쿼리 수행 오류(queryForSingle) 데이터미존재" + e);
			throw new APIException(APIResult.QUERYNODATAERROR);
		}catch(Exception e) {
			logger.error("쿼리 수행 오류(queryForSingle) " + e);
			throw new APIException(APIResult.QUERYERROR);
		}
    	
        return result;
    }
	
	public static DataSet queryUpdate(String sql, DataSet input) {    	
		DataSet result = new DataSet();
		try {
			jdbcTemplate.update(sql, input);
		}catch(EmptyResultDataAccessException e) {
			logger.error("쿼리 수행 오류(queryForSingle) 데이터미존재" + e);
			throw new APIException(APIResult.QUERYNODATAERROR);
		}catch(Exception e) {
			logger.error("쿼리 수행 오류(queryForSingle) " + e);
			throw new APIException(APIResult.QUERYERROR);
		}
    	
        return result;
    }
	
	public static DataSetList queryForList(String sql) {    	
		return queryForList(sql, new DataSet());
	}
	
	public static DataSetList queryForList(String sql, DataSet input) {    	
		DataSetList result = new DataSetList();
		try {
			List<Map<String, Object>> resultMap = jdbcTemplate.queryForList(sql, input);
	    	
	    	int i=0;
	    	for (Map<String, Object> row : resultMap) {
	    		DataSet rowDataSet = new DataSet();

	    		for (String key : row.keySet()) {
	        		String value = (String) row.get(key);
	        		rowDataSet.put(key, value);
	        	    //System.out.println("[key]:" + key + ", [value]:" + value);
	        	}    
	        	
	    		result.add(rowDataSet);
	        }
		}catch(Exception e) {
			logger.error("쿼리 수행 오류(queryForList) " + e);
			throw new APIException(APIResult.QUERYERROR);
		}
    	
        return result;
    }
}
