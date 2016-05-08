/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;

/**
 *
 * @author Kristian Recio
 */
public class Admin {
    private Tab tab;
    
    public Admin() {
         ObservableList<String> users = FXCollections.observableArrayList();
         Main_FX.Database.getItems(users, "user", "name");
    }
    
    public void setTab() {
        tab = new Tab();
    }
    
    public Tab getTab() {
        return tab;
    }
}
