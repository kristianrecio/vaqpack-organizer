package vaqpackorganizer;


import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MonthlySchedule {
    
    
    private Connection conn;
    PreparedStatement sl;
    private Tab tab;
    HBox rootPane = new HBox(2);
    VBox TextFields = new VBox();
    TimeTicks timeticks = new TimeTicks(15);
    
    public MonthlySchedule(Connection conn){
        this.conn = conn;
    }
    
    public void setCalendarTab() {
        tab = new Tab();
        tab.setText("Calendar");
        setCalendar();
        tab.setContent(rootPane);
    } 
    
    public void setConnection(Connection conn){
        this.conn = conn;
    }
    
    //insert into database
    public void addEvent(String Event_Name, String Event_Time_Start, String Event_Time_End, String Event_Place, Date Event_Date, String Reminder) {
        try{
            String addEvent = "INSERT INTO event(Event_Name, Event_Time_Start, Event_Time_End, Event_Place, Event_Date, Reminder)"
                    +"VALUES (?,?,?,?,?,?,?)";
            sl = conn.prepareStatement(addEvent);
            sl.setString(1, Event_Name);
            sl.setString(2, Event_Time_Start);
            sl.setString(3, Event_Time_End);
            sl.setString(4, Event_Place);
            sl.setDate(5, Event_Date);
            sl.setString(6, Reminder);
            sl.executeUpdate();
                  
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //end
    
    public void setCalendar() {
            
            
            //dimensions for rootPane
            rootPane.setMinWidth(400);
            rootPane.setMaxWidth(400);
            rootPane.setMinHeight(400);
            rootPane.setMaxHeight(400);
            //end
            
            
            Scene scene = new Scene(rootPane, 1080, 720);
            
            String css = MonthlySchedule.class.getResource("CalendarStyle.css").toExternalForm();
            scene.getStylesheets().add(css);

            DatePicker datePicker = new DatePicker();
            DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
            Node popupContent = datePickerSkin.getPopupContent();
            
            //Labels and textfields
            Label nameLabel = new Label("Event Name: ");
            TextField eventName = new TextField();
            
            //combo box with times
            ObservableList<String> options = FXCollections.observableArrayList();
            options.addAll(Arrays.asList(timeticks.generateTicks()));
            
            Label timeStartLabel = new Label("Event Start Time: ");
            ComboBox eventTimeStart = new ComboBox(options);
            
            Label timeEndLabel = new Label("Event End Time: ");
            ComboBox eventTimeEnd = new ComboBox(options);
            //end of combobox
            
            Label placeLabel = new Label("Place held: ");
            TextField eventPlace = new TextField();
            //end of labels and text fields
            
            //choice box for reminder
            Label reminderLabel = new Label("Would you like to set a reminder?");
            ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList("Yes", "No"));
            cb.setTooltip(new Tooltip("Select Yes or No"));
            //end
            
            Button eventBtn = new Button();
            eventBtn.setMinSize(200, 25);
            eventBtn.setText("Add Event");
            eventBtn.setOnAction((ActionEvent e) -> {
                
                if(Main_FX.person.getEmail().equals("")) { 
                        Alert alertEMail = new Alert(Alert.AlertType.ERROR, "No e-mail found" + ButtonType.OK);
                        alertEMail.showAndWait();
                    }
                    
                    else {
                        
                        String Event_Name = eventName.getText();
                        String Event_Time_Start = eventTimeStart.getSelectionModel().getSelectedItem().toString();
                        String Event_Time_End = eventTimeEnd.getSelectionModel().getSelectedItem().toString();
                        String Event_Place = eventPlace.getText();
                        LocalDate theDate = datePicker.getValue();
                        Date Event_Date = new Date(theDate.toEpochDay()); //type java.sql.Date
                        String reminderYesNo = cb.getSelectionModel().getSelectedItem().toString();
                        
                        data.addEvent(Event_Name, Event_Time_Start, Event_Time_End,Event_Place, Event_Date, reminderYesNo);
                    }
                
            });
            
            //create a list with all events on a day
            Label blankSpace = new Label(" ");
            Label eventThisDay = new Label(" Events today: ");
            TextArea printEvents = new TextArea(); //need to put database data here.
            //end
            
            //add textfields and labels to TextFields Pane
            TextFields.getChildren().addAll(nameLabel, eventName, timeStartLabel, eventTimeStart, timeEndLabel, 
                    eventTimeEnd, placeLabel, eventPlace, reminderLabel, cb, eventBtn, blankSpace, eventThisDay, printEvents);
            
            //add calendar, button and textfields pane
            rootPane.getChildren().addAll(popupContent, TextFields);
    
    }

    public Tab getTab() {
        return tab;
    }
}
