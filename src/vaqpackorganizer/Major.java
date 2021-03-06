/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Major {
    private final int user_id;
    private final int major_id;
    private ArrayList<Professor> professors = new ArrayList();
    private ArrayList<Emergency_contact> emergency = new ArrayList();
    public Police police = new Police();
    public Bookstore bookstore = new Bookstore();
    private String name;
    private String phone;
    private String email;
    private String url;
    
    public Major(int user_id, int major_id, Database Database){
        this.major_id = major_id;
        this.user_id = user_id;
        name = Database.getString("major", major_id, "name");
        phone = Database.getString("major", major_id, "phone");
        email = Database.getString("major", major_id, "email");
        url = Database.getString("major", major_id, "url");
        
        Database.generateProfessorList(professors, major_id);   
        Database.generateEmergencyList(emergency,user_id);
    }
        
    
    public ArrayList<Professor> getPList(){
        return professors;
    }
    
    public ArrayList<Emergency_contact> getEList(){
        return emergency;
    }
    
    public void setEList(){
        Main_FX.Database.generateEmergencyList(emergency,user_id);
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the major_id
     */
    public int getMajor_id() {
        return major_id;
    }
    
}

class Police{
    private String phone = "956-882-8232";
    private String location = "One W. University Boulevard, Brownsville, TX 78520";

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }
}

class Bookstore{
    private String phone = "956-665-2252";
    private String location = "1201 W University Dr, Edinburg, TX 78539, United States";
    private String url = "http://www.bkstr.com/utrgvstore/home";

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
}