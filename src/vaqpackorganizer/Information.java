/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Carlos
 */
public class Information {
    private Major info;
    private Person person;
    private Database Database;
    private Tab TInfo;
    private BorderPane pane;
    
    private Button Bedit = new Button();
    
    public Information(Major info, Person person, Database Database){
        this.info = info;
        this.person =  person;
        this.Database =  Database;
    }
    
    public void setTab(){
        TInfo = new Tab();
        TInfo.setText("Information");
        setPane();
        
    }
    public void setPane(){
        pane = new BorderPane();
        
        setTop();
        setLeft();
        //setCenter();
        //setRight();
        //setBottom();
        TInfo.setContent(pane);
    }
    
    private GridPane top_right = new GridPane();
    private BorderPane top = new BorderPane();
    private Label Lname = new Label("Name");
    private Label Lemail = new Label("e-mail");
    private Label Lid_num = new Label("UTRGV ID");
    private Label Lmajor = new Label("Major");
    private Label Lsemester = new Label("Semester");
    private TextField Tname = new TextField();
    private TextField Temail = new TextField();
    private TextField Tid_num = new TextField();
    private TextField Tmajor = new TextField();
    private TextField Tsemester = new TextField();
    
    public void setTop(){
        top_right.setHgap(8);
        top_right.setVgap(16);
        
        top_right.add(Lname, 0, 0);
        top_right.add(Lemail, 0, 1);
        top_right.add(Lid_num, 0, 2);
        top_right.add(Lmajor, 0, 3);
        top_right.add(Lsemester, 0, 4);
        
        Tname.setText(person.getName());
        Tname.setDisable(true);
        top_right.add(Tname, 1, 0);
        
        Temail.setText(person.getEmail());
        Temail.setDisable(true);
        top_right.add(Temail, 1, 1);
        
        Tid_num.setText(person.getId_num());
        Tid_num.setDisable(true);
        top_right.add(Tid_num, 1, 2);
        
        Tmajor.setText(person.getMajor());
        Tmajor.setDisable(true);
        top_right.add(Tmajor, 1, 3);
        
        Tsemester.setText(person.getSemester());
        Tsemester.setDisable(true);
        top_right.add(Tsemester, 1, 4);
        
        
        ImageView picture = ImageViewBuilder.create()
                .image(new Image(person.getProfile_url()))
                .build();
        
        picture.setFitHeight(148);
        picture.setFitWidth(128);
        
        top.setCenter(top_right);
        top.setLeft(picture);
        pane.setTop(top);
    }
    
    private GridPane left = new GridPane();
    private Label Lmajor_dep =  new Label("Major department information");
    private Label Lmajor_name = new Label ();
    private Label Lmajor_location = new Label();
    private Label Lmajor_phone = new Label();
    private Label Lmajor_email = new Label();
    private Label Lmajor_url = new Label();
    
    public void setLeft(){
        left.setHgap(8);
        left.setVgap(4);
        
        Lmajor_name.setText(info.getName());
        Lmajor_location.setText("Location: "+info.getLocation());
        Lmajor_phone.setText("Phone: "+info.getPhone());
        Lmajor_email.setText("e-mail: "+info.getEmail());
        Lmajor_url.setText("Department website: "+info.getUrl());
        
        left.add(Lmajor_dep, 0, 0);
        left.add(Lmajor_name, 1, 0);
        left.add(Lmajor_location, 0, 1);
        left.add(Lmajor_phone, 1, 1);
        left.add(Lmajor_email, 0, 2);
        left.add(Lmajor_url, 1, 2);
        
        pane.setLeft(left);
    }
    
    public Tab getTab(){
        return TInfo;
    }
}
