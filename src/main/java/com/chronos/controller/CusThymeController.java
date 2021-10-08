package com.chronos.controller;

import com.chronos.model.Customer;
import com.chronos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CusThymeController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/displayCustomer")//请求显示用户列表
    public String findAllCus(Model model) {
        model.addAttribute("customerList", customerService.findAllCustomer());
        return "/detail/privilege/displayCustomer";
    }
    @GetMapping("/insertCustomer")//请求添加用户页面
    public String insertCustomer(Model model) {
        return "/detail/privilege/insertCustomer";
    }
    @GetMapping("/saveCustomer")//保存用户信息
    public String saveCustomer(Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/displayCustomer";
    }
    @GetMapping("/updateCustomer")//更新用户信息
    public String updateCustomer(Integer id, Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/displayCustomer";
    }
    @GetMapping("/deleteCustomer")//删除用户信息
    public String deleteCustomer(Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/displayCustomer";
    }
    @GetMapping("/editCustomer")//编辑用户信息
    public String editCustomer(Integer id,Model model) {
        model.addAttribute("customer",customerService.findById(id));
        return "/detail/privilege/editCustomer";
    }
}


