package com.readfw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.readfw.fw.DataSet;
import com.readfw.fw.DataSetList;
import com.readfw.model.Customer;
import com.readfw.service.AccountService;
import com.readfw.service.CustomerService;
import com.readfw.service.TransactionService;

@RestController
public class TransactionRestController {
	private static Logger logger = LoggerFactory.getLogger(TransactionRestController.class);
	
	@Autowired
	private TransactionService transaction;
	@Autowired
    private CustomerService customerService;
	@Autowired
    private AccountService accountService;

	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	public Object transaction(@RequestBody Object param){
		DataSet input = new DataSet(param);
		
		DataSet result = transaction.getTransaction(input);
		return result;
	}
	
	@RequestMapping(value = "/transactionbyname", method = RequestMethod.POST)
	public Object transactionbyname(@RequestBody Object param){
		DataSet input = new DataSet(param);
		
		customerService.getCustomerByName(input);
		
		DataSetList result = transaction.getTransactionByName(input);
		return result;
	}
	
	@RequestMapping(value = "/transactionbyaccountnum", method = RequestMethod.POST)
	public Object transactionbyaccountnum(@RequestBody Object param){
		DataSet input = new DataSet(param);
		
		accountService.getAccount(input);
		
		DataSetList result = transaction.getTransactionByAccountNum(input);
		return result;
	}
	
	@RequestMapping(value = "/transactionlist", method = RequestMethod.POST)
	public Object transactionList(@RequestBody Object param){
		DataSetList result = transaction.getTransactionList();
		return result;
	}
}
