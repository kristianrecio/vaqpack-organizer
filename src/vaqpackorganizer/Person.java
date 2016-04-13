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
    private ArrayList<Course> courses = new ArrayList<Course>();
    
    public Person(int id, Connection conn){
        user_id = id;
        this.conn = conn;
        name = get("name");
        id_num = get("id_num");
        semester = get("Semester");
        email = get("email");
        major = get("major");
        password = get("password");
        username = get("username");
        profile_url = get("profile_url");
        generateCourses();
    }
    
    public void generateCourses(){
        try{
                String sql_get = "SELECT * FROM course WHERE user_id = ?";
                ps = conn.prepareStatement(sql_get);
                ps.setInt(1, user_id);
                rs = ps.executeQuery();
                while(rs.next()){
                Course c = new Course(rs.getString("prefix"),rs.getString("number"),
                            rs.getString("description"),rs.getString("location"),
                        rs.getString("days"),rs.getString("time_from"),rs.getString("time_to"));
                    System.out.println(c.getNumber());
                    courses.add(c);
                }
        }catch(SQLException e){
            Fn.showError(e);
        }
    }
    
    public String get(String Parameter){
        String result = "";
        try{
            String sql_get = "SELECT * FROM user WHERE id = ?";
            ps = conn.prepareStatement(sql_get);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            if(rs.next()){
                result = rs.getString(Parameter);
            }
            else{
                result = "";
            }
        }catch(SQLException e){
            Fn.showError(e);
        }
        return result;
    }
    
    public void set(String Parameter, String Value){
        if(!Value.equals("")){
            String sql_set = "UPDATE user"
                    + " SET "+Parameter+" = ?"
                    + " WHERE id = ?";
            try{
                ps = conn.prepareStatement(sql_set);
                ps.setString(1, Value);
                ps.setInt(2, user_id);
                ps.executeUpdate();
            }catch(SQLException e){
                Fn.showError(e);
            }
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        set("name",name);
        this.name = get("name");
    }
    
    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        set("id_num",id_num);
        this.id_num = get("id_num");
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        set("Semester",semester);
        this.semester = get("semester");
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
        set("email",email);
        this.email = get("email");
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        set("major",major);
        this.major = get("major");
    }

    public String getPassword(){
        return password;
    }
    
    public void setPassword(String oldpass,String newpass,String confirm) {
        if(oldpass.equals(getPassword()) && newpass.equals(confirm)){
            set("password",newpass);
            this.password = get("password");
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        set("username",username);
        this.username = get("username");
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        set("profile_url",profile_url);
        this.profile_url = get("profile_url");
    }
}
