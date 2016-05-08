/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
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
    
    private Button Bedit = new Button("Edit");
    private Button Bsave = new Button("Save Changes");
    private Button BaddContact = new Button("Add emergency contact");
    
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
        setRight();
        setBottom();
        TInfo.setContent(pane);
    }
    
    private GridPane top_center = new GridPane();
    private GridPane top_bottom = new GridPane();
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
        Bsave.setDisable(true);
         Bedit.setOnAction((ActionEvent event) -> {
            Tname.setEditable(true);
            Temail.setEditable(true);
            Tid_num.setEditable(true);
            Tsemester.setEditable(true);
            Bsave.setDisable(false);
            
        });
         Bsave.setOnAction((ActionEvent event) -> {
            Tname.setEditable(false);
            Temail.setEditable(false);
            Tid_num.setEditable(false);
            Tsemester.setEditable(false);
            if(!Tname.getText().equals("")){
                person.setName(Tname.getText());
            }
            
            if(!Temail.getText().equals("")){
                person.setEmail(Temail.getText());
            }
            
            if(!Tid_num.getText().equals("")){
                person.setId_num(Tid_num.getText());
            }

            if(!Tsemester.getText().equals("")){
                person.setSemester(Tsemester.getText());
            }
            
         });
         
        top_center.setHgap(8);
        top_center.setVgap(16);
        
        top_center.add(Lname, 0, 0);
        top_center.add(Lemail, 0, 1);
        top_center.add(Lid_num, 0, 2);
        top_center.add(Lmajor, 0, 3);
        top_center.add(Lsemester, 0, 4);
        
        Tname.setText(person.getName());
        Tname.setEditable(false);
        top_center.add(Tname, 1, 0);
        
        Temail.setText(person.getEmail());
        Temail.setEditable(false);
        top_center.add(Temail, 1, 1);
        
        Tid_num.setText(person.getId_num());
        Tid_num.setEditable(false);
        top_center.add(Tid_num, 1, 2);
        
        Tmajor.setText(person.getMajor());
        Tmajor.setEditable(false);
        top_center.add(Tmajor, 1, 3);
        
        Tsemester.setText(person.getSemester());
        Tsemester.setEditable(false);
        top_center.add(Tsemester, 1, 4);
        
        
        top_bottom.add(Bedit, 0, 0);
        top_bottom.add(Bsave, 0, 1);
        ImageView picture = ImageViewBuilder.create()
                .image(new Image(person.getProfile_url()))
                .build();
        
        picture.setFitHeight(148);
        picture.setFitWidth(128);
        top.setCenter(top_center);
        top.setLeft(picture);
        top.setBottom(top_bottom);
        pane.setTop(top);
    }
    
    private GridPane left = new GridPane();
    private Label Lmajor_dep =  new Label("Major department information");
    private Label Lmajor_name = new Label ();
    private Label Lmajor_location = new Label();
    private Label Lmajor_phone = new Label();
    private Label Lmajor_email = new Label();
    private Label Lmajor_url = new Label();
    private Label Lprof = new Label("Department professors:");
    private ListView LVprof = new ListView();
    
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
    
    private GridPane right = new GridPane();
    private Label Lemer = new Label("Emergency contact:");
    private ListView LVemer = new ListView();
        
    public void setRight(){
        police.setMaxSize(300, 256);
        bookstore.setMaxSize(300, 256);
        
        police.setText("Police Department\n"+info.police.getLocation()
                +"\n"+info.police.getPhone());
        police.setEditable(false);
        bookstore.setText("Bookstore\n"+info.bookstore.getLocation()
                +"\n"+info.bookstore.getPhone()+"\n"+info.bookstore.getUrl());
        bookstore.setEditable(false);
        right.add(police, 0, 0);
        right.add(bookstore, 1, 0);
        pane.setRight(right);
    }
    
    private GridPane bottom = new GridPane();
    private TextArea police = new TextArea();
    private TextArea bookstore = new TextArea();
    public void setBottom(){
        LVemer.setMaxSize(256, 148);
        LVprof.setMaxSize(256, 148);
        ObservableList<String> emergency = FXCollections.observableArrayList();
        
        for (int i = 0; i < info.getPList().size(); i++){
            emergency.add(info.getEList().get(i).getInfoFormat());
        }
        LVemer.setItems(emergency);
        
        bottom.add(Lemer, 0, 0);
        bottom.add(LVemer, 0, 1);
                    ObservableList<String> professors = FXCollections.observableArrayList();
        
        for (int i = 0; i < info.getPList().size(); i++){
            professors.add(info.getPList().get(i).getInfoFormat());
        }

        LVprof.setItems(professors);

        bottom.add(Lprof, 1, 0);
        bottom.add(LVprof, 1, 1);
        
        pane.setBottom(bottom);
    }
    
    public Tab getTab(){
        return TInfo;
    }
}
