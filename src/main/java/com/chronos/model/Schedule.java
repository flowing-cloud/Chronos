package com.chronos.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "t_schedule")
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sch_name")
    private String sch_name;
    @Column(name = "type")
    private String type;
    @Column(name = "start")
    private String start;
    @Column(name = "end")
    private String end;
    @Column(name = "note")
    private String note;
    @ManyToOne(fetch = FetchType.EAGER)//多对一
    private Customer aCustomer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSch_name() {
        return sch_name;
    }

    public void setSch_name(String sch_name) {
        this.sch_name = sch_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getaCustomer() {
        return aCustomer.getUsername();
    }

    public void setaCustomer(Customer aCustomer) {
        this.aCustomer = aCustomer;
    }
    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", sch_name='" + sch_name + '\'' +
                ", type='" + type + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", note='" + note + '\'' +
                ", aCustomer=" + aCustomer +
                '}';
    }
}

