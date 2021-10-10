package com.readfw.service;

import com.readfw.fw.DataSet;
import com.readfw.fw.DataSetList;
import com.readfw.model.Customer;
import com.readfw.repository.AccountRepository;
import com.readfw.repository.CustomerRepository;
import com.readfw.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
    
    @Autowired
    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    
    public DataSet getTransaction(DataSet input){
    	
    	return transactionRepository.getTransaction(input);
    }
    
    public DataSetList getTransactionByName(DataSet input){
    	
    	return transactionRepository.getTransactionByName(input);
    }
    
    public DataSetList getTransactionByAccountNum(DataSet input){
    	
    	return transactionRepository.getTransactionByAccountNum(input);
    }
    
    public DataSetList getTransactionList(){
    	
    	return transactionRepository.getTransactionList();
    }

}