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

@RestController
public class AccountRestController {
	private static Logger logger = LoggerFactory.getLogger(AccountRestController.class);
	
	@Autowired
	private AccountService accountService;
	@Autowired
    private CustomerService customerService;
	
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public Object account(@RequestBody Object param){
		DataSet input = new DataSet(param);
		
		DataSet result = accountService.getAccount(input);
		return result;
	}
	
	@RequestMapping(value = "/accountbyname", method = RequestMethod.POST)
	public Object accountbyname(@RequestBody Object param){
		DataSet input = new DataSet(param);
		
		customerService.getCustomerByName(input);
		
		DataSetList result = accountService.getAccountByName(input);
		return result;
	}
	
	@RequestMapping(value = "/accountlist", method = RequestMethod.POST)
	public Object accountList(@RequestBody Object param){
		DataSetList result = accountService.getAccountList();
		return result;
	}
}
