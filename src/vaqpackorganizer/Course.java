package vaqpackorganizer;

public class Course {
    private int id;
    private String prefix;
    private String number;
    private String description;
    private String location;
    private String days;
    private String startTime;
    private String endTime;
    
    public Course(int id, String prefix, String number, String description,
            String location, String days, String startTime, String endTime){
        this.id = id;
        this.prefix = prefix;
        this.number = number;
        this.description = description;
        this.location = location;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public Course(String prefix, String number, String description,
            String location, String days, String startTime, String endTime){
        this.prefix = prefix;
        this.number = number;
        this.description = description;
        this.location = location;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public boolean isEqualTo(Course course) {
        if (course.prefix.equals(this.prefix) &&
            course.number.equals(this.number) &&
            course.description.equals(this.description) &&
            course.location.equals(this.location) &&
            course.days.equals(this.days) &&
            course.startTime.equals(this.startTime) &&
            course.endTime.equals(this.endTime))
            return true;
        return false;
    }
    
    public String getTableInfo() {
        return prefix + " " + number + " " + location;
    }
    
    public String getListInfo() {
        return prefix + "-" + number + " " + description;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getDays() {
        return days;
    }
    
    public void setDays(String days) {
        this.days = days;
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
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
}
