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
    private int[][] eventsPlace;
    private ArrayList<Event> events;
    private ArrayList<Course> courses;
    
    public void generateCourseSchedule() {
        generateTimeTicks();
        initializeCoursesPlace();
        setCoursesPlace();
    }
    
    public void generateEventSchedule() {
        generateTimeTicks();
        initializeEventsPlace();
        setEventsPlace();
    }
    
    public void initializeCoursesPlace() {
        setCoursesPlace(new int[getTimeIntervals().length][5]);
        
        for (int[] rows : coursesPlace)
            for (int j = 0; j < rows.length; j++)
                rows[j] = -1;
    }
    
    public void initializeEventsPlace() {
        setEventsPlace(new int[getTimeIntervals().length][7]);
        
        for (int[] rows : eventsPlace)
            for (int i = 0; i < rows.length; i++)
                rows[i] = -1;
    }
    
    public void timeConflictAlert(int type) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Conflicting Times Alert");
        alert.setHeaderText("ERROR: Conflicting Times");
        switch (type) {
            case 0:
                alert.setContentText("The added course has conflicted with another courses's time. It will not be added.");
                break;
            case 1:
                alert.setContentText("The modified course has conflicted with another course's time. The modifications will not be applied.");
                break;
            case 2:
                alert.setContentText("The event added has conflicted with another event's time. It will not be added.");
                break;
            case 3:
                alert.setContentText("The event added has conflicted with a course's time. It will not be added.");
        }
        
        alert.showAndWait();
    }
    
    public boolean isThereCourseTimeConflict(int course) {
        ArrayList<Integer> courseTime = new ArrayList<>();
        ArrayList<Integer> otherCourseTime;
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
            otherCourseTime = new ArrayList<>();
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
                        if (courseTimeConflictDayCheck(course1, course2))
                            return true;
        }
        return false;
    }
    
    public boolean courseTimeConflictDayCheck(Course course1, Course course2) {
        String days1 = course1.getDays();
        String days2 = course2.getDays();
        
        switch (days1) {
            case "M":
                if (days2.equals("M") || days2.equals("MW"))
                    return true;
                break;
            case "T":
                if (days2.equals("T") || days2.equals("TR"))
                    return true;
                break;
            case "W":
                if (days2.equals("W") || days2.equals("MW"))
                    return true;
                break;
            case "TH":
                if (days2.equals("TH") || days2.equals("TR"))
                    return true;
                break;
            case "MW":
                if (days2.equals("MW") || days2.equals("M") || days2.equals("W"))
                    return true;
                break;
            case "TR":
                if (days2.equals("TR") || days2.equals("T") || days2.equals("TH"))
                    return true;
                break;
            default:
                if (days2.equals("F"))
                    return true;
                break;
        }
        return false;
    }
    
    public boolean isThereEventTimeConflict(int event) {
        ArrayList<Integer> eventTime = new ArrayList<>();
        ArrayList<Integer> otherEventTime;
        events = Main_FX.person.getEvents();
        Event event1 = events.get(event);
        Event event2 = null;
        
        for (int i = 0; i < timeIntervals.length; i++) {
            if (timeIntervals[i].equals(event1.getStartTime())) {
                while (!timeIntervals[i].equals(event1.getEndTime()))
                    eventTime.add(i++);
                break;
            }
        }
        
        for (int i = 0; i < events.size(); i++) {
            otherEventTime = new ArrayList<>();
            if (i == event)
                continue;
            for (int j = 0; j < timeIntervals.length; j++) {
                if (timeIntervals[j].equals(events.get(i).getStartTime())) {
                    while (!timeIntervals[j].equals(events.get(i).getEndTime()))
                        otherEventTime.add(j++);
                    event2 = events.get(i);
                    break;
                }
            }
            
            if (event2 == null)
                break;
            
            for (int j = 0; j < eventTime.size(); j++)
                for (int k = 0; k < otherEventTime.size(); k++)
                    if (eventTime.get(j).equals(otherEventTime.get(k)))
                        if (event1.getDate().equals(event2.getDate()))
                            return true;
        }
        
        return false;
    }
    
    public boolean isThereEventCourseConflict(int eventNum) {
        ArrayList<Integer> eventTime = new ArrayList<>();
        ArrayList<Integer> courseTime;
        events = Main_FX.person.getEvents();
        courses = Main_FX.person.getCourses();
        Event event = events.get(eventNum);
        Course course = null;
        
        for (int i = 0; i < timeIntervals.length; i++) {
            if (timeIntervals[i].equals(event.getStartTime())) {
                while (!timeIntervals[i].equals(event.getEndTime()))
                    eventTime.add(i++);
                break;
            }
        }
        
        for (int i = 0; i < courses.size(); i++) {
            courseTime = new ArrayList<>();
            for (int j = 0; j < timeIntervals.length; j++) {
                if (timeIntervals[j].equals(courses.get(i).getStartTime())) {
                    while (!timeIntervals[j].equals(courses.get(i).getEndTime()))
                        courseTime.add(j++);
                    course = courses.get(i);
                    break;
                }
            }
            
            if (course == null)
                break;
            
            for (int j = 0; j < eventTime.size(); j++)
                for (int k = 0; k < courseTime.size(); k++)
                    if (eventTime.get(j).equals(courseTime.get(k)))
                        if (courseEventConflictDayCheck(event, course))
                            return true;
        }
        
        return false;
    }
    
    public boolean courseEventConflictDayCheck(Event event, Course course) {
        switch (event.getDay()) {
            case "MONDAY":
                if (course.getDays().equals("M") || course.getDays().equals("MW"))
                    return true;
                break;
            case "TUESDAY":
                if (course.getDays().equals("T") || course.getDays().equals("TR"))
                    return true;
                break;
            case "WEDNESDAY":
                if (course.getDays().equals("W") || course.getDays().equals("MW"))
                    return true;
                break;
            case "THURSDAY":
                if (course.getDays().equals("TH") || course.getDays().equals("TR"))
                    return true;
                break;
            case "FRIDAY":
                if (course.getDays().equals("F"))
                    return true;
                break;
            default:
                return false;
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
                        while (!timeIntervals[end].equals(courses.get(course).getEndTime()))
                            if (++end >= timeIntervals.length)
                                break;
                        while (start < end)
                            coursesPlace[start++][0] = course;
                        break;
                    case "T":
                        while (!timeIntervals[end].equals(courses.get(course).getEndTime()))
                            if (++end >= timeIntervals.length)
                                break;
                        while (start < end)
                            coursesPlace[start++][1] = course;
                        break;
                    case "W":
                        while (!timeIntervals[end].equals(courses.get(course).getEndTime()))
                            if (++end >= timeIntervals.length)
                                break;
                        while (start < end)
                            coursesPlace[start++][2] = course;
                        break;
                    case "TH":
                        while (!timeIntervals[end].equals(courses.get(course).getEndTime()))
                            if (++end >= timeIntervals.length)
                                break;
                        while (start < end)
                            coursesPlace[start++][3] = course;
                    case "MW":
                        while (!timeIntervals[end].equals(courses.get(course).getEndTime()))
                            if (++end >= timeIntervals.length)
                                break;
                        while (start < end) {
                            coursesPlace[start][0] = course;
                            coursesPlace[start++][2] = course;
                        }
                        break;
                    case "TR":
                        while (!timeIntervals[end].equals(courses.get(course).getEndTime()))
                            if (++end >= timeIntervals.length)
                                break;
                        while (start < end) {
                            coursesPlace[start][1] = course;
                            coursesPlace[start++][3] = course;
                        }
                        break;
                    default:
                        while (!timeIntervals[end].equals(courses.get(course).getEndTime()))
                            if (++end >= timeIntervals.length)
                                break;
                        while (start < end)
                            coursesPlace[start++][4] = course;
                        break;
                }
                break;
            }
        }
    }
    
    public void setEventsPlace() {
        events = Main_FX.person.getEvents();
        String day;
        int start, end;
        for (int event = 0; event < events.size(); event++) {
            for (int time = 0; time < timeIntervals.length; time++) {
                if (!timeIntervals[time].equals(events.get(event).getStartTime()))
                    continue;
                start = time;
                end = start;
                int column;
                switch (events.get(event).getDay()) {
                    case "SUNDAY": column = 0; break;
                    case "MONDAY": column = 1; break;
                    case "TUESDAY": column = 2; break;
                    case "WEDNESDAY": column = 3; break;
                    case "THURSDAY": column = 4; break;
                    case "FRIDAY": column = 5; break;
                    default: column = 6; break;
                }
                
                while (!timeIntervals[++end].equals(events.get(event).getEndTime()))
                    if (end >= timeIntervals.length)
                        break;
                while (start < end)
                    eventsPlace[start++][column] = event;
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

    public int[][] getEventsPlace() {
        return eventsPlace;
    }

    public void setEventsPlace(int[][] eventsPlace) {
        this.eventsPlace = eventsPlace;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
