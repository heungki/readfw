package com.readfw.controller;

import com.readfw.fw.DataSet;
import com.readfw.fw.DataSetList;
import com.readfw.model.Customer;
import com.readfw.model.CustomerForm;
import com.readfw.service.AccountService;
import com.readfw.service.CustomerService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }
    
    @GetMapping("/accountlistview")
    public String accountlist(Model model){
    			
		DataSetList result = accountService.getAccountList();
        model.addAttribute("accountlist", result);
        return "accountlist.html";
    }
    
    @GetMapping("/accounttestview")
    public String accounttest(Model model){
    	
        return "accounttest.html";
    }
    
    @GetMapping("/accounttest2view")
    public String accounttest2(Model model){
    	
        return "accounttest2.html";
    }
    
}
