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
import com.readfw.service.CustomerService;

@RestController
public class CustomerRestController {
	private static Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public Object customer(@RequestBody Object param){
		DataSet input = new DataSet(param);
		
		DataSet result = customerService.getCustomer(input);
		return result;
	}
	
	@RequestMapping(value = "/customerbyname", method = RequestMethod.POST)
	public Object customerbyname(@RequestBody Object param){
		DataSet input = new DataSet(param);
		
		DataSet result = customerService.getCustomerByName(input);
		return result;
	}
	
	@RequestMapping(value = "/customerlist", method = RequestMethod.POST)
	public Object cutomerList(@RequestBody Object param){
		DataSetList result = customerService.getCustomerList();
		return result;
	}
}
