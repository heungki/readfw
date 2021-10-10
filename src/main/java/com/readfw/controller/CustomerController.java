package com.readfw.controller;

import com.readfw.fw.DataSet;
import com.readfw.fw.DataSetList;
import com.readfw.model.Customer;
import com.readfw.model.CustomerForm;
import com.readfw.service.CustomerService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    
    @GetMapping("/customerlistview")
    public String customerlist(Model model){
    			
		DataSetList result = customerService.getCustomerList();
        model.addAttribute("customerlist", result);
        return "customerlist.html";
    }
    
    @GetMapping("/customertestview")
    public String customertest(Model model){
    			
		DataSetList result = customerService.getCustomerList();
        model.addAttribute("customerlist", result);
        return "customertest.html";
    }
    
    
}
