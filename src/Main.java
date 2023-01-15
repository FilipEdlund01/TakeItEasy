import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;
import java.lang.Math;


public class Main extends PApplet{

    BoardHexan hexan1 =new BoardHexan(500,500,1);
   public int x=0;
    public int y=65;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private boolean clicked = false;
    private Hexan onHex1 = null;
    private boolean b = false;
    private Hexan mouseOverHexan = null;
    PImage img;// obrazek jsem ukladal v editoru velikost widht: 400,  https://www.photopea.com/


    public static void main(String[] args) {


        HexanBuilder.getInstance().buildHexans();

        PApplet.main("Main");
    }

    public void showAllHexans(){
        for (Hexan hexan : Hexan.all_hexans) {

            hexagon(hexan.getX(), hexan.getY(), Hexan.HEXAN_SIDE_SIZE);  // Hexagon
        }
       /* for (BoardHexan boardHexan : BoardHexan.all_BoardHexans){
            hexagon(boardHexan.getX(),boardHexan.getY(),80);
        }*/


    }




    /*public void drawEmptyBoard(){
        for (BoardHexan boardHexan : BoardHexan.all_BoardHexans){
            BoardHexagon(boardHexan.getX(),boardHexan.getY(),80);
        }
    }*/

    @Override
    public void windowResizable(boolean resizable) {
        super.windowResizable(true);
    }

    @Override
    public void settings() {

        size(1600, 800, "processing.awt.PGraphicsJava2D");


    }

    @Override
    public void setup() {
        img = loadImage("hex586.png");

    }

     @Override
    public void draw() {





         background(255,255,255);


         //  hexagon(500,600,Hexan.HEXAN_SIDE_SIZE);
         stroke(0);          // Setting the outline (stroke) to black
         fill(150);
         BoardHexagon(hexan1.getX(),hexan1.getY(),80);
         fill(255,255,255);
         showAllHexans();
         showImage();



        point(200, 200);

    }
    public void showImage(){
        image(img, x, y);
    }



    void hexagon(float x, float y, float radius) {
        float angle = TWO_PI / 6;
      //  background(0);

        beginShape();
        for (float a = 0; a < TWO_PI; a += angle) {
            float sx = x + cos(a) * radius;
            float sy = y + sin(a) * radius;
            vertex(sx, sy);
        }
        endShape(CLOSE);
    }
    void BoardHexagon(float x, float y, float radius) {
        float angle = TWO_PI / 6;
        beginShape();
        for (float a = 0; a < TWO_PI; a += angle) {
            float sx = x + cos(a) * radius;
            float sy = y + sin(a) * radius;
            vertex(sx, sy);
        }
        endShape(CLOSE);
    }



    @Override
    public void mouseMoved(MouseEvent event) {
        checkColisoin();
        // cos p = b/c
        // a = cos b * c


       // float lengthY = cos(PI/6) * Hexan.HEXAN_SIDE_SIZE;

        double lengthY = Math.sqrt((Hexan.HEXAN_SIDE_SIZE*Hexan.HEXAN_SIDE_SIZE)+(Hexan.HEXAN_SIDE_SIZE/2*Hexan.HEXAN_SIDE_SIZE/2)); // pocitani vzdalenosti od stredu k hrane pomoci pythagora
        for (Hexan hexan: Hexan.all_hexans) {
            if(hexan.getX() + Hexan.HEXAN_SIDE_SIZE > mouseX && hexan.getY() +  lengthY  > mouseY &&
                    hexan.getX() - Hexan.HEXAN_SIDE_SIZE < mouseX && hexan.getY() - lengthY < mouseY){
                mouseOverHexan = hexan;
            }else {
                mouseOverHexan = null;
            }
        }

        if(clicked ==true && mouseOverHexan != null){
            mouseOverHexan.setX(mouseX);
            mouseOverHexan.setY(mouseY);
            x = mouseX-200;
            y = mouseY-130;

        }
    }

//    @Override
//    public void mouseReleased() {
//        clicked = false;
//    }
//
//    @Override
//    public void mouseDragged() {
//        System.out.println("dragged");
//
//        clicked = true;
//    }

    @Override
    public void mousePressed() {

        System.out.println("cliked");
        System.out.println("kokot");
        clicked = !clicked;
    }

    //Hexan hexan = Hexan.all_hexans[2];


    public void checkColisoin(){
        double lengthY = Math.sqrt((Hexan.HEXAN_SIDE_SIZE*Hexan.HEXAN_SIDE_SIZE)+(Hexan.HEXAN_SIDE_SIZE/2*Hexan.HEXAN_SIDE_SIZE/2)); // pocitani vzdalenosti od stredu k hrane pomoci pythagora
        for (Hexan hexan: Hexan.all_hexans) {
          if(hexan.getX()>hexan1.getX()-Hexan.HEXAN_SIDE_SIZE && hexan.getX()< hexan1.getX()+Hexan.HEXAN_SIDE_SIZE && hexan.getY()<hexan1.getY()+lengthY && hexan.getY()> hexan1.getY()-lengthY){
              //System.out.println("Touching hexagon1");
              b = true;

          }else{

              b = false;

          }

          if(clicked == false && b == true && mouseOverHexan !=null){
              hexan.setX((int) hexan1.getX());
              hexan.setY((int) hexan1.getY());
              x = (int)hexan1.getX()-200;
              y = (int)hexan1.getY()-130;
              clicked=false;
              mouseX = 100;
              mouseY = 100;
          }


        }

    }
}


