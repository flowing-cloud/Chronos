package com.chronos.service;


import com.chronos.model.Schedule;
import com.chronos.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    //按 id 号升序返回日程列表
    public List<Schedule> findAllSchedule(){
        return scheduleRepository.findAllByOrderById();
    }
    //按 id 号返回日程对象
    public Schedule findById(Integer id){
        return scheduleRepository.findById(id).get();
    }
    //保存日程对象
    public boolean saveSch(Schedule schedule){
        scheduleRepository.save(schedule);
        return true ;
    }
    //按 id 号删除日程
    public boolean deleteSch(Integer id){
        scheduleRepository.deleteById(id);
        return true;
    }
    //更新日程信息
    public boolean updateSch(Schedule schedule) {
        scheduleRepository.deleteById(schedule.getId());
        scheduleRepository.save(schedule);
        return true;
    }
}


