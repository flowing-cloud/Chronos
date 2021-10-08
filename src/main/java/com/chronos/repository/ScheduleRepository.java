package com.chronos.repository;

import com.chronos.model.Customer;
import com.chronos.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    List<Schedule> findAllByOrderById();

    @Query( value = "select b from t_schedule  b where b.start >= ?1 and b.end <= ?2")
    public List<Schedule> findByDateBetween(LocalDateTime start, LocalDateTime end);
    @Modifying
    @Transactional
    @Query("update t_schedule s set s.sch_name=?1,s.type=?2,s.start=?3,s.end=?4,s.aCustomer=?5where s.id=?6")
    int update(String sch_name, String type, String start, String end, Customer aCustomer, Integer id);
}

