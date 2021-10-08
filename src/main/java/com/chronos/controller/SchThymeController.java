package com.chronos.controller;

import com.chronos.model.Schedule;
import com.chronos.service.CustomerService;
import com.chronos.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SchThymeController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CustomerService customerService;
    @GetMapping("/displaySchedule")//请求显示日程列表
    public String findAllSch(Model model) {
        model.addAttribute("schList", scheduleService.findAllSchedule());
        return "/detail/ordinary/displaySchedule";
    }
    @GetMapping("/insertSchedule")//请求添加日程页面
    public String insertSch(Model model) {
        return "/detail/ordinary/insertSchedule";
    }
    @GetMapping("/saveSchedule")//保存日程信息
    public String saveSch(Schedule schedule , String username) {
        schedule.setaCustomer(customerService.findByUserName(username));
        scheduleService.saveSch(schedule);
        return "redirect:/displaySchedule";
    }
    @GetMapping("/updateSchedule")//更新日程信息
    public String updateSchedule(Integer id, Schedule schedule, String username) {
        schedule.setaCustomer(customerService.findByUserName(username));
        scheduleService.updateSch(schedule);
        return "redirect:/displaySchedule";
    }
    @GetMapping("/deleteSchedule")//删除日程信息
    public String deleteSchedule(Integer id) {
        scheduleService.deleteSch(id);
        return "redirect:/displaySchedule";
    }
    @GetMapping("/editSchedule")//编辑日程信息
    public String editSchedule(Integer id,Model model) {
        model .addAttribute("schedule",scheduleService.findById(id));
        return "/detail/ordinary/editSchedule";
    }
}

