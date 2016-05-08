package vaqpackorganizer;


import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import javafx.beans.binding.BooleanBinding;
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
    
    
    
    private Tab tab;
    HBox rootPane = new HBox(2);
    VBox TextFields = new VBox();
    TimeTicks timeticks = new TimeTicks(15);
    private Schedule schedule = new Schedule();
    private SendEMail sendMail = new SendEMail();
    
    public void setCalendarTab() {
        tab = new Tab();
        tab.setText("Calendar");
        setCalendar();
        tab.setContent(rootPane);
    } 
    
 
    
    
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
            eventBtn.disableProperty().bind(eventName.textProperty().isEmpty()
                                            .or(eventTimeStart.valueProperty().isNull())
                                            .or(eventTimeEnd.valueProperty().isNull())
                                            .or(eventPlace.textProperty().isEmpty()));
            eventBtn.setOnAction((ActionEvent e) -> {
                
                        String Event_Name = eventName.getText();
                        String Event_Time_Start = eventTimeStart.getSelectionModel().getSelectedItem().toString();
                        String Event_Time_End = eventTimeEnd.getSelectionModel().getSelectedItem().toString();
                        String Event_Place = eventPlace.getText();
                        LocalDate theDate = datePicker.getValue();
                        Date Event_Date = new Date(theDate.toEpochDay()); //type java.sql.Date
                        String reminderYesNo = cb.getSelectionModel().getSelectedItem().toString();
                        
                        schedule.generateEventSchedule();
                        Event event = new Event(Event_Name, Event_Time_Start, Event_Time_End, Event_Place, Event_Date, reminderYesNo);
                        Main_FX.person.getEvents().add(event);
                        if (schedule.isThereEventTimeConflict(Main_FX.person.getEvents().size() - 1)) {
                            schedule.timeConflictAlert(2);
                            Main_FX.person.getEvents().remove(Main_FX.person.getEvents().size() - 1);
                        } else if (schedule.isThereEventCourseConflict(Main_FX.person.getEvents().size() - 1)) {
                            schedule.timeConflictAlert(3);
                            Main_FX.person.getEvents().remove(Main_FX.person.getEvents().size() - 1);
                        }
                        else
                            Main_FX.Database.addEvent(Event_Name, Event_Time_Start, Event_Time_End,Event_Place, Event_Date, reminderYesNo);
                    
                
            });
            
            Button sendEmail = new Button();
            sendEmail.setText("Send reminder e-mail");
            sendEmail.setOnAction((ActionEvent e) -> {
                
                if(Main_FX.person.getEmail().equals("")) {
                    Alert alertEMail = new Alert(Alert.AlertType.ERROR, "No e-mail found" + ButtonType.OK);
                    alertEMail.showAndWait();
                }
                
                else if(Main_FX.person.getEmail().equals()) { //need to fix this by saying that user has an email.
                    Alert fileChoice = new Alert(Alert.AlertType.CONFIRMATION, "Do you want me to send you a text file?" + ButtonType.YES + ButtonType.NO);
                    fileChoice.showAndWait();
                    sendMail.writeTextFiles();
                }
                
                else {
                    Alert fileChoice = new Alert(Alert.AlertType.CONFIRMATION, "Do you want me to send you an HTML file?" + ButtonType.YES + ButtonType.NO);
                    fileChoice.showAndWait();
                    sendMail.writeHTMLFiles();
                }
                
            });
            
            //create a list with all events on a day
            Label blankSpace = new Label(" ");
            Label eventThisDay = new Label(" Events today: ");
            TextArea printEvents = new TextArea(); //need to put database data here.
            //end
            
            //add textfields and labels to TextFields Pane
            TextFields.getChildren().addAll(nameLabel, eventName, timeStartLabel, eventTimeStart, timeEndLabel, 
                    eventTimeEnd, placeLabel, eventPlace, reminderLabel, cb, eventBtn, blankSpace, sendEmail, eventThisDay, printEvents);
            
            //add calendar, button and textfields pane
            rootPane.getChildren().addAll(popupContent, TextFields);
    
    }

    public Tab getTab() {
        return tab;
    }
}