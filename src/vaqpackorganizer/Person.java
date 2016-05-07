package vaqpackorganizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Person {
    private final int user_id;
    private final Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs;
    
    private String name;
    private String id_num;
    private String semester;
    private String email;
    private String major;
    private String password;
    private String username;
    private String profile_url;
    private ArrayList<Course> courses;
    private Database Database;
    
    public Person(int id, int major_id, Connection conn, Database Database){
        this.Database = Database;
        user_id = id;
        this.conn = conn;
        name = this.Database.getString("user", user_id, "name");
        id_num = this.Database.getString("user", user_id, "id_num");
        semester = this.Database.getString("user", user_id, "semester");
        email = this.Database.getString("user", user_id, "email");
        major = this.Database.getString("major",major_id,"name");
        password = this.Database.getString("user", user_id, "password");
        username = this.Database.getString("user", user_id, "username");
        profile_url = this.Database.getString("user", user_id, "profile_url");
        generateCourses();
    }
    
    public void generateCourses(){
        courses = new ArrayList<>();
        try{
                String sql_get = "SELECT * FROM course WHERE user_id = ?";
                ps = conn.prepareStatement(sql_get);
                ps.setInt(1, user_id);
                rs = ps.executeQuery();
                while(rs.next()){
                Course c = new Course(rs.getInt("id"), rs.getString("prefix"),rs.getString("number"),
                            rs.getString("description"),rs.getString("location"),
                        rs.getString("days"),rs.getString("startTime"),rs.getString("endTime"));
                    courses.add(c);
                }
        }catch(SQLException e){
            Fn.showError(e);
        }
    }
    
    public int getUserId() {
        return user_id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        Database.modifyString(user_id, "user", "name", name);
        this.name = Database.getString("user", user_id, "name");
    }
    
    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        Database.modifyString(user_id, "user", "id_num", id_num);
        this.id_num = Database.getString("user", user_id, "id_num");
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        Database.modifyString(user_id, "user", "semester", semester);
        this.semester = Database.getString("user", user_id, "semester");
    }
    
    public ArrayList getCourses() {
        return courses;
    }
    
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        Database.modifyString(user_id, "user", "email", email);
        this.email = Database.getString("user", user_id, "email");
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(int major_id) {
        Database.modifyInt(user_id, "user", "major_id", major_id);
        this.major = Database.getString("major", major_id, "name");
    }

    public String getPassword(){
        return password;
    }
    
    public void setPassword(String oldpass,String newpass,String confirm) {
        if(oldpass.equals(getPassword()) && newpass.equals(confirm)){
        Database.modifyString(user_id, "user", "password", password);
        this.password = Database.getString("user", user_id, "password");
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Database.modifyString(user_id, "user", "username", username);
        this.username = Database.getString("user", user_id, "username");
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        Database.modifyString(user_id, "user", "profile_url", profile_url);
        this.profile_url = Database.getString("user", user_id, "profile_url");
    }
}
