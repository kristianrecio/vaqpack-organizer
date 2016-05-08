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
    private String dateString;
    private int month;
    private int day;
    private int year;
    private String dayString;
    private String reminder;
    private String description; // Not implemented in MonthlySchedule.java
    
    public Event(String name, String startTime, String endTime, String place, LocalDate date, String reminder, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.dateString = date.toString();
        this.month = date.getMonthValue();
        this.day = date.getDayOfMonth();
        this.year = date.getYear();
        this.dayString = date.getDayOfWeek().toString();
        this.reminder = reminder;
        this.description = description;
    }
    
    public Event(String name, String startTime, String endTime, String place, String dateString,
            int month, int day, int year, String dayString, String reminder, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.dateString = dateString;
        this.month = month;
        this.day = day;
        this.year = year;
        this.dayString = dayString;
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

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDayString() {
        return dayString;
    }

    public void setDayString(String dayString) {
        this.dayString = dayString;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
