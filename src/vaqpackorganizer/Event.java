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
    private LocalDate date;
    private String day;
    private String dateString;
    private String reminder;
    private String description; 
    
    public Event(String name, String startTime, String endTime, String place, LocalDate date, String reminder, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.date = date;
        this.dateString = date.toString();
        this.day = date.getDayOfWeek().toString();
        this.reminder = reminder;
        this.description = description;
    }
    
    public Event(String name, String startTime, String endTime, String place, String dateString, String day, String reminder, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.dateString = dateString;
        this.day = day;
        this.reminder = reminder;
        this.description = description;
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

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
