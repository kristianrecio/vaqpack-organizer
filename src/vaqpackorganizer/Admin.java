/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Kristian Recio
 */
public class Admin {
    private Tab tab;
    private BorderPane pane;
    private ObservableList<String> users;
    private ListView list;
    
    public Admin() {
         users = FXCollections.observableArrayList();
         Main_FX.Database.getItems(users, "user", "Username");
    }
    
    public void setTab() {
        tab = new Tab();
        tab.setText("Admin");
        tab.setStyle("-fx-background-color: red;");
        pane = new BorderPane();
        
        Label userLabel = new Label("Users: ");
        userLabel.setStyle("-fx-font-size: 20;");
        
        list = new ListView();
        list.setItems(users);
        
        Label btnLabel = new Label("Select a user to delete.");
        
        Button delete = new Button("Delete");
        delete.setDisable(true);
        
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(btnLabel, delete);
        
        pane.setTop(userLabel);
        pane.setCenter(list);
        pane.setRight(vBox);
        pane.setPadding(new Insets(10, 10, 10, 10));
        
        
        list.setOnMouseClicked(value -> {
            if (list.getSelectionModel().getSelectedIndex() != -1)
                delete.setDisable(false);
        });
        
        delete.setOnAction(value -> {
            String item = list.getSelectionModel().getSelectedItem().toString();
            System.out.println(item);
            if (item.equals("admin")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error: Deleting Admin User");
                alert.setHeaderText("Error: Admin account cannot be deleted.");
                alert.showAndWait();
            } else
                delete(item);
            refresh();
            delete.setDisable(true);
        });
        
        tab.setContent(pane);
    }
    
    public void delete(String username) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Delete User");
        alert.setHeaderText("Are you sure you want to delete the selected user?");
        
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().add(cancel);
        
        alert.setResultConverter(button -> {
            if (button != cancel) {
                Main_FX.Database.deleteUser(username);
            }
            return button;
        });
        
        alert.showAndWait();
    }
    
    public void refresh() {
        users = FXCollections.observableArrayList();
        Main_FX.Database.getItems(users, "user", "Username");
        list = new ListView(users);
        pane.setCenter(list);
    }
    
    public Tab getTab() {
        return tab;
    }
}
