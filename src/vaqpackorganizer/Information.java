/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import javafx.scene.control.Tab;

/**
 *
 * @author Carlos
 */
public class Information {
    private Major info;
    private Tab TInfo;
    public Information(Major info){
        this.info = info;
    }
    
    public void setTab(){
        
    }
    
    public Tab getTab(){
        return TInfo;
    }
}
