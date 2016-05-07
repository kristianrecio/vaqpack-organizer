package vaqpackorganizer;

import java.sql.Connection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class Main_FX extends Application {
    private void setTSchedule(){
        WeeklySchedule s = new WeeklySchedule(id,Database,conn); 
        s.setTheTab();
        TSchedule = s.getTab();
    }
    private void setMSchedule(){
        MonthlySchedule e = new MonthlySchedule();
        e.setCalendarTab();
        MSchedule = e.getTab();
    }
    
    private void setTInfo(Major info){
        Information i = new Information(info,person, Database);
        i.setTab();
        TInfo = i.getTab();
    }

    private Tab MSchedule;
    private Tab TSchedule;
    private Tab TInfo;
    private MenuItem MIout = Fn.setMenuItem("Log out");
    private MenuItem MIclose = Fn.setMenuItem("Close");
    private MenuItem MIedit = Fn.setMenuItem("Edit user information");
    private final int id;
    private final Connection conn;
    public static Person person;
    public static String theme;
    public static Scene scene;
    
    public Database Database;
    
    public Main_FX(int id, int theme_id, Connection conn,Stage primaryStage){
        this.id = id;
        this.conn = conn;
        Database = new Database(conn);
        theme = Database.getString("theme",theme_id,"name");
        person = new Person(id, Database.getInt("user",id,"major_id"), conn, Database);
        setTSchedule();
        setMSchedule();
        Major info = new Major(id,Database.getInt("user",id,"major_id"), Database);
        setTInfo(info);
        start(primaryStage);
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        
        MIout.setOnAction((ActionEvent event) -> { 
            startMIout(primaryStage);
        });
        MIclose.setOnAction((ActionEvent event) -> { 
            primaryStage.close();
        });
        MIedit.setOnAction((ActionEvent event) -> {
            Stage Userinfo = new Stage();
            Userinfo_FX edit = new Userinfo_FX(conn,person,Userinfo);
        });
        
        AnchorPane Main = setMain();
        scene = new Scene(Main, Main.getPrefWidth(), Main.getPrefHeight());
        System.out.println(theme);
        scene.getStylesheets().add(getClass().getResource(theme).toExternalForm());
        primaryStage.setTitle("VaqPack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void startMIout(Stage Current){
        Current.hide();
        Stage New = new Stage();
        Log_FX Login = new Log_FX();
        Login.start(New);
        Current.close();
    }
    
    public AnchorPane setMain(){
        AnchorPane Main = new AnchorPane();
        Main.setPrefHeight(500);
        Main.setPrefWidth(1050);
        BorderPane Bpane = new BorderPane();
        Bpane.setPrefHeight(Main.getPrefHeight());
        Bpane.setPrefWidth(Main.getPrefWidth());
        Bpane.setLayoutX(200);
        
        MenuBar Mbar = new MenuBar();
        Menu Moptions = new Menu();
        Moptions.setText("Options");
        Moptions.getItems().addAll(MIout,MIclose, MIedit);
        Mbar.getMenus().add(Moptions);
        Bpane.setTop(Mbar);
        
        TabPane Tpane = new TabPane();
        Tpane.setPrefHeight(Main.getPrefHeight());
        Tpane.setPrefWidth(Main.getPrefWidth());
        Tpane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        Tpane.getTabs().addAll(TSchedule,MSchedule, TInfo);
        Bpane.setCenter(Tpane);
        
        Main.setTopAnchor(Bpane,0.0);
        Main.setBottomAnchor(Bpane,0.0);
        Main.setLeftAnchor(Bpane,0.0);
        Main.setRightAnchor(Bpane,0.0);
        Main.getChildren().add(Bpane);
        return Main;
    }
    
}
