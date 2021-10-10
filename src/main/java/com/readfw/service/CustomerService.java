package com.readfw.service;

import com.readfw.fw.DataSet;
import com.readfw.fw.DataSetList;
import com.readfw.model.Customer;
import com.readfw.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    
    public DataSet getCustomer(DataSet input){
    	
    	return customerRepository.getCustomer(input);
    }
    
    public DataSet getCustomerByName(DataSet input){
    	
    	return customerRepository.getCustomerByName(input);
    }
    
    public DataSetList getCustomerList(){
    	
    	return customerRepository.getCustomerList();
    }

}