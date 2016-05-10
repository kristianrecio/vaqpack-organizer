/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

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
    private String date;
    private String day;
    private String reminder;
    private String description; 
    
    public Event(String name, String startTime, String endTime, String place, LocalDate date, String reminder, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.date = date.toString();
        this.day = date.getDayOfWeek().toString();
        this.reminder = reminder;
        this.description = description;
    }
    
    public Event(String name, String startTime, String endTime, String place, String date,
            String day, String reminder, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.date = date;
        this.day = day;
        this.reminder = reminder;
        this.description = description;
    }
    
    public String getEventInfo() {
        String info = String.format("Name: " + name + "\nTime: " + startTime + " - " + endTime +
                "\nPlace: " + place + "\nDate: " + date + "\nDescription: " + description);
        return info;
    }
    
    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getPlace() {
        return place;
    }

    public String getReminder() {
        return reminder;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getDay() {
        return day;
    }
}
