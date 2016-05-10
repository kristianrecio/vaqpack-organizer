package vaqpackorganizer;


import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Roberto
 */

public class MonthlySchedule {
    
    
    
    private Tab tab;
    VBox rootPane = new VBox(15);
    VBox TextFields = new VBox();
    TimeTicks timeticks = new TimeTicks(15);
    private Schedule schedule = new Schedule();
    private SendEMail sendMail = new SendEMail();
    private PieChartAnimation pieChartAnimation = new PieChartAnimation();
    private PieChart pieChart;
    private String fileType;
    
    String eventTextArea = "";
    public void setCalendarTab() {
        tab = new Tab();
        tab.setText("Calendar");
        setCalendar();
        setPieChart();
        
        HBox hBox = new HBox();
        hBox.getChildren().addAll(rootPane, pieChart);
        tab.setContent(hBox);
    } 
    
    public void setPieChart() {
        pieChartAnimation.setChart();
        pieChart = pieChartAnimation.getChart();
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
                sendEmail();
            });
            
            //add textfields and labels to TextFields Pane
            TextFields.getChildren().addAll(nameLabel, eventName, timeStartLabel, eventTimeStart, timeEndLabel, 
                    eventTimeEnd, placeLabel, eventPlace, reminderLabel, cb, descriptionLabel, eventDescription, eventBtn, sendEmail);
            
            //add calendar, button and textfields pane
            rootPane.getChildren().addAll(popupContent, TextFields);
            rootPane.setPadding(new Insets(10, 10, 10, 10));
            rootPane.setSpacing(10);
    }
    
    public void sendEmail() {
        Alert alertSendEMail = new Alert(AlertType.CONFIRMATION);
        alertSendEMail.setTitle("Send Events via E-mail");
        alertSendEMail.setHeaderText("Send Events via E-mail");
        alertSendEMail.setContentText("Please select which file type to: ");
        
        GridPane pane1 = new GridPane();
        
        Button htmlBtn = new Button("HTML");
        Button textBtn = new Button("Text");
        
        pane1.add(htmlBtn, 0, 0);
        pane1.add(textBtn, 1, 0);
        
        alertSendEMail.getDialogPane().setContent(pane1);
        
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alertSendEMail.getButtonTypes().add(cancel);
        
        VBox vBox = new VBox();
        
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        ArrayList<Event> events = Main_FX.person.getEvents();
        ArrayList<String> eventNames = new ArrayList<>();
        
        for (int i = 0; i < events.size(); i++) {
            eventNames.add(events.get(i).getName());
            checkBoxes.add(new CheckBox(eventNames.get(i)));
        }
        
        for (int i = 0; i < checkBoxes.size(); i++) {
            vBox.getChildren().add(checkBoxes.get(i));
        }
        
        Button ok = new Button("OK");
        vBox.getChildren().add(ok);
        
        GridPane pane2 = new GridPane();
        alertSendEMail.setHeaderText("Choose email to send to: ");
        
        RadioButton rb1 = new RadioButton("My Email: ");
        RadioButton rb2 = new RadioButton("Other Email: ");
        
        Label lb = new Label();
        TextField tf = new TextField();
        tf.setDisable(true);
        
        if (Main_FX.person.getEmail().isEmpty()) {
            lb.setText("No personal email saved.");
            rb1.setDisable(true);
        }
        else
            lb.setText(Main_FX.person.getEmail());
        
        pane2.add(rb1, 0, 0);
        pane2.add(rb2, 0, 1);
        pane2.add(lb, 1, 0);
        pane2.add(tf, 1, 1);
        
        ButtonType send = new ButtonType("Send", ButtonData.OK_DONE);
        
        
        
        htmlBtn.setOnAction(value -> {
            alertSendEMail.getDialogPane().setContent(vBox);
            fileType = "HTML";
        });
        
        textBtn.setOnAction(value -> {
            alertSendEMail.getDialogPane().setContent(vBox);
            fileType = "TEXT";
        });
        
        ArrayList<Event> selectedEvents = new ArrayList<>();
        
        ok.setOnAction(value -> {
            for (int i = 0; i < checkBoxes.size(); i++)
                if (checkBoxes.get(i).isSelected())
                    selectedEvents.add(events.get(i));
            alertSendEMail.getDialogPane().setContent(pane2);
            alertSendEMail.getButtonTypes().add(send);
            alertSendEMail.getDialogPane().lookupButton(send).setDisable(true);
        });
        
        rb1.setOnAction(value -> {
            alertSendEMail.getDialogPane().lookupButton(send).setDisable(false);
        });
        
        rb2.setOnAction(value -> {
            alertSendEMail.getDialogPane().lookupButton(send).setDisable(false);
        });
        
        alertSendEMail.setResultConverter(button -> {
            if (button == send) {
                String email = (rb1.isSelected()) ? Main_FX.person.getEmail() : tf.getText();
                
                SendEMail sendEmail = new SendEMail();
                if (fileType.equals("TEXT"))
                    sendEmail.writeTextFiles(email, selectedEvents);
                else {
                    sendEmail.writeHTMLFiles(email, selectedEvents);
                }
            }
            return null;
        });
        
        alertSendEMail.showAndWait();
    }
    
    public void success() {
        Alert alert = new Alert(AlertType.INFORMATION, "Event Added Successfully", ButtonType.OK);
        alert.showAndWait();
    }
    
    public void showReminder() {
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
        Date date = new Date();
            
        String todayDate = date.toString();
        ArrayList<Event> events = Main_FX.person.getEvents();
        ArrayList<String> reminderList = new ArrayList<>();
            
            
        for (int i = 0; i < Main_FX.person.getEvents().size(); i++) {
            String userEventDate = events.get(i).getDate();
            if (todayDate.charAt(0) == userEventDate.charAt(0)
                    && todayDate.charAt(1) == userEventDate.charAt(1)
                    && todayDate.charAt(2) == userEventDate.charAt(2)
                    && todayDate.charAt(3) == userEventDate.charAt(3)
                    && todayDate.charAt(5) == userEventDate.charAt(5)
                    && todayDate.charAt(6) == userEventDate.charAt(6)
                    && todayDate.charAt(8) == userEventDate.charAt(8)
                    && todayDate.charAt(9) == userEventDate.charAt(9)){
                    
                    reminderList.add(events.get(i).getName() + " " 
                            + events.get(i).getStartTime() + " " 
                            + events.get(i).getEndTime());
                    
                }
            }
            
        
            Alert reminderAlert = new Alert(AlertType.INFORMATION);
            reminderAlert.setTitle("Reminder Dialog");
            reminderAlert.setHeaderText("You have an event!");
            reminderAlert.setContentText("Check Calendar tab for more info");
            
            VBox reminderShowList = new VBox();
            for (int i = 0; i < reminderList.size(); i++) {
            
                Label myLabel = new Label(reminderList.get(i));
                reminderShowList.getChildren().add(myLabel);
            }
            reminderAlert.getDialogPane().setContent(reminderShowList);
        
    }

    public Tab getTab() {
        return tab;
    }
}
