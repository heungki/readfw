package com.readfw.controller;

import com.readfw.fw.DataSet;
import com.readfw.fw.DataSetList;
import com.readfw.model.Customer;
import com.readfw.model.CustomerForm;
import com.readfw.service.AccountService;
import com.readfw.service.CustomerService;
import com.readfw.service.TransactionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }
    
    @GetMapping("/transactionlistview")
    public String transactionlist(Model model){
    			
		DataSetList result = transactionService.getTransactionList();
        model.addAttribute("transactionlist", result);
        return "transactionlist.html";
    }
    
    @GetMapping("/transactiontestview")
    public String transactiontest(Model model){
		
        return "transactiontest.html";
    }
    
    @GetMapping("/transactiontest2view")
    public String transactiontest2(Model model){
		
        return "transactiontest2.html";
    }
    
    
}
