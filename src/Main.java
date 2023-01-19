import processing.core.PApplet;
import processing.event.MouseEvent;
import java.lang.Math;


public class Main extends PApplet{

    public static int WIDTH = 1600;
    public static int HEIGHT = 800;
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

    private HexanCard mouseOverHexan = null;


    public static void main(String[] args) {
        HexanBuilder.getInstance().buildHexans();
        HexanBuilder.getInstance().buildHexanBoard();

        PApplet.main("Main");
    }


    @Override
    public void windowResizable(boolean resizable) {
        super.windowResizable(true);
    }

    @Override
    public void settings() {
        size(Main.WIDTH, Main.HEIGHT, "processing.awt.PGraphicsJava2D");
    }

    @Override
    public void setup() {
        // load all images
        for (HexanCard hexan: HexanCard.allHexans) {
            hexan.setImage(loadImage(hexan.getImageString()));
        }
    }

     @Override
    public void draw() {

       //  background(255,199,140);
         background(255,255,255);

        // draw hex cards
         for (HexanCard hexan: HexanCard.allHexans) {
             showImage(hexan);
         }

         // draw board
         for (HexanBoard[] hexanBoardColumn: HexanBoard.hexanBoard) {
             for (HexanBoard hexanBoard: hexanBoardColumn) {
                 hexagon(hexanBoard.getX(), hexanBoard.getY(), HexanCard.HEXAN_SIDE_SIZE);
             }
         }
    }
    public void showImage(HexanCard hexan){
        image(hexan.getImage(), hexan.getX(), hexan.getY());
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

    public void showNextHex(){

    }



    @Override
    public void mouseMoved(MouseEvent event) {
        //checkCollision();
        // cos p = b/c
        // a = cos b * c


       // float lengthY = cos(PI/6) * Hexan.HEXAN_SIDE_SIZE;

        double lengthY = Math.sqrt((HexanCard.HEXAN_SIDE_SIZE* HexanCard.HEXAN_SIDE_SIZE)+(HexanCard.HEXAN_SIDE_SIZE/2* HexanCard.HEXAN_SIDE_SIZE/2)); // pocitani vzdalenosti od stredu k hrane pomoci pythagora
        for (HexanCard hexan: HexanCard.allHexans) {
            if(hexan.getX() + HexanCard.HEXAN_SIDE_SIZE > mouseX && hexan.getY() +  lengthY  > mouseY &&
                    hexan.getX() - HexanCard.HEXAN_SIDE_SIZE < mouseX && hexan.getY() - lengthY < mouseY){
                mouseOverHexan = hexan;
            }else {
                mouseOverHexan = null;
            }
        }

        if(clicked && mouseOverHexan != null){
            mouseOverHexan.setX(mouseX);
            mouseOverHexan.setY(mouseY);
            x = mouseX-200;
            y = mouseY-135;

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


//    public void checkCollision(){
//        double lengthY = Math.sqrt((Hexan.HEXAN_SIDE_SIZE*Hexan.HEXAN_SIDE_SIZE)+(Hexan.HEXAN_SIDE_SIZE/2*Hexan.HEXAN_SIDE_SIZE/2)); // pocitani vzdalenosti od stredu k hrane pomoci pythagora
//        for (Hexan hexan: Hexan.allHexans) {
//
//          if(!clicked &&  hexan.getX()>hexan1.getX()-Hexan.HEXAN_SIDE_SIZE && hexan.getX()< hexan1.getX()+Hexan.HEXAN_SIDE_SIZE && hexan.getY()<hexan1.getY()+lengthY && hexan.getY()> hexan1.getY()-lengthY && mouseOverHexan !=null){
//              hexan.setX((int) hexan1.getX());
//              hexan.setY((int) hexan1.getY());
//              x = (int)hexan1.getX()-200;
//              y = (int)hexan1.getY()-130;
//              clicked=false;
//              mouseX = 100;
//              mouseY = 100;
//          }
//        }

}


