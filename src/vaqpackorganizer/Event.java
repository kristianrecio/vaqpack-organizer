/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Kristian Recio
 */
public class Event {
    private String name;
    private String startTime;
    private String endTime;
    private String place;
    private Date date;
    private String reminder;
    private String description; // Not implemented in MonthlySchedule.java
    
    public Event(String name, String startTime, String endTime, String place, Date date, String reminder) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.date = date;
        this.reminder = reminder;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}
