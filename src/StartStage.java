import processing.core.PFont;

import static java.lang.Math.sqrt;
import static processing.core.PApplet.sq;


public class StartStage {
    private final static StartStage startStageInstance = new StartStage();
    private int x= Main.WIDTH/2;

    private int y= Main.HEIGHT/2;
    private  int diameter =200;

    public PFont f;

    public void setF(PFont f) {
        this.f = f;
    }

    public PFont getF() {
        return f;
    }

    public int getCircleX() {
        return x;
    }

    public int getCircleY() {
        return y;
    }

    public int getCircleDiameter() {
        return diameter;
    }

    public void setCircleDiameter(int diameter) {
        this.diameter = diameter;
    }

    private StartStage() {


    }

    public static StartStage getInstance() {
        return startStageInstance;
    }

    public static void runCircleAnimation(){
        for(int i =1000; i>200; i--){

        }
    }

    public boolean overCircle( int mouseX, int mouseY) {
        float disX = x - mouseX;
        float disY = y - mouseY;
        if (sqrt(sq(disX) + sq(disY)) < diameter / 2) {
            return true;
        } else {
            return false;
        }
    }
    public String printWelcomeText(){
        return "Click on the button to start!";
    }






}
