package com.readfw.service;

import com.readfw.fw.DataSet;
import com.readfw.fw.DataSetList;
import com.readfw.model.Customer;
import com.readfw.repository.CustomerRepository;
import com.readfw.repository.FrameworkRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrameworkService {

	private final FrameworkRepository frameworkRepository;
    
    @Autowired
    public FrameworkService(FrameworkRepository frameworkRepository){
        this.frameworkRepository = frameworkRepository;
    }

    public void insertLog(DataSet input){
       
        frameworkRepository.insertLog(input);
    }
    
    public DataSetList getLogList() {
    	return frameworkRepository.getLogList();
    }

}