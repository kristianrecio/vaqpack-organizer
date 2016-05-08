/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Kristian Recio
 */
public class PieChartAnimation {
    private Schedule schedule;
    private PieChart chart;
    
    
    public PieChartAnimation() {
        
    }
    
    public void setChart() {
        chart = new PieChart(setData());
    }
    
    public ObservableList<PieChart.Data> setData() {
        schedule.generateCourseSchedule();
        schedule.generateEventSchedule();
        int[][] coursesPlace = schedule.getCoursesPlace();
        int[][] eventsPlace = schedule.getEventsPlace();
        
        int courseTime = 0;
        int eventTime = 0;
        int freeTime = 57 * 5;
        
        for (int[] coursesPlace1 : coursesPlace) {
            for (int j = 0; j < coursesPlace1.length; j++) {
                if (coursesPlace1[j] != -1) {
                    courseTime++;
                    freeTime--;
                }
            }
        }
        
        for (int[] eventsPlace1 : eventsPlace) {
            for (int j = 1; j < 6; j++) {
                if (eventsPlace1[j] != -1) {
                    eventTime++;
                    freeTime--;
                }
            }
        }
        
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("Course Time", courseTime * 15));
        data.add(new PieChart.Data("Commitments Time", eventTime * 15));
        data.add(new PieChart.Data("Free Time", freeTime * 15));
        
        return data;
    }
    
    public void updatePieChart() {
        schedule.generateCourseSchedule();
        schedule.generateEventSchedule();
        int[][] coursesPlace = schedule.getCoursesPlace();
        int[][] eventsPlace = schedule.getEventsPlace();
        
        int coursesTime = 0;
        int eventsTime = 0;
        int freeTime = 57 * 5;
        
        for (int[] coursesPlace1 : coursesPlace) {
            for (int j = 0; j < coursesPlace1.length; j++) {
                if (coursesPlace1[j] != -1) {
                    coursesTime++;
                    freeTime--;
                }
            }
        }
        
        for (int[] eventsPlace1 : eventsPlace) {
            for (int j = 1; j < 6; j++) {
                if (eventsPlace1[j] != -1) {
                    eventsTime++;
                    freeTime--;
                }
            }
        }
        
        for (PieChart.Data data : chart.getData()) {
            if (data.getName().equals("Course Time"))
                data.setPieValue(coursesTime * 15);
            if (data.getName().equals("Commitments Time"))
                data.setPieValue(eventsTime * 15);
            if (data.getName().equals("Free Time"))
                data.setPieValue(freeTime * 15);
        }
    }
    
    public PieChart getChart() {
        return chart;
    }
}
