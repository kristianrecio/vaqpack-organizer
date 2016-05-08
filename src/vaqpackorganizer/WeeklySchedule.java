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
import javafx.scene.chart.PieChart;
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
    private int id;
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
    private Database Database;
    public static boolean success = true;
    private PieChart pieChart;
    
    public WeeklySchedule(int id, Database Database, Connection conn) {
        this.id = id;
        this.Database = Database;
        this.conn = conn;
    }
    
    public void setTheTab() {
        tab = new Tab();
        tab.setText("Weekly Schedule");
        setPane();
        tab.setContent(pane);
    }
    
    public void setPane() {
        pane = new BorderPane();
        
        setTheTop();
        setTheLeft();
        setTheCenter();
        setTheRight();
        setTheBottom();
    }
    
    public void setTheTop() {
        name = new Label();
        id_num = new Label();
        semester = new Label();
        
        name.setText(Main_FX.person.getName());
        id_num.setText(Main_FX.person.getId_num());
        semester.setText(Main_FX.person.getSemester());
        
        name.setStyle("-fx-font-size: 20;");
        
        top = new VBox();
        top.getChildren().addAll(name, id_num, semester);
        top.setAlignment(Pos.CENTER);
        
        pane.setTop(top);
    }
    
    public void setTheLeft() {
        int buttonSize = 120;
        
        addCourse = new Button("Add A New Course");
        deleteCourse = new Button("Delete A Course");
        modifyCourse = new Button("Modify A Course");
        addCourse.setMinWidth(buttonSize);
        deleteCourse.setMinWidth(buttonSize);
        modifyCourse.setMinWidth(buttonSize);
        
        addCourse.setOnAction(e -> {
            addCourse();
            if (success) {
                addCourseToDatabase();
                Main_FX.person.generateCourses();
                setTheTable();
                pane.setCenter(table);
                setTheBottom();
            }
            success = true;
            updatePieChart();
        });
        
        deleteCourse.setOnAction(e -> {
            deleteCourse();
            setTheTable();
            pane.setCenter(table);
            setTheBottom();
            updatePieChart();
        });
        
        modifyCourse.setOnAction(e -> {
            modifyCourse();
            if (success) {
                setTheTable();
                pane.setCenter(table);
                setTheBottom();
                updatePieChart();
            }
            success = true;
        });
        
        left = new VBox();
        left.getChildren().addAll(addCourse, deleteCourse, modifyCourse);
        left.setSpacing(10);
        left.setPadding(new Insets(0, 10, 0, 10));
        pane.setLeft(left);
    }
    
    public void setTheCenter() {
        setTheTable();
        pane.setCenter(table);
    }
    
    public void setTheRight() {
        int buttonSize = 120;
        
        intervalChange = new Button("Change Interval");
        changeTheme = new Button("Change Theme");
        intervalChange.setMinWidth(buttonSize);
        changeTheme.setMinWidth(buttonSize);
        
        intervalChange.setOnAction(e -> {
            changeInterval();
            setTheTable();
            pane.setCenter(table);
        });
        
        changeTheme.setOnAction(e -> {
            changeTheme();
        });
        
        pieChart = new PieChart(setData());
        
        right = new VBox();
        right.getChildren().addAll(intervalChange, changeTheme, pieChart);
        right.setSpacing(10);
        right.setPadding(new Insets(0, 10, 0, 10));
        right.setMaxWidth(100);
        pane.setRight(right);
    }
    
    public void setTheBottom() {
        setCoursesList();
        bottom = new VBox();
        Label label;
        if (!coursesList.isEmpty())
            for (int i = 0; i < coursesList.size(); i++) {
                label = new Label();
                label.setText("\t\t\t\t\t\t\t" + coursesList.get(i).getText());
                bottom.getChildren().add(label);
            }
        bottom.setAlignment(Pos.CENTER_LEFT);
        bottom.setPadding(new Insets(10, 10, 10, 10));
        pane.setBottom(bottom);
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
        schedule.generateCourseSchedule();
        Row[] rows = new Row[schedule.getTimeIntervals().length];
        int timeIncrement = 0;
        
        try {
            sql = "SELECT * FROM user WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Main_FX.person.getUserId());
            rs = ps.executeQuery();
            while (rs.next())
                timeIncrement = Integer.parseInt(rs.getString("time_increment"));
        } catch (SQLException e) {
            Fn.showError(e);
        }
        
        initializeRows(rows, timeIncrement);
        int inc = 0;
        switch (timeIncrement) {
            case 0: inc = 4; break;
            case 15: inc = 1; break;
            case 30: inc = 2; break;
        }
        
        for (int i = 0; i < rows.length; i += inc)
            rowValues.add(rows[i]);
        
        return rowValues;
    }
    
    public void initializeRows(Row[] rows, int timeIncrement) {
        String day;
        int cell;
        int inc = 1;
        courses = Main_FX.person.getCourses();
        
        switch (timeIncrement) {
            case 0: inc = 4; break;
            case 15: inc = 1; break;
            case 30: inc = 2; break;
        }
        
        for(int i = 0; i < rows.length; i += inc) {
            rows[i] = new Row();
            rows[i].setTime(schedule.getTimeIntervals()[i]);
            
            if (Main_FX.person.getCourses().isEmpty())
                rows[i].setAllDays("");
            else
               for (int j = 0; j < 5; j++) {
                   cell = schedule.getCoursesPlace()[i][j];
                   day = (cell == -1) ? "" : courses.get(cell).getTableInfo();
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
        courses = Main_FX.person.getCourses();
        coursesList = new ArrayList<>();
        if (Main_FX.person.getCourses().isEmpty())
            return;
        for (int i = 0; i < Main_FX.person.getCourses().size(); i++) {
            String string = courses.get(i).getListInfo();
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
        dialog.getDialogPane().getStylesheets().add(getClass().getResource(Main_FX.theme).toExternalForm());
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
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
        
        grid.add(prefixLb, 0, 0);
        grid.add(numberLb, 0, 1);
        grid.add(descriptionLb, 0, 2);
        grid.add(locationLb, 0, 3);
        grid.add(daysLb, 0, 4);
        grid.add(startTimeLb, 0, 5);
        grid.add(endTimeLb, 0, 6);
        
        grid.add(prefixTf, 1, 0);
        grid.add(numberTf, 1, 1);
        grid.add(descriptionTf, 1, 2);
        grid.add(locationTf, 1, 3);
        grid.add(daysCb, 1, 4);
        grid.add(startTimeCb, 1, 5);
        grid.add(endTimeCb, 1, 6);
        
        ButtonType done = new ButtonType("Done", ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(done, cancel);
        dialog.getDialogPane().lookupButton(done).setDisable(true);
        
        prefixTf.setOnKeyReleased(e -> {
            if (prefixTf.getText().equals("") || numberTf.getText().equals("") ||
                descriptionTf.getText().equals("") || locationTf.getText().equals("") || 
                daysCb.getSelectionModel().getSelectedItem() == null ||
                startTimeCb.getSelectionModel().getSelectedItem() == null ||
                endTimeCb.getSelectionModel().getSelectedItem() == null)
                dialog.getDialogPane().lookupButton(done).setDisable(true);
            else
                dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        numberTf.setOnKeyReleased(e -> {
            if (prefixTf.getText().equals("") || numberTf.getText().equals("") ||
                descriptionTf.getText().equals("") || locationTf.getText().equals("") || 
                daysCb.getSelectionModel().getSelectedItem() == null ||
                startTimeCb.getSelectionModel().getSelectedItem() == null ||
                endTimeCb.getSelectionModel().getSelectedItem() == null)
                dialog.getDialogPane().lookupButton(done).setDisable(true);
            else
                dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        descriptionTf.setOnKeyReleased(e -> {
            if (prefixTf.getText().equals("") || numberTf.getText().equals("") ||
                descriptionTf.getText().equals("") || locationTf.getText().equals("") || 
                daysCb.getSelectionModel().getSelectedItem() == null ||
                startTimeCb.getSelectionModel().getSelectedItem() == null ||
                endTimeCb.getSelectionModel().getSelectedItem() == null)
                dialog.getDialogPane().lookupButton(done).setDisable(true);
            else
                dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        locationTf.setOnKeyReleased(e -> {
            if (prefixTf.getText().equals("") || numberTf.getText().equals("") ||
                descriptionTf.getText().equals("") || locationTf.getText().equals("") || 
                daysCb.getSelectionModel().getSelectedItem() == null ||
                startTimeCb.getSelectionModel().getSelectedItem() == null ||
                endTimeCb.getSelectionModel().getSelectedItem() == null)
                dialog.getDialogPane().lookupButton(done).setDisable(true);
            else
                dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        daysCb.setOnAction(e -> {
            if (prefixTf.getText().equals("") || numberTf.getText().equals("") ||
                descriptionTf.getText().equals("") || locationTf.getText().equals("") || 
                daysCb.getSelectionModel().getSelectedItem() == null ||
                startTimeCb.getSelectionModel().getSelectedItem() == null ||
                endTimeCb.getSelectionModel().getSelectedItem() == null)
                dialog.getDialogPane().lookupButton(done).setDisable(true);
            else
                dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        startTimeCb.setOnAction(e -> {
            if (prefixTf.getText().equals("") || numberTf.getText().equals("") ||
                descriptionTf.getText().equals("") || locationTf.getText().equals("") || 
                daysCb.getSelectionModel().getSelectedItem() == null ||
                startTimeCb.getSelectionModel().getSelectedItem() == null ||
                endTimeCb.getSelectionModel().getSelectedItem() == null)
                dialog.getDialogPane().lookupButton(done).setDisable(true);
            else
                dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        endTimeCb.setOnAction(e -> {
            if (prefixTf.getText().equals("") || numberTf.getText().equals("") ||
                descriptionTf.getText().equals("") || locationTf.getText().equals("") || 
                daysCb.getSelectionModel().getSelectedItem() == null ||
                startTimeCb.getSelectionModel().getSelectedItem() == null ||
                endTimeCb.getSelectionModel().getSelectedItem() == null)
                dialog.getDialogPane().lookupButton(done).setDisable(true);
            else
                dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        dialog.setResultConverter(button -> {
            if (button == done) {
                return new Course(prefixTf.getText(), numberTf.getText(),
                        descriptionTf.getText(), locationTf.getText(),
                        daysCb.getSelectionModel().getSelectedItem().toString(),
                        startTimeCb.getSelectionModel().getSelectedItem().toString(),
                        endTimeCb.getSelectionModel().getSelectedItem().toString());
            }
            return null;
        });
        
        Optional<Course> result = dialog.showAndWait();
        if (result.isPresent()) {
            Main_FX.person.getCourses().add(result.get());
            int course = Main_FX.person.getCourses().size() - 1;
            if (schedule.isThereCourseTimeConflict(course)) {
               Main_FX.person.getCourses().remove(course);
               schedule.timeConflictAlert(0);
               success = false;
            }
        } else
            success = false;
    }
    
    public void deleteCourse() {        
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Delete course");
        dialog.setHeaderText("Choose a course to delete");
        dialog.getDialogPane().getStylesheets().add(getClass().getResource(Main_FX.theme).toExternalForm());
        
        GridPane grid = new GridPane();
        
        ComboBox comboBox = new ComboBox();
        ObservableList<String> comboBoxList = FXCollections.observableArrayList();
        setCoursesList();
        coursesList.stream().forEach((course) -> {
            comboBoxList.add(course.getText());
        });
        comboBox.setItems(comboBoxList);
        
        Label coursesLb = new Label("Courses: ");
        
        grid.add(coursesLb, 0, 0);
        grid.add(comboBox, 0, 1);
        
        ButtonType done = new ButtonType("Done", ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(done, cancel);
        dialog.getDialogPane().lookupButton(done).setDisable(true);
        
        comboBox.setOnAction(e -> {
            dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        dialog.setResultConverter(button -> {
            if (button == done) {
                return comboBox.getSelectionModel().getSelectedIndex();
            }
            return null;
        });
        
        Optional result = dialog.showAndWait();
        if (result.isPresent()) {
            int course = comboBox.getSelectionModel().getSelectedIndex();
            int courseID = courses.get(course).getId();
            sql = "DELETE FROM course WHERE id = ?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, courseID);
                ps.executeUpdate();
            } catch (SQLException e) {
                Fn.showError(e);
            }
            courses.remove(course);
        }
    }
    
    public void modifyCourse() {
        Dialog dialog = new Dialog();
        dialog.setTitle("Modify a Course");
        dialog.setHeaderText("Select which course to modify.");
        dialog.getDialogPane().getStylesheets().add(getClass().getResource(Main_FX.theme).toExternalForm());
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        Label coursesLb = new Label("Courses:");
        
        ComboBox coursesCb = new ComboBox();
        setCoursesList();
        ObservableList<String> comboBoxList = FXCollections.observableArrayList();
        for (Label course : coursesList)
            comboBoxList.add(course.getText());
        coursesCb.setItems(comboBoxList);
        
        Button ok = new Button("OK");
        
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
        
        ObservableList<String> daysList = FXCollections.observableArrayList();
        daysList.addAll("M", "T", "W", "TH", "F", "MW", "TR");
        
        daysCb.setItems(daysList);
        
        ObservableList<String> times = FXCollections.observableArrayList();
        times.addAll(Arrays.asList(schedule.getTimeIntervals()));
        
        startTimeCb.setItems(times);
        endTimeCb.setItems(times);
        
        prefixTf.setDisable(true);
        numberTf.setDisable(true);
        descriptionTf.setDisable(true);
        locationTf.setDisable(true);
        daysCb.setDisable(true);
        startTimeCb.setDisable(true);
        endTimeCb.setDisable(true);
        
        grid.add(coursesLb, 0, 0);
        grid.add(coursesCb, 0, 1);
        grid.add(ok, 1, 1);
        grid.add(prefixLb, 0, 2);
        grid.add(prefixTf, 1, 2);
        grid.add(numberLb, 0, 3);
        grid.add(numberTf, 1, 3);
        grid.add(descriptionLb, 0, 4);
        grid.add(descriptionTf, 1, 4);
        grid.add(locationLb, 0, 5);
        grid.add(locationTf, 1, 5);
        grid.add(daysLb, 0, 6);
        grid.add(daysCb, 1, 6);
        grid.add(startTimeLb, 0, 7);
        grid.add(startTimeCb, 1, 7);
        grid.add(endTimeLb, 0, 8);
        grid.add(endTimeCb, 1, 8);
        
        ButtonType done = new ButtonType("Done", ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(done, cancel);
        dialog.getDialogPane().lookupButton(done).setDisable(true);
        
        
        ok.setOnAction(e -> {
            prefixTf.setDisable(false);
            numberTf.setDisable(false);
            descriptionTf.setDisable(false);
            locationTf.setDisable(false);
            daysCb.setDisable(false);
            startTimeCb.setDisable(false);
            endTimeCb.setDisable(false);
            dialog.getDialogPane().lookupButton(done).setDisable(false);
            
            int index = coursesCb.getSelectionModel().getSelectedIndex();
            prefixTf.setText(courses.get(index).getPrefix());
            numberTf.setText(courses.get(index).getNumber());
            descriptionTf.setText(courses.get(index).getDescription());
            locationTf.setText(courses.get(index).getLocation());
            daysCb.setValue(courses.get(index).getDays());
            startTimeCb.setValue(courses.get(index).getStartTime());
            endTimeCb.setValue(courses.get(index).getEndTime());
        });
        
        coursesCb.setOnAction(e -> {
            prefixTf.setDisable(true);
            numberTf.setDisable(true);
            descriptionTf.setDisable(true);
            locationTf.setDisable(true);
            daysCb.setDisable(true);
            startTimeCb.setDisable(true);
            endTimeCb.setDisable(true);
            dialog.getDialogPane().lookupButton(done).setDisable(true);
            
            prefixTf.setText("");
            numberTf.setText("");
            descriptionTf.setText("");
            locationTf.setText("");
            daysCb.setValue("");
            startTimeCb.setValue("");
            endTimeCb.setValue("");
        });
        
        prefixTf.setOnKeyReleased(e -> {
            if (prefixTf.getText().equals("") || numberTf.getText().equals("")
                    || descriptionTf.getText().equals("")
                    || locationTf.getText().equals(""))
                dialog.getDialogPane().lookupButton(done).setDisable(true);
            else
                dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        numberTf.setOnKeyReleased(e -> {
            if (prefixTf.getText().equals("") || numberTf.getText().equals("")
                    || descriptionTf.getText().equals("")
                    || locationTf.getText().equals(""))
                dialog.getDialogPane().lookupButton(done).setDisable(true);
            else
                dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        descriptionTf.setOnKeyReleased(e -> {
            if (prefixTf.getText().equals("") || numberTf.getText().equals("")
                    || descriptionTf.getText().equals("")
                    || locationTf.getText().equals(""))
                dialog.getDialogPane().lookupButton(done).setDisable(true);
            else
                dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        locationTf.setOnKeyReleased(e -> {
            if (prefixTf.getText().equals("") || numberTf.getText().equals("")
                    || descriptionTf.getText().equals("")
                    || locationTf.getText().equals(""))
                dialog.getDialogPane().lookupButton(done).setDisable(true);
            else
                dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        dialog.setResultConverter(button -> {
            if (button == done) {
                int index = coursesCb.getSelectionModel().getSelectedIndex();
                Course originalCourse = courses.get(index);
                Course modifiedCourse = new Course(originalCourse.getId(),
                        prefixTf.getText(), numberTf.getText(), descriptionTf.getText(),
                        locationTf.getText(), daysCb.getSelectionModel().getSelectedItem().toString(),
                        startTimeCb.getSelectionModel().getSelectedItem().toString(),
                        endTimeCb.getSelectionModel().getSelectedItem().toString());
                
                if (originalCourse.isEqualTo(modifiedCourse)) {
                    success = false;
                    return null;
                }
                
                Main_FX.person.getCourses().remove(index);
                Main_FX.person.getCourses().add(index, modifiedCourse);
                if (schedule.isThereCourseTimeConflict(index)) {
                    schedule.timeConflictAlert(1);
                    Main_FX.person.getCourses().remove(index);
                    Main_FX.person.getCourses().add(index, originalCourse);
                    success = false;
                }
                return index;
            }
            return null;
        });
        
        int resultIndex;
        Optional<Integer> result = dialog.showAndWait();
        if (result.isPresent()) {
            resultIndex = result.get();
            updateCourseInDatabase(resultIndex);
        }
    }
    
    public void changeInterval() {
        Dialog dialog = new Dialog();
        dialog.setTitle("Change Time Interval");
        dialog.setHeaderText("Choose a time interval.");
        dialog.getDialogPane().getStylesheets().add(getClass().getResource(Main_FX.theme).toExternalForm());
        
        ButtonType bt1 = new ButtonType("0");
        ButtonType bt2 = new ButtonType("15");
        ButtonType bt3 = new ButtonType("30");
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        dialog.getDialogPane().getButtonTypes().addAll(bt1, bt2, bt3, cancel);
        
        dialog.setResultConverter(button -> {
            if (button == bt1)
                return 0;
            else if (button == bt2)
                return 15;
            else if (button == bt3)
                return 30;
            return null;
        });
        
        int timeIncrement;
        int uesr_id;
        Optional<Integer> result = dialog.showAndWait();
        if (result.isPresent()) {
            timeIncrement = result.get();
            uesr_id = Main_FX.person.getUserId();
            Database.modifyInt(uesr_id, "user", "time_increment", timeIncrement);
        }
    }
    
    public void changeTheme() {
        Dialog dialog = new Dialog();
        dialog.setTitle("Themes");
        dialog.setHeaderText("Choose a theme.");
        dialog.getDialogPane().getStylesheets().add(getClass().getResource(Main_FX.theme).toExternalForm());
        
        GridPane grid = new GridPane();

        ComboBox themeCb = new ComboBox();
        ObservableList<String> themes = FXCollections.observableArrayList();
        Database.getItems(themes,"theme","name");
        themeCb.setItems(themes);
        
        grid.add(themeCb, 0, 0);
        
        ButtonType done = new ButtonType("Done", ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(done, cancel);
        dialog.getDialogPane().lookupButton(done).setDisable(true);
        
        themeCb.setOnAction(e -> {
            dialog.getDialogPane().lookupButton(done).setDisable(false);
        });
        
        dialog.setResultConverter(button -> {
            if (button == done) {
                int theme_id = Database.getID("theme", "name", themeCb.getSelectionModel().getSelectedItem().toString());
                Database.modifyInt(id, "user", "theme_id", theme_id);
                Main_FX.theme = Database.getString("theme",theme_id,"name");
                Main_FX.scene.getStylesheets().remove(0);
                Main_FX.scene.getStylesheets().add(getClass().getResource(Main_FX.theme).toExternalForm());
            }
            return null;
        });
        dialog.showAndWait();
    }
    
    public void addCourseToDatabase() {
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
    
    public void updateCourseInDatabase(int course) {
        courses = Main_FX.person.getCourses();
        Course modifiedCourse = courses.get(course);
        
        int id = modifiedCourse.getId();
        String prefix = modifiedCourse.getPrefix();
        String number = modifiedCourse.getNumber();
        String description = modifiedCourse.getDescription();
        String location = modifiedCourse.getLocation();
        String days = modifiedCourse.getDays();
        String startTime = modifiedCourse.getStartTime();
        String endTime = modifiedCourse.getEndTime();
        
        sql = "UPDATE course SET prefix='" + prefix + "', number='" + number +
                "', description='" + description + "', location='" + location +
                "', days='" + days + "', startTime='" + startTime + "', endTime='" + 
                endTime + "' WHERE id='" + id + "'";
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            Fn.showError(e);
        }
    }
    
    public ObservableList<PieChart.Data> setData() {
        schedule.generateCourseSchedule();
        schedule.generateEventSchedule();
        int[][] coursesPlace = schedule.getCoursesPlace();
        int[][] eventsPlace = schedule.getEventsPlace();
        
        System.out.println(coursesPlace.length);
        System.out.println(eventsPlace.length);
        
        int num = 0;
        int freeTime = 57 * 5;
        for (int i = 0; i < coursesPlace.length; i++) {
            for (int j = 0; j < coursesPlace[i].length; j++) {
                if (coursesPlace[i][j] != -1) {
                    num++;
                    freeTime--;
                }
            }
        }
        
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("Course Time", num * 15));
        data.add(new PieChart.Data("Commitments Time", 0));
        data.add(new PieChart.Data("Free Time", freeTime * 15));
        
        return data;
    }
    
    public void updatePieChart() {
        int[][] coursesPlace = schedule.getCoursesPlace();
        
        int num = 0;
        int freeTime = 57 * 5;
        for (int i = 0; i < coursesPlace.length; i++) {
            for (int j = 0; j < coursesPlace[i].length; j++) {
                if (coursesPlace[i][j] != -1) {
                    num++;
                    freeTime--;
                }
            }
        }
        
        for (PieChart.Data data : pieChart.getData()) {
            if (data.getName().equals("Course Time"))
                data.setPieValue(num * 15);
            if (data.getName().equals("Free Time"))
                data.setPieValue(freeTime * 15);
        }
    }
}
