package vaqpackorganizer;

import java.util.ArrayList;

public class Person {
    private String name;
    private String id;
    private String semester;
    private ArrayList<Course> courses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    public ArrayList getCourses() {
        return courses;
    }
    
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
