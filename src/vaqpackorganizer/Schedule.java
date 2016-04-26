/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Kristian Recio
 */
public class Schedule {
    private int timeIncrement;
    private String[] timeIntervals;
    private int[][] coursesPlace;
    private Connection conn;
    private PreparedStatement ps;
    private String sql;
    
    public void generateSchedule() {
        generateTimeTicks();
        initializeCoursesPlace();
        inputDataIntoCoursesPlace();
    }
    
    public void initializeCoursesPlace() {
        setCoursesPlace(new int[getTimeIntervals().length][5]);
        
        for (int[] rows : getCoursesPlace())
            for (int j = 0; j < rows.length; j++)
                rows[j] = -1;
    }
    
    public void inputDataIntoCoursesPlace() {
        ArrayList<Course> courses = Main_FX.person.getCourses();
        int k;
        for (int i = 0; i < getTimeIntervals().length; i++) {
            for (int j = 0; j < courses.size(); j++) {
                if (getTimeIntervals()[i].equals(courses.get(j).getStartTime())) {
                    k = i;
                    while (!timeIntervals[k].equals(courses.get(j).getEndTime())) {
                        switch (courses.get(j).getDays()) {
                            case "M":
                                if (getCoursesPlace()[k][0] == -1)
                                    getCoursesPlace()[k][0] = j;
                                else {
                                    conflictingTimes(j);
                                    return;
                                }
                                break;
                            case "T":
                                if (getCoursesPlace()[k][1] == -1)
                                    getCoursesPlace()[k][1] = j;
                                else {
                                    conflictingTimes(j);
                                    return;
                                }
                                break;
                            case "W":
                                if (getCoursesPlace()[k][2] == -1)
                                    getCoursesPlace()[k][2] = j;
                                else {
                                    conflictingTimes(j);
                                    return;
                                }
                                break;
                            case "TH":
                                if (getCoursesPlace()[k][3] == -1)
                                    getCoursesPlace()[k][3] = j;
                                else {
                                    conflictingTimes(j);
                                    return;
                                }
                                break;
                            case "MW":
                                if (getCoursesPlace()[k][0] == -1 && getCoursesPlace()[k][2] == -1) {
                                    getCoursesPlace()[k][0] = j;
                                    getCoursesPlace()[k][2] = j;
                                } else {
                                    conflictingTimes(j);
                                    return;
                                }
                                break;
                            case "TR":
                                if (getCoursesPlace()[k][1] == -1 && getCoursesPlace()[k][3] == -1) {
                                    getCoursesPlace()[k][1] = j;
                                    getCoursesPlace()[k][3] = j;
                                } else {
                                    conflictingTimes(j);
                                    return;
                                }
                                break;
                            default:
                                if (getCoursesPlace()[k][4] == -1)
                                    getCoursesPlace()[k][4] = j;
                                else {
                                    conflictingTimes(j);
                                    return;
                                }
                                break;
                        }
                        k++;
                        if (!(k < timeIntervals.length)) break;
                    }
                }
            }
        }
    }
    
    public void conflictingTimes(int course) {
        Main_FX.person.getCourses().remove(course);
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Conflicting Times Alert");
        alert.setHeaderText("ERROR: Conflicting Times");
        alert.setContentText("The added course has conflicted with another courses's time. It will not be added.");
        
        alert.showAndWait();
        
        conn = Fn.get(conn);
        String user_id = Integer.toString(Main_FX.person.getUserId());
        ArrayList<Course> courses = Main_FX.person.getCourses();
        Course cour = courses.get(course);
        String number = cour.getNumber();
        sql = "DELETE FROM course WHERE user_id='" + user_id + "' AND number='" + number + "';";
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            Fn.showError(e);
        }
        
        generateSchedule();
        WeeklySchedule.lastestAdd = false;
    }
    
    public void generateTimeTicks() {
        TimeTicks timeTicks = new TimeTicks(getTimeIncrement());
        timeTicks.generateTicks();
        setTimeIntervals(timeTicks.getTimeTicksStrings());
    }

    public int getTimeIncrement() {
        return timeIncrement;
    }

    public void setTimeIncrement(int timeIncrement) {
        this.timeIncrement = timeIncrement;
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
