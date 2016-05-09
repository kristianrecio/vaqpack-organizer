package vaqpackorganizer;


import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
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
    VBox rootPane = new VBox(15);
    VBox TextFields = new VBox();
    TimeTicks timeticks = new TimeTicks(15);
    private Schedule schedule = new Schedule();
    private SendEMail sendMail = new SendEMail();
    private PieChartAnimation pieChartAnimation = new PieChartAnimation();
    private PieChart pieChart;
    
    public void setCalendarTab() {
        tab = new Tab();
        tab.setText("Calendar");
        setCalendar();
        setPieChart();
        tab.setContent(rootPane);
    } 
    
    public void setPieChart() {
        pieChartAnimation.setChart();
        pieChart = pieChartAnimation.getChart();
        
        rootPane.getChildren().add(pieChart);
    }
    
    public void updatePieChart() {
        pieChartAnimation.updatePieChart();
        pieChart = pieChartAnimation.getChart();
    }
    
    public void setCalendar() {
            
            
            //dimensions for rootPane
            rootPane.setMinWidth(400);
            rootPane.setMaxWidth(400);
            rootPane.setMinHeight(400);
            rootPane.setMaxHeight(400);
            //end
            
            Scene scene = new Scene(rootPane, 1500, 950);

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
            
            Label descriptionLabel = new Label("Brief description of the event: ");
            TextField eventDescription = new TextField();
            //end of labels and text fields
            
            //choice box for reminder
            Label reminderLabel = new Label("Would you like to set a reminder?");
            ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList("Yes", "No"));
            cb.setTooltip(new Tooltip("Select Yes or No"));
            //end
            
            
            
            Button eventBtn = new Button();
            eventBtn.setMinSize(200, 30);
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
                        String Event_Description = eventDescription.getText();
                        LocalDate theDate = datePicker.getValue();
                        //Date Event_Date = new Date(theDate.toEpochDay()); //type java.sql.Date
                        String reminderYesNo = cb.getSelectionModel().getSelectedItem().toString();
                        
                        schedule.generateEventSchedule();
                        Event event = new Event(Event_Name, Event_Time_Start, Event_Time_End, Event_Place, theDate, reminderYesNo, Event_Description);
                        Main_FX.person.getEvents().add(event);
                        if (schedule.isThereEventTimeConflict(Main_FX.person.getEvents().size() - 1)) {
                            schedule.timeConflictAlert(2);
                            Main_FX.person.getEvents().remove(Main_FX.person.getEvents().size() - 1);
                        } else if (schedule.isThereEventCourseConflict(Main_FX.person.getEvents().size() - 1)) {
                            schedule.timeConflictAlert(3);
                            Main_FX.person.getEvents().remove(Main_FX.person.getEvents().size() - 1);
                        }
                        else {
                            Main_FX.Database.addEvent(event);
                            success();
                            updatePieChart();
                        }
                    
                
            });
            
            Button sendEmail = new Button();
            sendEmail.setText("Send reminder e-mail");
            sendEmail.setOnAction((ActionEvent e) -> {
                    Alert alertSendEMail = new Alert(AlertType.CONFIRMATION);
                    alertSendEMail.setTitle("Send reminder via E-mail");
                    alertSendEMail.setHeaderText("Sed Reminder via E-mail");
                    alertSendEMail.setContentText("Please select an option: ");
                    
                    ButtonType sendHTML = new ButtonType("Send HTML file");
                    ButtonType sendText = new ButtonType("Send text file");
                    ButtonType addNewMail = new ButtonType("Add another E-mail");
                    ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                    
                    alertSendEMail.getButtonTypes().setAll(sendHTML, sendText, addNewMail, cancel);
                    
                    Optional<ButtonType> result = alertSendEMail.showAndWait();
                    if(result.get() == sendHTML) { //need to fix this
                        
                    }
                    else if(result.get() == sendText) {
                        sendMail.writeTextFiles();
                    }
                    else if(result.get() == addNewMail) {
                        sendMail.writeHTMLFiles();
                    }
                    else{
                        
                    }
                
                
            });
            
            //create a list with all events on a day
            Label blankSpace = new Label(" ");
            Label eventThisDay = new Label(" Events today: ");
            TextArea printEvents = new TextArea(); //need to put database data here.
            printEvents.prefHeight(100);
            printEvents.prefWidth(300);
            //end
            
            //add textfields and labels to TextFields Pane
            TextFields.getChildren().addAll(nameLabel, eventName, timeStartLabel, eventTimeStart, timeEndLabel, 
                    eventTimeEnd, placeLabel, eventPlace, reminderLabel, descriptionLabel, eventDescription, cb, eventBtn, blankSpace, sendEmail, eventThisDay, printEvents);
            
            //add calendar, button and textfields pane
            rootPane.getChildren().addAll(popupContent, TextFields);
            rootPane.setPadding(new Insets(10, 10, 10, 10));
            rootPane.setSpacing(10);
    }
    
    public void success() {
        Alert alert = new Alert(AlertType.INFORMATION, "Event Added Successfully", ButtonType.OK);
        alert.showAndWait();
    }
    
    public void showReminder() {
        
        if(Main_FX.person.getEvents().equals(1)) { //need to fix this
            Alert reminderAlert = new Alert(AlertType.INFORMATION);
            reminderAlert.setTitle("Reminder Dialog");
            reminderAlert.setHeaderText("You have an event!");
            reminderAlert.setContentText("Check Calendar tab for more info");
        }
    }

    public Tab getTab() {
        return tab;
    }
}
