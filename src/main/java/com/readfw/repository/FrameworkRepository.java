package com.readfw.repository;

import com.readfw.fw.CommonQuery;
import com.readfw.fw.DataSet;
import com.readfw.fw.DataSetList;
import com.readfw.fw.frameworkQuery;
import com.readfw.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class FrameworkRepository {
    
    public void insertLog(DataSet input) {
    	
    	String sql = "insert into logdata values(:GUID||:REQUEST_TYPE||LPAD(CEILING(TO_CHAR(ROUND(RAND()*100,0))),3,'0'),"
    			+ ":URL,"
    			+ ":TRX_DATETIME,"
    			+ "sysdate,"
    			+ ":GUID,"
    			+ ":REQUEST_TYPE,"
    			+ ":RESPONSE_TYPE,"
    			+ ":BIZDATA,"
    			+ ":PROCESS_TIME)";    	
    	
    	frameworkQuery.queryUpdate(sql, input);
        
    }
    
    public DataSetList getLogList() {
    	String sql = "select log_id,"
    			+ " url,"
    			+ " trx_datetime,"
    			+ " to_char(log_datetime,'yyyy/MM/dd hh24:mi:ss') as log_datetime,"
    			+ " guid,"
    			+ " request_type,"
    			+ " response_type,"
    			+ " bizdata,"
    			+ " process_time"
    			+ " from logdata order by log_datetime";
    	
    	DataSetList result = frameworkQuery.queryForList(sql);
    	
        return result;
    }
}
