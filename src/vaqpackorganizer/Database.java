/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Carlos
 */
public class Database {
    private final Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs;
    
    public Database(Connection conn){
        this.conn = conn;
    }
        public ResultSet get(String table){
        try{
            String sql = "SELECT * FROM "+table;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        }catch(SQLException e){
            Fn.showError(e);
        }
        return null;
    }
    
    public ResultSet get(String table, int id){
        try{
            String sql = "SELECT * FROM "+table;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        }catch(SQLException e){
            Fn.showError(e);
        }
        return null;
    }
    public void getItems(ObservableList<String> items, String table, String column){
        get(table);
        try{
            while(rs.next()){
                items.add(rs.getString(column));
            }
        }catch(SQLException e){
            Fn.showError(e);
        }
    }
    
    public void getItems(ObservableList<String> items, String table, String column, String parameter,int id){
        try{
        String sql = "SELECT * FROM "+table+" WHERE "+parameter+" = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                items.add(rs.getString(column));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
        
    public int getInt(String table, int id, String parameter){
        try{
            String sql = "SELECT * FROM "+table+" WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt(parameter);
        }
        }catch(SQLException e){
            Fn.showError(e);
        }
        return 0;
    }
    
    public String getString(String table, int id, String parameter){
    ResultSet rs;
        try{
        String sql = "SELECT * FROM "+table+" WHERE id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if(rs.next()){
            return rs.getString(parameter);
        }
        }catch(SQLException e){
            Fn.showError(e);
        }
        return null;
    }
    
    public int getID(String table, String column, int selection){
        try{
            String sql = "SELECT * FROM "+table+" WHERE "+column+" = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, selection);
            rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        }
        }catch(SQLException e){
            Fn.showError(e);
        }
        return 0;
    }
    
    
    public void generateProfessorList(ArrayList<Professor> professors, int major_id){
        try{
                String sql_get = "SELECT * FROM professor WHERE major_id = "+major_id;
                ps = conn.prepareStatement(sql_get);
                rs = ps.executeQuery();
                while(rs.next()){
                    Professor p = new Professor(rs.getString("name"),
                        rs.getString("phone"),rs.getString("email"),rs.getString("office"));
                    professors.add(p);
                }
        }catch(SQLException e){
            Fn.showError(e);
        }
    }
    
        public void generateEmergencyList(ArrayList<Emergency_contact> emergency, int user_id){
        try{
                String sql_get = "SELECT * FROM emergency_contact WHERE user_id = ?";
                ps = conn.prepareStatement(sql_get);
                ps.setInt(1, user_id);
                rs = ps.executeQuery();
                while(rs.next()){
                    Emergency_contact e = new Emergency_contact(rs.getString("name"),
                        rs.getString("phone"),rs.getString("email"),rs.getString("address"));
                    emergency.add(e);
                }
        }catch(SQLException e){
            Fn.showError(e);
        }
    }
        
    public void modifyString(int inv_id, String table, String Parameter, String value){
        if(!value.equals("")){
            String sql = "UPDATE "+table
                    + " SET "+Parameter+" = ?"
                    + " WHERE id = ?";
            try{
                ps = conn.prepareStatement(sql);
                ps.setString(1, value);
                ps.setInt(2, inv_id);
                ps.executeUpdate();
            }catch(SQLException e){

            }
        }
    }
    public void modifyInt(int id, String table, String Parameter, int value){
            String sql_set = "UPDATE "+table
                    + " SET "+Parameter+" = ?"
                    + " WHERE id = ?";
            try{
                ps = conn.prepareStatement(sql_set);
                ps.setInt(1, value);
                ps.setInt(2, id);
                ps.executeUpdate();
            }catch(SQLException e){

            }
    }
    public int getID(String table, String column, String selection){
        try{
        String sql = "SELECT * FROM "+table+" WHERE "+column+" = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, selection);
        rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        }
        }catch(SQLException e){
            Fn.showError(e);
        }
        return 0;
    }
}
