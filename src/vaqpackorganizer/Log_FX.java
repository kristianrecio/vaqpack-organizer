package vaqpackorganizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class Log_FX extends Application {
    
    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Label LnoMatch = Fn.setLabel(174,20,"No matching\nusername\nor password.",false,"RED");
    private TextField Fname = Fn.setTextField(8,16,"User name");
    private PasswordField Fpass = Fn.setPasswordField(8,56,"Password");
    private Button Blog = Fn.setButton(8,96,"Log in");
    private Button Bregister = Fn.setButton(200,96,"Register");
    Stage Register = new Stage();
    private Register_FX Register_FX;
    
    @Override
    public void start(Stage primaryStage) {
        conn = Fn.get(conn);
        Register_FX = new Register_FX(conn,Register);
        Blog.setOnAction((ActionEvent event) -> {
            startBlog();
        });
        Bregister.setOnAction((ActionEvent event) -> {
            startBregister();
        });
        AnchorPane Login = new AnchorPane();
        Login.setPrefHeight(128);
        Login.setPrefWidth(256);
        Login.getChildren().addAll(Fname,Fpass,Blog,Bregister,LnoMatch);
        
        Scene scene = new Scene(Login, Login.getPrefWidth(), Login.getPrefHeight());
        primaryStage.setTitle("Welcome!");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void startBlog(){
        String username = Fname.getText();
        String password = Fpass.getText();

        String sql = "SELECT * FROM user WHERE username = ? AND BINARY password = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    Stage Current = (Stage) Blog.getScene().getWindow();
                    Current.hide();
                    Stage New = new Stage();
                    Main_FX Main = new Main_FX(rs.getInt("id"),conn,New);
                    Current.close();
                }
                else{
                    LnoMatch.setVisible(true);
                }
            }catch(SQLException e){
                Fn.showError(e);
            }
    }
    
    public void startBregister(){
        Register.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
