package vaqpackorganizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class Userinfo_FX extends Application {
    private Connection conn;
    private PreparedStatement ps = null;
    private Person user;
    
    private Button Ball = Fn.setButton(168,336,"Save Changes");
    private Button Bpass = Fn.setButton(168,136,"Change Password");
    
    private TextField Fuser_name = Fn.setTextField(8,16,"Username");
    private PasswordField Foldpass = Fn.setPasswordField(8,56,"Old password");
    private PasswordField Fnewpass = Fn.setPasswordField(8,96,"New password");
    private PasswordField Fconfirm = Fn.setPasswordField(8,136,"Confirm new password");
    private TextField Femail = Fn.setTextField(8,176,"e-mail");
    private TextField Fmajor = Fn.setTextField(8,216,"Major");
    private TextField Fid_num = Fn.setTextField(8,256,"UTRGV ID");
    private TextField Fsemester = Fn.setTextField(8,296,"Semester");
    private TextField Fname = Fn.setTextField(8,336,"Name");
    
    
    public Userinfo_FX(Connection conn, Person user, Stage primaryStage){
        this.conn = conn;
        this.user = user;
        start(primaryStage);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        Ball.setOnAction((ActionEvent event) -> {
        String user_name = Fuser_name.getText();
        String email = Femail.getText();
        String major = Fmajor.getText();
        String id_num = Fid_num.getText();
        String semester = Fsemester.getText();
        String name = Fname.getText();
        
            user.setUsername(user_name);
            user.setEmail(email);
            user.setMajor(major);
            user.setId_num(id_num);
            user.setSemester(semester);
            user.setName(name);
        });
        
        Bpass.setOnAction((ActionEvent event) ->{
            user.setPassword(Foldpass.getText(),
                    Fnewpass.getText(),Fconfirm.getText());
        });
        
        
        AnchorPane Userinfo = new AnchorPane();
        Userinfo.setPrefHeight(168);
        Userinfo.setPrefWidth(256);
        Userinfo.getChildren().addAll(Fuser_name,Foldpass,Fnewpass,Fconfirm,Bpass,
                Femail,Fmajor,Fid_num,Fsemester,Fname,Ball);
        
        Scene scene = new Scene(Userinfo, 300, 380);
        
        primaryStage.setTitle("Edit user information");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
