/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Kristian Recio
 */
public class Admin {
    private Tab tab;
    private GridPane pane;
    private TableView table;
    private ListView list;
    private ObservableList<Data> data;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    public Admin() {
    }
    
    public void setTab() {
        tab = new Tab();
        pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(25);
        pane.setPadding(new Insets(10, 10, 10, 10));
        
        Label label = new Label("Users (Id, Username): ");
        label.setStyle("-fx-font-size: 20;");
        
        list = new ListView();
        list.setItems(getData());
        
        Button delete = new Button("Delete");
        delete.setDisable(true);
        Label delLb = new Label("Select a user: ");
        
        pane.add(label, 0, 0);
        pane.add(list, 0, 1);
        pane.add(delLb, 1, 0);
        pane.add(delete, 1, 1);
        
        GridPane.setValignment(delete, VPos.TOP);
        
        list.setOnMouseClicked(value -> {
            if (list.getSelectionModel().getSelectedIndex() == -1)
                delete.setDisable(true);
            else
                delete.setDisable(false);
        });
        
        delete.setOnAction(value -> {
            
        });
        
        tab.setText("Admin");
        tab.setContent(pane);
    }
    
    public void setTheTable() {
        table = new TableView();
        
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn username = new TableColumn("Username");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        
        table.getColumns().addAll(id, username);
        table.setItems(getData());
    }
    
    public ObservableList<Data> getData() {
        data = FXCollections.observableArrayList();
        
        String id;
        String name;
        
        conn = Fn.get(conn);
        
        try {
            sql = "SELECT * FROM user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                id = rs.getString("id");
                name = rs.getString("username");
                Data d = new Data(id, name);
                data.add(d);
            }
        } catch (SQLException e) {
            Fn.showError(e);
        }
        
        return data;
    }
    
    public Tab getTab() {
        return tab;
    }
}
