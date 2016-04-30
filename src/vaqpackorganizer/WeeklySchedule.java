package vaqpackorganizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class WeeklySchedule {
    private Tab tab;
    private BorderPane pane;
    private Label name;
    private Label id_num;
    private Label semester;
    private ArrayList<Label> coursesList;
    private ArrayList<Course> courses;
    private VBox top;
    private VBox left;
    private VBox right;
    private VBox bottom;
    private TableView<Row> table;
    private Button addCourse;
    private Button deleteCourse;
    private Button modifyCourse;
    private Button intervalChange;
    private Button changeTheme;
    private Schedule schedule;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    public static boolean lastestAdd = true;
    
    public WeeklySchedule() {
    }
    
    public void setTheTab() {
        conn = Fn.get(conn);
        tab = new Tab();
        tab.setText("Weekly Schedule");
        setPane();
        tab.setContent(pane);
    }
    
    public void setPane() {
        pane = new BorderPane();
        
        // Top border parts
        name = new Label();
        id_num = new Label();
        semester = new Label();
        
        name.setText(Main_FX.person.getName());
        id_num.setText(Main_FX.person.getId_num());
        semester.setText(Main_FX.person.getSemester());
        
        top = new VBox();
        top.getChildren().addAll(name, id_num, semester);
        top.setAlignment(Pos.CENTER);
        
        pane.setTop(top);
        
        // Left border parts
        int buttonSize = 120;
        
        addCourse = new Button("Add A New Course");
        deleteCourse = new Button("Delete A Course");
        modifyCourse = new Button("Modify A Course");
        addCourse.setMinWidth(buttonSize);
        deleteCourse.setMinWidth(buttonSize);
        modifyCourse.setMinWidth(buttonSize);
        
        addCourse.setOnAction(e -> {
            addCourse();
            setTheTable();
            pane.setCenter(table);
            if (lastestAdd)
                updateDatabaseCourse();
            lastestAdd = true;
        });
        
        deleteCourse.setOnAction(e -> {
            deleteCourse();
            setTheTable();
            pane.setCenter(table);
        });
        
        left = new VBox();
        left.getChildren().addAll(addCourse, deleteCourse, modifyCourse);
        left.setSpacing(10);
        left.setPadding(new Insets(0, 10, 0, 10));
        pane.setLeft(left);
        
        // Right border parts
        intervalChange = new Button("Change Interval");
        changeTheme = new Button("Change Theme");
        intervalChange.setMinWidth(buttonSize);
        changeTheme.setMinWidth(buttonSize);
        
        right = new VBox();
        right.getChildren().addAll(intervalChange, changeTheme);
        right.setSpacing(10);
        right.setPadding(new Insets(0, 10, 0, 10));
        pane.setRight(right);
        
        // Bottom border parts
        setCoursesList();
        bottom = new VBox();
        if (!coursesList.isEmpty())
            for (int i = 0; i < coursesList.size(); i++)
                bottom.getChildren().add(coursesList.get(i));
        bottom.setAlignment(Pos.CENTER);
        
        // Center border parts
        setTheTable();
        pane.setCenter(table);
    }
    
    public void setTheTable() {
        table = new TableView<>();
        
        int dayColumnsSize = 137;
        
        TableColumn<Row, String> timeColumn = new TableColumn<>("");
        timeColumn.setMaxWidth(60);
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        TableColumn<Row, String> monColumn = new TableColumn<>("Monday");
        //monColumn.setMinWidth(dayColumnsSize);
        monColumn.setCellValueFactory(new PropertyValueFactory<>("monday"));
        
        TableColumn<Row, String> tuesColumn = new TableColumn<>("Tuesday");
        //tuesColumn.setMinWidth(dayColumnsSize);
        tuesColumn.setCellValueFactory(new PropertyValueFactory<>("tuesday"));
        
        TableColumn<Row, String> wedColumn = new TableColumn<>("Wednesday");
        //wedColumn.setMinWidth(dayColumnsSize);
        wedColumn.setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        
        TableColumn<Row, String> thurColumn = new TableColumn<>("Thursday");
        //thurColumn.setMinWidth(dayColumnsSize);
        thurColumn.setCellValueFactory(new PropertyValueFactory<>("thursday"));
        
        TableColumn<Row, String> friColumn = new TableColumn<>("Friday");
        //friColumn.setMinWidth(dayColumnsSize);
        friColumn.setCellValueFactory(new PropertyValueFactory<>("friday"));
        
        table.getColumns().addAll(timeColumn, monColumn, tuesColumn, wedColumn, thurColumn, friColumn);
        table.setItems(getTimes());
        BorderPane.setAlignment(table, Pos.TOP_CENTER);
        
        for (int i = 0; i < table.getColumns().size(); i++) {
            if (i != 0)
                table.getColumns().get(i).setMinWidth(dayColumnsSize);
            table.getColumns().get(i).setStyle("-fx-alignment: CENTER;");
            table.getColumns().get(i).setSortable(false);
            table.getColumns().get(i).impl_setReorderable(false);
        }
    }
    
    public ObservableList<Row> getTimes() {
        ObservableList rowValues = FXCollections.observableArrayList();
        schedule = new Schedule();
        schedule.generateSchedule();
        Row[] rows = new Row[schedule.getTimeIntervals().length];
        int timeIncrement = 0;
        
        try {
            sql = "SELECT * FROM user WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Main_FX.person.getUserId());
            rs = ps.executeQuery();
            while (rs.next())
                timeIncrement = Integer.parseInt(rs.getString("TimeIncrement"));
        } catch (SQLException e) {
            Fn.showError(e);
        }
        
        switch (timeIncrement) {
            case 0:
                initializeRows0(schedule, rows);
                for (int i = 0; i < rows.length; i += 4)
                    rowValues.add(rows[i]);
                break;
            case 15:
                initializeRows15(schedule, rows);
                for (int i = 0; i < rows.length; i++)
                    rowValues.add(rows[i]);
                break;
            case 30: 
                initializeRows30(schedule, rows);
                for (int i = 0; i < rows.length; i += 2)
                    rowValues.add(rows[i]);
                break;
        }
        
        return rowValues;
    }
    
    public void initializeRows0(Schedule schedule, Row[] rows) {
        String day;
        int cell;
        courses = Main_FX.person.getCourses();
        
        for(int i = 0; i < rows.length; i += 4) {
            rows[i] = new Row();
            rows[i].setTime(schedule.getTimeIntervals()[i]);
            
            if (Main_FX.person.getCourses().isEmpty())
                rows[i].setAllDays("none");
            else
               for (int j = 0; j < 5; j++) {
                   cell = schedule.getCoursesPlace()[i][j];
                   day = (cell == -1) ? "none" : courses.get(cell).getTableInfo();
                   switch (j) {
                       case 0: rows[i].setMonday(day); break;
                       case 1: rows[i].setTuesday(day); break;
                       case 2: rows[i].setWednesday(day); break;
                       case 3: rows[i].setThursday(day); break;
                       case 4: rows[i].setFriday(day); break;
                   }
               }
        }
    }
    
    public void initializeRows15(Schedule schedule, Row[] rows) {
        String day;
        int cell;
        courses = Main_FX.person.getCourses();
        
        for(int i = 0; i < rows.length; i++) {
            rows[i] = new Row();
            rows[i].setTime(schedule.getTimeIntervals()[i]);
            
            if (Main_FX.person.getCourses().isEmpty())
                rows[i].setAllDays("none");
            else
               for (int j = 0; j < 5; j++) {
                   cell = schedule.getCoursesPlace()[i][j];
                   day = (cell == -1) ? "none" : courses.get(cell).getTableInfo();
                   switch (j) {
                       case 0: rows[i].setMonday(day); break;
                       case 1: rows[i].setTuesday(day); break;
                       case 2: rows[i].setWednesday(day); break;
                       case 3: rows[i].setThursday(day); break;
                       case 4: rows[i].setFriday(day); break;
                   }
               }
        }
    }
    
    public void initializeRows30(Schedule schedule, Row[] rows) {
        String day;
        int cell;
        courses = Main_FX.person.getCourses();
        
        for(int i = 0; i < rows.length; i += 2) {
            rows[i] = new Row();
            rows[i].setTime(schedule.getTimeIntervals()[i]);
            
            if (Main_FX.person.getCourses().isEmpty())
                rows[i].setAllDays("none");
            else
               for (int j = 0; j < 5; j++) {
                   cell = schedule.getCoursesPlace()[i][j];
                   day = (cell == -1) ? "none" : courses.get(cell).getTableInfo();
                   switch (j) {
                       case 0: rows[i].setMonday(day); break;
                       case 1: rows[i].setTuesday(day); break;
                       case 2: rows[i].setWednesday(day); break;
                       case 3: rows[i].setThursday(day); break;
                       case 4: rows[i].setFriday(day); break;
                   }
               }
        }
    }
            
    public void setCoursesList() {
        Course course;
        courses = Main_FX.person.getCourses();
        String string;
        coursesList = new ArrayList<>();
        if (Main_FX.person.getCourses().isEmpty())
            return;
        for (int i = 0; i < Main_FX.person.getCourses().size(); i++) {
            course = courses.get(i);
            string = course.getListInfo();
            coursesList.add(new Label(string));
       }
    }
    
    public Tab getTab() {
        return tab;
    }
    
    public void addCourse() {
        Dialog<Course> dialog = new Dialog<>();
        dialog.setTitle("Add a New Course.");
        dialog.setHeaderText(null);
        
        GridPane dialogPane = new GridPane();
        dialogPane.setHgap(10);
        dialogPane.setVgap(10);
        
        Label prefixLb = new Label("Prefix: ");
        Label numberLb = new Label("Number: ");
        Label descriptionLb = new Label("Description: ");
        Label locationLb = new Label("Location: ");
        Label daysLb = new Label("Days: ");
        Label startTimeLb = new Label("Start Time: ");
        Label endTimeLb = new Label("End Time");
        
        TextField prefixTf = new TextField();
        TextField numberTf = new TextField();
        TextField descriptionTf = new TextField();
        TextField locationTf = new TextField();
        
        ComboBox daysCb = new ComboBox();
        ComboBox startTimeCb = new ComboBox();
        ComboBox endTimeCb = new ComboBox();
        
        ObservableList<String> days = FXCollections.observableArrayList();
        days.addAll("M", "T", "W", "TH", "F", "MW", "TR");
        
        daysCb.setItems(days);
        
        ObservableList<String> times = FXCollections.observableArrayList();
        times.addAll(Arrays.asList(schedule.getTimeIntervals()));
        
        startTimeCb.setItems(times);
        endTimeCb.setItems(times);
        
        dialogPane.add(prefixLb, 0, 0);
        dialogPane.add(numberLb, 0, 1);
        dialogPane.add(descriptionLb, 0, 2);
        dialogPane.add(locationLb, 0, 3);
        dialogPane.add(daysLb, 0, 4);
        dialogPane.add(startTimeLb, 0, 5);
        dialogPane.add(endTimeLb, 0, 6);
        
        dialogPane.add(prefixTf, 1, 0);
        dialogPane.add(numberTf, 1, 1);
        dialogPane.add(descriptionTf, 1, 2);
        dialogPane.add(locationTf, 1, 3);
        dialogPane.add(daysCb, 1, 4);
        dialogPane.add(startTimeCb, 1, 5);
        dialogPane.add(endTimeCb, 1, 6);
        
        dialog.getDialogPane().setContent(dialogPane);
        
        ButtonType done = new ButtonType("Done", ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        dialog.getDialogPane().getButtonTypes().addAll(cancel, done);
        
        dialog.setResultConverter((ButtonType b) -> {
            if (b == done) {
                return new Course(prefixTf.getText(), numberTf.getText(),
                        descriptionTf.getText(), locationTf.getText(),
                        daysCb.getSelectionModel().getSelectedItem().toString(),
                        startTimeCb.getSelectionModel().getSelectedItem().toString(),
                        endTimeCb.getSelectionModel().getSelectedItem().toString());
            }
            return null;
        });
        
        Optional<Course> result = dialog.showAndWait();
        if (result.isPresent())
            Main_FX.person.getCourses().add(result.get());
    }
    
    public void deleteCourse() {        
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Delete course");
        dialog.setHeaderText("Choose a course to delete");
        
        GridPane grid = new GridPane();
        
        ComboBox comboBox = new ComboBox();
        ObservableList<String> comboBoxList = FXCollections.observableArrayList();
        for (Label course : coursesList)
            comboBoxList.add(course.getText());
        comboBox.setItems(comboBoxList);
        
        Label coursesLb = new Label("Courses: ");
        
        grid.add(coursesLb, 0, 0);
        grid.add(comboBox, 0, 1);
        
        ButtonType done = new ButtonType("Done", ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(done, cancel);
        
        dialog.setResultConverter(button -> {
            if (button == done) {
                return comboBox.getSelectionModel().getSelectedIndex();
            }
            return null;
        });
        
        Optional result = dialog.showAndWait();
        if (result.isPresent()) {
            courses.remove(comboBox.getSelectionModel().getSelectedIndex());
            String user_id = Integer.toString(Main_FX.person.getUserId());
            sql = "DELETE FROM course WHERE user_id='" + user_id + "';";
            try {
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
            } catch (SQLException e) {
                Fn.showError(e);
            }
        }
    }
    
    public void modifyCourse() {
        
    }
    
    public void changeInterval() {
        
    }
    
    public void changeTheme() {
        
    }
    
    public void updateDatabaseCourse() {
        int num = courses.size() - 1;
        Course course = courses.get(num);
        
        String prefix = course.getPrefix();
        String number = course.getNumber();
        String description = course.getDescription();
        String location = course.getLocation();
        String days = course.getDays();
        String startTime = course.getStartTime();
        String endTime = course.getEndTime();
        
        String user_id = Integer.toString(Main_FX.person.getUserId());
        
        try {
            sql = "INSERT INTO course (user_id, prefix, number, startTime, endTime, description, location, days)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user_id);
            ps.setString(2, prefix);
            ps.setString(3, number);
            ps.setString(4, startTime);
            ps.setString(5, endTime);
            ps.setString(6, description);
            ps.setString(7, location);
            ps.setString(8, days);
            ps.executeUpdate();
        } catch (SQLException e) {
            Fn.showError(e);
        }
    }
}
