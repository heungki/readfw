package com.readfw.repository;

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
public class AccountRepository {
	private static Logger logger = LoggerFactory.getLogger(AccountRepository.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public DataSet getAccount(DataSet input) {

    	String sql = "select accountnum, customernum, balance from accountinfo where accountnum = :ACCOUNTNUM";
    	logger.info("input : " + input);
    	logger.info("sql : " + sql);
    	DataSet result = CommonQuery.queryForSingle(sql, input);
    	
        return result;
    }
    
    public DataSetList getAccountByName(DataSet input) {
    		
    	String sql = "select a.accountnum, a.customernum, a.balance, b.customernum, b.name from accountinfo a, customerinfo b "
    			+ "where a.customernum = b.customernum "
    			+ "and b.name= :NAME";
    	logger.info("input : " + input);
    	logger.info("sql : " + sql);
    	DataSetList result = CommonQuery.queryForList(sql, input);
    	
        return result;
    }
    
    public DataSetList getAccountList() {
    	String sql = "select accountnum, customernum, balance from accountinfo";
    	
    	DataSetList result = CommonQuery.queryForList(sql);
    	
        return result;
    }
    
}
