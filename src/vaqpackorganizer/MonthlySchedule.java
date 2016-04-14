package vaqpackorganizer;


import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Calendar;
import javafx.scene.shape.Rectangle;

public class MonthlySchedule extends Application{
    //get calendar instance
        private Calendar cal = Calendar.getInstance();
        
        int WeekDay = cal.get(Calendar.DAY_OF_WEEK);
        int dayMonth = cal.get(Calendar.DAY_OF_MONTH);
        int Month   = cal.get(Calendar.MONTH);
        int Year    = cal.get(Calendar.YEAR);
       
        String[] sDay = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        
        String[] sMonth = {"January", "February", "March", "April", "May", 
            "June", "July", "August", "September", 
            "October", "November", "December"};

        
        //declaring variables for day rectangles
        private int rWidth = 140;
        private int rHeigth = 30;

        private Stage stage;
        
        
   /* public StackPane[] doCalendar(int WeekDay, int dayMonth, int Month, int Year, String[] sDay, String[] sMonth) {
        
      
        Rectangle MonthRec = new Rectangle();
        StackPane monthPane = new StackPane();  
        Year = 10;
    }
    */
    @Override
    public void start (Stage stage) {
        this.stage = stage;
        stage.setTitle("Calendar");
        
        stage.show();
    }
}