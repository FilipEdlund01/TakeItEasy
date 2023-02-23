import processing.core.PFont;

import static java.lang.Math.sqrt;
import static processing.core.PApplet.sq;


public class StartStage {
    private final static StartStage startStageInstance = new StartStage();
    private int x= Constants.WIDTH.getValue()/2;

    private int y= Constants.HEIGHT.getValue()/2;
    private  int diameter =200;

    public PFont f;

    public void setF(PFont f) {
        this.f = f;
    }

    public PFont getF() {
        return f;
    }

   /* public int getCircleX() {
        return x;
    }

    public int getCircleY() {
        return y;
    }

    public int getCircleDiameter() {
        return diameter;
    }
*/
    public void setCircleDiameter(int diameter) {
        this.diameter = diameter;
    }

    private StartStage() {


    }

    public void buildCircles(){
      /*  for (int i=0; i < Circle.allCircels.length; i++){
            Circle.allCircels[i] = new Circle()
        }*/

        Circle.allCircels[0] = new Circle(x-Constants.WIDTH.getValue()/3,y,diameter);
        Circle.allCircels[1] = new Circle(x+Constants.WIDTH.getValue()/3,y,diameter);
       // Circle.allCircels[2] = new Circle(-1,-1,-1);//avoid out of bounds exception



    }
    public void buildRects(){
        Rectangle.allRectangels[0]=new Rectangle(Constants.rectx0.getValue(), Constants.recty0.getValue(), Constants.rectXdistance.getValue(),Constants.rectYdistance.getValue());

    }
    public String prinsStringForUser(int n){
        String s = null;
        if(n==0){
            s="Please type your username";
        }
        return s;
    }

    public static StartStage getInstance() {
        return startStageInstance;
    }



    public boolean overCircle( int mouseX, int mouseY,int circleX,int circleY,int diameter) {
      /*  for (int i=0; i < Circle.allCircels.length; i++){
            float disX = Circle.allCircels[i].getCordX() - mouseX;
            float disY = Circle.allCircels[i].getCordY() - mouseY;
            if (sqrt(sq(disX) + sq(disY)) < Circle.allCircels[i].getDiameter() / 2) {
                return true;
            } else {
                return false;
            }

        }*/

        float disX = circleX - mouseX;
        float disY = circleY - mouseY;
        if (sqrt(sq(disX) + sq(disY)) < diameter / 2) {
            return true;
        } else {
            return false;
        }



    }


    public String printWelcomeText(){
        return "Click on the button to start!";
    }

    public String logIn(){
        return "LOG IN";
    }
    public String register(){
        return "REGISTER";
    }






}
