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
    private ArrayList<Course> courses;
    
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
    
    public void timeConflictAlert(int type) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Conflicting Times Alert");
        alert.setHeaderText("ERROR: Conflicting Times");
        if (type == 0)
            alert.setContentText("The added course has conflicted with another courses's time. It will be removed.");
        else
            alert.setContentText("The modified course has conflicted with another course's time. The modifications will be removed.");
        
        alert.showAndWait();
    }
    
    public boolean isThereATimeConflict(int course) {
        ArrayList<Integer> courseTime = new ArrayList<>();
        ArrayList<Integer> otherCourseTime = new ArrayList<>();
        courses = Main_FX.person.getCourses();
        Course course1 = courses.get(course);
        Course course2 = null;
        
        for (int i = 0; i < timeIntervals.length; i++) {
            if (timeIntervals[i].equals(course1.getStartTime())) {
                while (!timeIntervals[i].equals(course1.getEndTime()))
                    courseTime.add(i++);
                break;
            }
        }
        
        for (int i = 0; i < courses.size(); i++) {
            if (i == course)
                continue;
            for (int j = 0; j < timeIntervals.length; j++) {
                if (timeIntervals[j].equals(courses.get(i).getStartTime())) {
                    while (!timeIntervals[j].equals(courses.get(i).getEndTime()))
                        otherCourseTime.add(j++);
                    course2 = courses.get(i);
                    break;
                }
            }
            if (course2 == null)
                break;
            
            for (int j = 0; j < courseTime.size(); j++)
                for (int k = 0; k < otherCourseTime.size(); k++)
                    if (courseTime.get(j).equals(otherCourseTime.get(k)))
                        if (course1.getDays().equals(course2.getDays()))
                            return true;
        }
        return false;
    }
    
    public void setCoursesPlace() {
        courses = Main_FX.person.getCourses();
        int start, end;
        for (int course = 0; course < courses.size(); course++) {
            for (int time = 0; time < timeIntervals.length; time++) {
                if (!timeIntervals[time].equals(courses.get(course).getStartTime()))
                    continue;
                start = time;
                end = start;
                switch (courses.get(course).getDays()) {
                    case "M":
                        while (!timeIntervals[++end].equals(courses.get(course).getEndTime()))
                            if (end >= timeIntervals.length)
                                break;
                        while (start < end)
                            coursesPlace[start++][0] = course;
                        break;
                    case "T":
                        while (!timeIntervals[++end].equals(courses.get(course).getEndTime()))
                            if (end >= timeIntervals.length)
                                break;
                        while (start < end)
                            coursesPlace[start++][1] = course;
                        break;
                    case "W":
                        while (!timeIntervals[++end].equals(courses.get(course).getEndTime()))
                            if (end >= timeIntervals.length)
                                break;
                        while (start < end)
                            coursesPlace[start++][2] = course;
                        break;
                    case "TH":
                        while (!timeIntervals[++end].equals(courses.get(course).getEndTime()))
                            if (end >= timeIntervals.length)
                                break;
                        while (start < end)
                            coursesPlace[start++][3] = course;
                    case "MW":
                        while (!timeIntervals[++end].equals(courses.get(course).getEndTime()))
                            if (end >= timeIntervals.length)
                                break;
                        while (start < end) {
                            coursesPlace[start][0] = course;
                            coursesPlace[start++][2] = course;
                        }
                        break;
                    case "TR":
                        while (!timeIntervals[++end].equals(courses.get(course).getEndTime()))
                            if (end >= timeIntervals.length)
                                break;
                        while (start < end) {
                            coursesPlace[start][1] = course;
                            coursesPlace[start++][3] = course;
                        }
                        break;
                    default:
                        while (!timeIntervals[++end].equals(courses.get(course).getEndTime()))
                            if (end >= timeIntervals.length)
                                break;
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
