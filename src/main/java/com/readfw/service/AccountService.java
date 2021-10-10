package com.readfw.service;

import com.readfw.fw.DataSet;
import com.readfw.fw.DataSetList;
import com.readfw.model.Customer;
import com.readfw.repository.AccountRepository;
import com.readfw.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
    
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    
    public DataSet getAccount(DataSet input){
    	
    	return accountRepository.getAccount(input);
    }
    
    public DataSetList getAccountByName(DataSet input){
    	
    	return accountRepository.getAccountByName(input);
    }
    
    public DataSetList getAccountList(){
    	
    	return accountRepository.getAccountList();
    }

}