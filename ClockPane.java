package application;

import java.util.Calendar;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

/*
 * THIS IS THE 3RD VERSION OF THIS PROJECT 
 * 1.0 only displayed the time it was when it ran
 * 1.5 was animated but inaccurate because it was measuring time based 
 * off of the original angle of the second hand, meaning that the clock would be
 * on average one minute behind at any given time, and the time underneath did not change
 * this is 2.0
 * 
 * GOALS: 
 * fulfill the project guidelines: Clock must reflect correct time, 
 * digital clock (label) beneath should have correct format (hh:mm:ss) 
 * must display date with format May 1st, 2018
 * I would also like to increase the size of the clock with the window
 * and change the colorscheme but that might not get done
 * I will be using code from past versions
 * 
 * 
 */
public class ClockPane extends Pane {
	private Pane face = new Pane();
	private Circle circle = new Circle(100, 100, 75);
    private Circle inner = new Circle(100, 100, 60);
    private Circle center = new Circle(100, 100, 5);
    
    private Text twelve = new Text(93, 36 , "12");
    private Text three = new Text(165, 105,"3");
    private Text six = new Text(97, 173,"6");
    private Text nine = new Text(29, 105,"9");
    
    protected Line hour = new Line(100, 100, 100, 60);
    protected Line minute = new Line(100, 100, 100, 43);
    protected Line second = new Line(100, 100, 100, 40);
    Rotate sixDegrees = new Rotate(6, 100, 100);
    private double hourAngle;
    private double minuteAngle;
    private double secondAngle;
    private int knownMin;
    
    public ClockPane(Calendar c) {
    	setup();
    	setTime(c.get(10), c.get(12), c.get(13));
    	knownMin = c.get(12);
    }
    
    public void setTime(int hours, int minutes, int seconds) { 
		hourAngle = hours*30;
		hour.getTransforms().add(new Rotate(hourAngle, 100, 100));
		minuteAngle = minutes*6;
		minute.getTransforms().add(new Rotate(minuteAngle, 100, 100));
		secondAngle = seconds*6;
		second.getTransforms().add(new Rotate(secondAngle, 100, 100));
	}
    public void moveHands(boolean m, boolean s) {
    	second.getTransforms().add(sixDegrees);
    	if(m == true) {
    		minute.getTransforms().add(sixDegrees);
    		//System.out.println("MINUTE HAND");
    		hour.getTransforms().add(new Rotate(0.5, 100, 100));
    	}
    	    	
    }
    public void showTimePassage(Calendar c) {
    	int min = c.get(12);
    	boolean moveM = false;
    	boolean moveS = true;
    	if(min != knownMin) {
    		moveM = true;
    		knownMin = min;
    	}
    	moveHands(moveM, moveS);
    }
    
    public void setup() {
		center.setFill(Color.SPRINGGREEN);
	    inner.setFill(null);
	    inner.setStroke(Color.PALEVIOLETRED);
	    face.getChildren().addAll(circle, center);
	    twelve.setFill(Color.SPRINGGREEN);
	    three.setFill(Color.SPRINGGREEN);
	    six.setFill(Color.SPRINGGREEN);
	    nine.setFill(Color.SPRINGGREEN);
	    face.getChildren().addAll(twelve, three, six, nine);
	    hour.setStyle("-fx-stroke: springgreen; -fx-stroke-width: 3");
	    minute.setStyle("-fx-stroke: palevioletred; -fx-stroke-width: 1");
	    second.setStyle("-fx-stroke: white; -fx-stroke-width: .5");
	    getChildren().addAll(face, inner, hour, minute, second);
	}

}
