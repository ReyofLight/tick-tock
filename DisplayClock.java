package application;
/*
 * Note to Self: perhaps there's a better way? 
 * FIX NUMBER FORMAT SO EACH IS 2 DIGITS ALWAYS
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;


public class DisplayClock extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a pane to hold the clock components
    Pane pane = new Pane();
    
  //--------------------------
 //CIRCLE
  //--------------------------
    Circle circle = new Circle(100, 100, 75);
    Circle inner = new Circle(100, 100, 60);
    Circle center = new Circle(100, 100, 5);
    center.setFill(Color.SPRINGGREEN);
    inner.setFill(null);
    inner.setStroke(Color.PALEVIOLETRED);
    pane.getChildren().addAll(circle, inner, center);
    
//    Line guide = new Line(25, 100, 175, 100);
//    guide.setStroke(Color.ALICEBLUE);
//    Line guide2 = new Line(100, 25, 100, 175);
//    guide2.setStroke(Color.ALICEBLUE);
//    pane.getChildren().addAll(guide, guide2);

      
  //--------------------------
  //NUMBERS
  //--------------------------
    Text twelve = new Text(93, 36 , "12");
    twelve.setFill(Color.SPRINGGREEN);
    Text three = new Text(165, 105,"3");
    three.setFill(Color.SPRINGGREEN);
    Text six = new Text(97, 173,"6");
    six.setFill(Color.SPRINGGREEN);
    Text nine = new Text(29, 105,"9");
    nine.setFill(Color.SPRINGGREEN);
    pane.getChildren().addAll(twelve, three, six, nine);

    
  //--------------------------
 //LINES
 //--------------------------
    Line hour = new Line(100, 100, 100, 60);
    hour.setStyle("-fx-stroke: springgreen; -fx-stroke-width: 3");
    Line minute = new Line(100, 100, 100, 43);
    minute.setStyle("-fx-stroke: palevioletred; -fx-stroke-width: 1");
   
   
    
    //------------------------------
    //CALENDAR SHENANIGANS 
    //------------------------------
    
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
    Date now = cal.getTime();
   String amPM;
   int hours = cal.get(10);
   int minutes = cal.get(12);
   int seconds = cal.get(13);
    if (cal.get(9) == 0) {
    	amPM = "AM";
    }
    else
    	amPM = "PM";
    Text time = new Text(100, 190, hours + ":" + minutes + ":" + seconds + " " + amPM);
    time.setStyle("-fx-font-family: fantasy; -fx-font-size: 14 ");
    pane.getChildren().add(time);
    //----------------hand angle shenanigans--------------------
    double hourHandAngle = (hours*30);
    hour.getTransforms().add(new Rotate(hourHandAngle, 100, 100));
    double minHandAngle = (minutes*6);
    minute.getTransforms().add(new Rotate(minHandAngle, 100, 100));
    pane.getChildren().addAll(hour, minute);
    //---------------------END SHENANIGANS --------------------------------------
    
    // Create a scene and place it in the stage, modify and set the size of the initial scene to 200 by 200
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("DisplayClock"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  

  public static void main(String[] args) {
    launch(args);
  }
}