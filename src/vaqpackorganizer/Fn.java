package vaqpackorganizer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

/**
 *
 * @author Carlos
 */
public class Fn {
    
    public static void showError(Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
        alert.showAndWait();
    }
    
    private static Connection createConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/vaqpack", "root", "root");
        }catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    
    public static Connection get(Connection connection) {
        if(connection == null) {
            connection = createConnection();
        }
        return connection;
    }
    
    public static TextField setTextField(double x, double y, String pText){
        TextField Fname = new TextField();
        Fname.setLayoutX(x);
        Fname.setLayoutY(y);
        Fname.setPromptText(pText);
        return Fname;
    }
    
    public static PasswordField setPasswordField(double x, double y, String pText){
        PasswordField Fpass = new PasswordField();
        Fpass.setLayoutX(x);
        Fpass.setLayoutY(y);
        Fpass.setPromptText(pText);
        return Fpass;
    }
    public static Button setButton(double x, double y, String text){
        Button Blog = new Button();
        Blog.setLayoutX(x);
        Blog.setLayoutY(y);
        Blog.setText(text);
        Blog.setMnemonicParsing(false);
        return Blog;
    }
    public static Label setLabel(double x,double y, String text, boolean vis,String color){
        Label label = new Label();
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setText(text);
        label.setTextFill(Paint.valueOf(color));
        label.setVisible(vis);
        return label;
    }
    
    public static MenuItem setMenuItem(String Text){
        MenuItem Mitem = new MenuItem();
        Mitem.setMnemonicParsing(false);
        Mitem.setText(Text);
        return Mitem;
    }
    
}
