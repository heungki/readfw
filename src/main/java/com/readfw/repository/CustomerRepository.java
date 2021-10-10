package com.readfw.repository;

import com.readfw.aspect.FrameworkAspect;
import com.readfw.fw.CommonQuery;
import com.readfw.fw.DataSet;
import com.readfw.fw.DataSetList;
import com.readfw.model.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CustomerRepository {
	private static Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    @Autowired
    public CustomerRepository(){
    }
    
    public DataSet getCustomer(DataSet input) {
    		
    	String sql = "select customernum, name from customerinfo where customernum = :CUSTOMERNUM";
    	logger.info("input : " + input);
    	logger.info("sql : " + sql);
    	DataSet result = CommonQuery.queryForSingle(sql, input);
    	
        return result;
    }
    
    public DataSet getCustomerByName(DataSet input) {
    	
    	String sql = "select customernum, name from customerinfo where name = :NAME";
    	logger.info("input : " + input);
    	logger.info("sql : " + sql);
    	DataSet result = CommonQuery.queryForSingle(sql, input);
    	
        return result;
    }
    
    public DataSetList getCustomerList() {
    	String sql = "select customernum, name from customerinfo";
    	
    	DataSetList result = CommonQuery.queryForList(sql);
    	
        return result;
    }
    
}
