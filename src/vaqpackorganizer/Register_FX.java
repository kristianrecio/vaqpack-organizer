package vaqpackorganizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class Register_FX extends Application {
    
    private Connection conn;
    private PreparedStatement ps = null;
    private TextField Fname = Fn.setTextField(8,16,"User name");
    private PasswordField Fpass = Fn.setPasswordField(8,56,"Password");
    private PasswordField Fconfirm = Fn.setPasswordField(8,96,"Confirm Password");
    private Button Bsubmit = Fn.setButton(8,136,"Submit");
    private Label LeName = Fn.setLabel(174, 16, "No user name.", false, "RED");
    private Label LePass = Fn.setLabel(174, 56, "No Password.", false, "RED");
    private Label LeMatch = Fn.setLabel(174, 96, "Password does\nnot match!", false, "RED");
    
    public Register_FX(Connection conn, Stage primaryStage){
        this.conn = conn;
        start(primaryStage);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Bsubmit.setOnAction((ActionEvent event) -> {
            startBsubmit();
        });
        AnchorPane Register = new AnchorPane();
        Register.setPrefHeight(168);
        Register.setPrefWidth(256);
        Register.getChildren().addAll(LeName,LePass,LeMatch,Fname,Fpass,Fconfirm,Bsubmit);
        
        Scene scene = new Scene(Register, Register.getPrefWidth(), Register.getPrefHeight());
        primaryStage.setTitle("Register.");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
    }
    
    public void startBsubmit(){
        String name = Fname.getText().toLowerCase();
        String pass = Fpass.getText();
        String confirm = Fconfirm.getText();
        boolean noError = true;
        
        if(name.equals("")){LeName.setVisible(true); noError = false;}
        else{LeName.setVisible(false);}
        if(pass.equals("")){LePass.setVisible(true); noError = false;}
        else{LePass.setVisible(false);}
        if(!pass.equals(confirm)){LeMatch.setVisible(true); noError = false;}
        else{LeMatch.setVisible(false);}
        
        if(noError){
            try{
                String sql = "INSERT INTO user (username,password)" +
                    "VALUES (?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, pass);
                ps.executeUpdate();
                Alert success = new Alert(Alert.AlertType.CONFIRMATION, name+" registered successfully!", ButtonType.OK);
                success.setTitle("Success!");
                success.showAndWait();
                Stage Current = (Stage) Bsubmit.getScene().getWindow();
                Current.close();
            }catch(SQLException e){
                Fn.showError(e);
            }
        }
    }
    
}
