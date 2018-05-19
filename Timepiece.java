package application;
/*
 * EXTRA CREDIT ASSIGNMENT 
 * 
 */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Timepiece extends Application {
	private Calendar cal;
	
	protected String thisMonth;
	private String thisDay;//USE FOR CURRENT DATE
	private int year;
	private int date; //of the month
	private int thisHour;
	private int thisMinute;
	private int thisSecond;
	private String currentTime;//USE FOR CURRENT TIME
	private String amPm;
	protected NumberFormat fix = new DecimalFormat("00");
	protected Timeline oncePerSecond;
	ClockPane clock;
	
	public void start(Stage primaryStage) {
		BorderPane environment = new BorderPane();
		setupCalendar();
		clock = new ClockPane(cal);
		
		
		//-------------------------------
		//SET UP WINDOW
		//-------------------------------
		Text showDate = new Text(thisDay);
		showDate.setStyle("-fx-font-size: 30");
		showDate.setFill(Color.DARKORANGE);
		Text showCurrentTime = new Text(currentTime);
		showCurrentTime.setStyle("-fx-font-size: 25");
		showCurrentTime.setFill(Color.MEDIUMPURPLE);
		VBox dateTime = new VBox(showDate, showCurrentTime);
		
		oncePerSecond = new Timeline(
				new KeyFrame(Duration.seconds(1), e -> {
					Calendar newNow = whenIsIt();
					clock.showTimePassage(newNow);
					update(newNow); //that updates the variables so Strings will work
					showCurrentTime.setText(currentTime);
				}));
		oncePerSecond.setCycleCount(Timeline.INDEFINITE);
		
		environment.setCenter(clock);
		environment.setBottom(dateTime);
		//environment.setBottom(/*put the time and date w/ animations here*/);
		
		Scene scene = new Scene(environment);
		primaryStage.setScene(scene);
		primaryStage.show();
		oncePerSecond.play();
	}
	//SETS UP ALL OF THE CALENDAR PROPERLY it's a long one, just wanted it all in one place
	public void setupCalendar() {  
		cal = whenIsIt();
		Locale here = Locale.US;
		thisMonth = cal.getDisplayName(2, Calendar.LONG_STANDALONE, here);
		date = cal.get(5); //lesson learned: if it's a number do this
		//ADD CODE FOR ORDINALS HERE
		year = cal.get(1);
		thisHour = cal.get(10);
		thisMinute = cal.get(12);
		thisSecond = cal.get(13);
		amPm = cal.getDisplayName(9, Calendar.SHORT, here);
		thisDay = thisMonth +" "+ addOrdinal(date) + ", " + year;
		currentTime = fix.format(thisHour) + ":" + fix.format(thisMinute) + ":" + fix.format(thisSecond) + " " + amPm;
	}
	
	public void update(Calendar c) {  
		Locale here = Locale.US;
		thisMonth = c.getDisplayName(2, Calendar.LONG_STANDALONE, here);
		date = c.get(5); //lesson learned: if it's a number do this
		//ADD CODE FOR ORDINALS HERE
		year = c.get(1);
		thisHour = c.get(10);
		thisMinute = c.get(12);
		thisSecond = c.get(13);
		amPm = c.getDisplayName(9, Calendar.SHORT, here);
		thisDay = thisMonth +" "+ addOrdinal(date) + ", " + year;
		currentTime = fix.format(thisHour) + ":" + fix.format(thisMinute) + ":" + fix.format(thisSecond) + " " + amPm;
	}
	
	
	public Calendar whenIsIt() {
		return Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
	}
	
	public String addOrdinal(int date) { // in order: st, nd, rd, th
		int i = date%10;
		String suffix = "";
		switch(i) {
		case 1: 
			suffix = "st";
			break;
		case 2:
			suffix = "nd";
			break;
		case 3: 
			suffix = "rd";
			break;
		default: 
			suffix = "th";
		}
		return date + suffix;
	}
	

	public static void main(String[] args) {
		launch(args);
		
	}
}
