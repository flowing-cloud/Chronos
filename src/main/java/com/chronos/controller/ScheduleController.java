package com.chronos.controller;

import com.chronos.model.Schedule;
import com.chronos.repository.CustomerRepository;
import com.chronos.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/findsch/{id}")
    public Schedule getSchedule(@PathVariable("id") Integer id){
        return scheduleRepository.findById(id).get();
    }
    @GetMapping("/deletesch/{id}")
    public String delSchedule(@PathVariable("id") Integer id){
        scheduleRepository.deleteById(id);
        return "已删除！ ";
    }
    @GetMapping("/insertsch/{username}")
    public Schedule insertSchedule(Schedule schedule, @PathVariable("username") String username){
        schedule.setaCustomer(customerRepository.findByUsername(username));
        return scheduleRepository.save(schedule);
    }
    @RequestMapping(value="/allevents", method= RequestMethod.GET)
    public List<Schedule> allEvents() {
        return scheduleRepository.findAll();
    }
}



