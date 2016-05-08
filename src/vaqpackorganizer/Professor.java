/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

/**
 *
 * @author Carlos
 */
public class Professor {
    private String name;
    private String phone;
    private String email;
    private String address;
    
    public Professor(String name, String phone, String email, String address){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    
    public String getInfoFormat(){
        return name+" - "+email;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }
}

