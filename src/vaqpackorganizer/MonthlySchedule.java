package vaqpackorganizer;


import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MonthlySchedule extends Application{
    
       @Override
    public void start(Stage monthlyStage) {
        
        try {
            HBox rootPane = new HBox(2);
            //dimensions for rootPane
            rootPane.setMinWidth(400);
            rootPane.setMaxWidth(400);
            rootPane.setMinHeight(400);
            rootPane.setMaxHeight(400);
            //end
            
            VBox TextFields = new VBox();
            Scene scene = new Scene(rootPane, 1080, 720);
            
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
            
            //choice box for reminder
            Label reminderLabel = new Label("Would you like to set a reminder?");
            ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList("Yes", "No"));
            cb.setTooltip(new Tooltip("Select Yes or No"));
            //end
            
            //add textfields and labels to TextFields Pane
            TextFields.getChildren().addAll(nameLabel, eventName, timeLabel, eventTime, placeLabel, eventPlace,reminderLabel, cb, eventBtn);
            
            //add calendar, button and textfields pane
            rootPane.getChildren().addAll(popupContent, TextFields);
            
            monthlyStage.setScene(scene);
            monthlyStage.setTitle("Monthly Calendar");
            monthlyStage.show();
            
        }catch(Exception monthlySchedule) {
            monthlySchedule.printStackTrace();
        }
      
    }
}