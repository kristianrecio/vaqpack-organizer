/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

/**
 *
 * @author Kristian Recio
 */
public class Row {
    private String time;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    
    public Row() {
        time = "";
        monday = "";
        tuesday = "";
        wednesday = "";
        thursday = "";
        friday = "";
    }
    
    public Row(String time, String monday, String tuesday, String wednesday, String thursday, String friday) {
        this.time = time;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }
    
    public void setAllDays(String day) {
        monday = day;
        tuesday = day;
        wednesday = day;
        thursday = day;
        friday = day;
    }
}
