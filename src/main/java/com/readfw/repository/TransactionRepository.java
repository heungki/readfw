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
public class TransactionRepository {
	private static Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public DataSet getTransaction(DataSet input) {

    	String sql = "select trankey, accountnum, to_char(trantime,'yyyy/MM/dd hh24:mi:ss') as trantime, trantype, amount, sender from transaction where trankey = :TRANKEY";
    	logger.info("input : " + input);
    	logger.info("sql : " + sql);
    	DataSet result = CommonQuery.queryForSingle(sql, input);
    	
        return result;
    }
    
    public DataSetList getTransactionByName(DataSet input) {
    		
    	String sql = "select a.trankey, a.accountnum, to_char(a.trantime,'yyyy/MM/dd hh24:mi:ss') as trantime, a.trantype, a.amount, a.sender"
    			+ ", b.balance, b.accountnum, c.name"
    			+ " from transaction a, accountinfo b, customerinfo c "
    			+ "where a.accountnum = b.accountnum "
    			+ "and b.customernum = c.customernum "
    			+ "and c.name= :NAME";
    	logger.info("input : " + input);
    	logger.info("sql : " + sql);
    	DataSetList result = CommonQuery.queryForList(sql, input);
    	
        return result;
    }
    
    public DataSetList getTransactionByAccountNum(DataSet input) {
		
    	String sql = "select a.trankey, a.accountnum, to_char(a.trantime,'yyyy/MM/dd hh24:mi:ss') as trantime, a.trantype, a.amount, a.sender"
    			+ ", b.balance, b.accountnum, c.name"
    			+ " from transaction a, accountinfo b, customerinfo c "
    			+ "where a.accountnum = b.accountnum "
    			+ "and b.customernum = c.customernum "
    			+ "and b.accountnum= :ACCOUNTNUM";
    	logger.info("input : " + input);
    	logger.info("sql : " + sql);
    	DataSetList result = CommonQuery.queryForList(sql, input);
    	
        return result;
    }
    
    public DataSetList getTransactionList() {
    	String sql = "select trankey,"
    			+ " accountnum,"
    			+ " to_char(trantime,'yyyy/MM/dd hh24:mi:ss') as trantime,"
    			+ " trantype,"
    			+ " amount,"
    			+ " sender"
    			+ " from transaction order by trankey";
    	
    	DataSetList result = CommonQuery.queryForList(sql);
    	
        return result;
    }
    
}
