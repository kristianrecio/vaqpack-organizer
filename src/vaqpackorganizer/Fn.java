/*
*   This class is for all general functions.
*/

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
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

/**
 *
 * @author Carlos
 */
public class Fn {
    
    //displays an error message
    public static void showError(Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
        alert.setHeaderText("Error");
        alert.showAndWait();
    }
    
    private static Connection createConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/vaqpack", "root", "Kr7909!?");
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
    
    public static TextField setTextField(double LayoutX, double LayoutY, String pText){
        TextField Fname = new TextField();
        Fname.setLayoutX(LayoutX);
        Fname.setLayoutY(LayoutY);
        Fname.setPromptText(pText);
        return Fname;
    }
    
    public static PasswordField setPasswordField(double LayoutX, double LayoutY, String pText){
        PasswordField Fpass = new PasswordField();
        Fpass.setLayoutX(LayoutX);
        Fpass.setLayoutY(LayoutY);
        Fpass.setPromptText(pText);
        return Fpass;
    }
    
    public static Button setButton(double LayoutX, double LayoutY, String text){
        Button button = new Button();
        button.setLayoutX(LayoutX);
        button.setLayoutY(LayoutY);
        button.setText(text);
        button.setMnemonicParsing(false);
        return button;
    }
    
    public static Label setLabel(double LayoutX,double LayoutY, String text, boolean visible,String color){
        Label label = new Label();
        label.setLayoutX(LayoutX);
        label.setLayoutY(LayoutY);
        label.setText(text);
        label.setTextFill(Paint.valueOf(color));
        label.setVisible(visible);
        return label;
    }
    
    public static MenuItem setMenuItem(String Text){
        MenuItem Mitem = new MenuItem();
        Mitem.setMnemonicParsing(false);
        Mitem.setText(Text);
        return Mitem;
    }
    
    
}
