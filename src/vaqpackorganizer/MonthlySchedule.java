package vaqpackorganizer;


import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Calendar;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MonthlySchedule extends Application{
    
       @Override
    public void start(Stage monthlyStage) {
        
        try {
            HBox rootPane = new HBox(2);
            Scene scene = new Scene(rootPane, 1080, 720);
            VBox TextFields = new VBox();
            
            String css = MonthlySchedule.class.getResource("CalendarStyle.css").toExternalForm();
            scene.getStylesheets().add(css);

            DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker());
            Node popupContent = datePickerSkin.getPopupContent();
            
            
            Button eventBtn = new Button();
            eventBtn.setMinSize(200, 25);
            eventBtn.setText("Add Event");
            eventBtn.setOnAction((ActionEvent e) -> {
                
            });
            //Labels and textfields
            Label nameLabel = new Label("Event Name: ");
            TextField eventName = new TextField();
            
            Label timeLabel = new Label("Event Time: ");
            TextField eventTime = new TextField();
            
            Label placeLabel = new Label("Place held: ");
            TextField eventPlace = new TextField();
            //end of labels and text fields
            
            //add textfields and labels to TextFields Pane
            TextFields.getChildren().addAll(nameLabel, eventName, timeLabel, eventTime, placeLabel, eventPlace);
            
            //dimensions for rootPane
            rootPane.setMinWidth(400);
            rootPane.setMaxWidth(400);
            rootPane.setMinHeight(400);
            rootPane.setMaxHeight(400);
            //end
            
            //add calendar, button and textfields pane
            rootPane.getChildren().addAll(popupContent, eventBtn, TextFields);
            
           

            monthlyStage.setScene(scene);
            monthlyStage.setTitle("MonthlyCalendar");
            monthlyStage.show();
            
        }catch(Exception monthlySchedule) {
            monthlySchedule.printStackTrace();
        }
      
    }
}