/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Kristian Recio
 */
public class Schedule {
    private final int timeIncrement = 15;
    private String[] timeIntervals;
    private int[][] coursesPlace;
    private ArrayList<Course> Courses;
    
    public void generateSchedule() {
        generateTimeTicks();
        initializeCoursesPlace();
        setCoursesPlace();
    }
    
    public void initializeCoursesPlace() {
        setCoursesPlace(new int[getTimeIntervals().length][5]);
        
        for (int[] rows : getCoursesPlace())
            for (int j = 0; j < rows.length; j++)
                rows[j] = -1;
    }
    
    public void timeConflict() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Conflicting Times Alert");
        alert.setHeaderText("ERROR: Conflicting Times");
        alert.setContentText("The added course has conflicted with another courses's time. It will not be added.");
        
        alert.showAndWait();
        int latest = Main_FX.person.getCourses().size() - 1;
        Main_FX.person.getCourses().remove(latest);
        generateSchedule();
        WeeklySchedule.lastestAdd = false;
    }
    
    public void setCoursesPlace() {
        Courses = Main_FX.person.getCourses();
        int start, end;
        for (int course = 0; course < Courses.size(); course++) {
            for (int time = 0; time < timeIntervals.length; time++) {
                if (!timeIntervals[time].equals(Courses.get(course).getStartTime()))
                    continue;
                start = time;
                end = start;
                switch (Courses.get(course).getDays()) {
                    case "M":
                        while (!timeIntervals[end].equals(Courses.get(course).getEndTime())) {
                            if (coursesPlace[end++][0] != -1) {
                                timeConflict();
                                return;
                            }
                            if (end >= timeIntervals.length)
                                break;
                        }
                        while (start < end)
                            coursesPlace[start++][0] = course;
                        break;
                    case "T":
                        while (!timeIntervals[end].equals(Courses.get(course).getEndTime())) {
                            if (coursesPlace[end++][1] != -1) {
                                timeConflict();
                                return;
                            }
                            if (end >= timeIntervals.length)
                                break;
                        }
                        while (start < end)
                            coursesPlace[start++][1] = course;
                        break;
                    case "W":
                        while (!timeIntervals[end].equals(Courses.get(course).getEndTime())) {
                            if (coursesPlace[end++][2] != -1) {
                                timeConflict();
                                return;
                            }
                            if (end >= timeIntervals.length)
                                break;
                        }
                        while (start < end)
                            coursesPlace[start++][2] = course;
                        break;
                    case "TH":
                        while (!timeIntervals[end].equals(Courses.get(course).getEndTime())) {
                            if (coursesPlace[end++][3] != -1) {
                                timeConflict();
                                return;
                            }
                            if (end >= timeIntervals.length)
                                break;
                        }
                        while (start < end)
                            coursesPlace[start++][3] = course;
                        break;
                    case "MW":
                        while (!timeIntervals[end].equals(Courses.get(course).getEndTime())) {
                            if (coursesPlace[end][0] != -1 ||
                                coursesPlace[end++][2] != -1) {
                                timeConflict();
                                return;
                            }
                            if (end >= timeIntervals.length)
                                break;
                        }
                        while (start < end) {
                            coursesPlace[start][0] = course;
                            coursesPlace[start++][2] = course;
                        }
                        break;
                    case "TR":
                        while (!timeIntervals[end].equals(Courses.get(course).getEndTime())) {
                            if (coursesPlace[end][1] != -1 ||
                                coursesPlace[end++][3] != -1) {
                                timeConflict();
                                return;
                            }
                            if (end >= timeIntervals.length)
                                break;
                        }
                        while (start < end) {
                            coursesPlace[start][1] = course;
                            coursesPlace[start++][3] = course;
                        }
                        break;
                    default:
                        while (!timeIntervals[end].equals(Courses.get(course).getEndTime())) {
                            if (coursesPlace[end++][4] != -1) {
                                timeConflict();
                                return;
                            }
                            if (end >= timeIntervals.length)
                                break;
                        }
                        while (start < end)
                            coursesPlace[start++][4] = course;
                        break;
                }
                break;
            }
        }
    }
    
    public void generateTimeTicks() {
        TimeTicks timeTicks = new TimeTicks(getTimeIncrement());
        timeTicks.generateTicks();
        setTimeIntervals(timeTicks.getTimeTicksStrings());
    }

    public int getTimeIncrement() {
        return timeIncrement;
    }
    
    public String[] getTimeIntervals() {
        return timeIntervals;
    }

    public void setTimeIntervals(String[] timeIntervals) {
        this.timeIntervals = timeIntervals;
    }

    public int[][] getCoursesPlace() {
        return coursesPlace;
    }

    public void setCoursesPlace(int[][] coursesPlace) {
        this.coursesPlace = coursesPlace;
    }
}
