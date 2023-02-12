import processing.core.PApplet;
import processing.event.MouseEvent;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Main extends PApplet{
    private int boardX;
    private int boardY;

    public static boolean b = false;
    static int randomInt;

    public boolean[] forbidenNumbers = new boolean[27];
    ArrayList<HexCard> listOfHexCards = new ArrayList<HexCard>();



    public static int WIDTH = 1600;
    public static int HEIGHT = 800;




    private boolean clicked = false;



    double lengthY = Math.sqrt(( HexCard.HEX_SIDE_SIZE* HexCard.HEX_SIDE_SIZE)+(HexCard.HEX_SIDE_SIZE/2* HexCard.HEX_SIDE_SIZE/2)); // pocitani vzdalenosti od stredu k hrane pomoci pythagora


    private HexCard mouseOverHexan = null;

    public boolean yes;

    public int n = 0;
    int xPixelsofImage=160/2;
    int yPixelsofImge=150/2;

    int x;
    int y;

    int counter;




    public static void main(String[] args) {

        HexBuilder.getInstance().buildHexans();
        HexBuilder.getInstance().buildHexBoard();


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
        Arrays.fill(forbidenNumbers,true);
        // load all images
       for (HexCard hexan: HexCard.allHexans) {
            hexan.setImage(loadImage(hexan.getImageString()));
        }
       generateCard();





    }

     @Override
    public void draw() {

        if (counter==20){
            background(0,255,0);
        }else{




            background(255,255,255);


            // draw board
            for (HexBoard[] hexanBoardColumn: HexBoard.hexBoard) {
                for (HexBoard hexanBoard: hexanBoardColumn) {
                    this.fillWithHexColor(hexanBoard);
                    hexagon(hexanBoard.getXCords(), hexanBoard.getYCords(), HexCard.HEX_SIDE_SIZE);
                }
            };
            HexBoard hex1 = HexBoard.hexBoard[3][2];
            HexBoard hex2 = HexBoard.hexBoard[2][2];
            HexBoard hexBoard = hex2.neighbours[HexBoard.RIGHT_TOP_NEIGHBOUR];
            



            checkHexOverBoard();




            showImage(HexCard.allHexans[randomInt]);
            for (HexCard hexan: listOfHexCards) {

                showImages(hexan);
            }


        }



     }

     public void checkHexOverBoard() {
        int count =1;


         if (clicked && mouseOverHexan != null) {
             n = 1;
             //yes = false;
             for (int i = 0; i < HexBoard.hexBoard.length; i++) {
                 for (int j = 0; j < HexBoard.hexBoard[i].length; j++) {

                     if (HexBoard.hexBoard[i][j].getXCords() + HexCard.HEX_SIDE_SIZE > mouseX && HexBoard.hexBoard[i][j].getYCords() + lengthY > mouseY &&
                             HexBoard.hexBoard[i][j].getXCords() - HexCard.HEX_SIDE_SIZE < mouseX && HexBoard.hexBoard[i][j].getYCords() - lengthY < mouseY) {
                         fill(255, 0, 0);
                         hexagon(HexBoard.hexBoard[i][j].getXCords(), HexBoard.hexBoard[i][j].getYCords(), HexCard.HEX_SIDE_SIZE);
                         count++;

                         // cardOverBoard=true;
                         //System.out.println("over");

                        /* boardX = HexBoard.hexBoard[i][j].getXCords();
                         boardY = HexBoard.hexBoard[i][j].getYCords();*/
                         x = HexBoard.hexBoard[i][j].getXCords();
                         y = HexBoard.hexBoard[i][j].getYCords();




                     }
                 }
             }
             if(count==2){
                 n=2;
                 boardX=x;
                 boardY=y;


             }


         } else {
             n = 0;
         }








    }
    public void showImage(HexCard hexan){
        image(hexan.getImage(),HexCard.allHexans[randomInt].getXCords() , HexCard.allHexans[randomInt].getYCords());

    }

    public void showImages(HexCard hexan){

        image(hexan.getImage(), hexan.getXCords(),hexan.getYCords());
    }

    public void addNeighbours(){

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






    @Override
    public void mouseMoved(MouseEvent event) {
        //checkCollision();
        // cos p = b/c
        // a = cos b * c


       // float lengthY = cos(PI/6) * Hexan.HEXAN_SIDE_SIZE;
        int a = HexCard.HEX_SIDE_SIZE;





        // trida HexCard extenduje Hex
        if((HexCard.allHexans[randomInt].getXCords()+xPixelsofImage) + HexCard.HEX_SIDE_SIZE > mouseX && (HexCard.allHexans[randomInt].getYCords()+yPixelsofImge)+  lengthY  > mouseY &&
                (HexCard.allHexans[randomInt].getXCords()+xPixelsofImage) - HexCard.HEX_SIDE_SIZE < mouseX && (HexCard.allHexans[randomInt].getYCords()+yPixelsofImge) - lengthY < mouseY){
            mouseOverHexan = HexCard.allHexans[randomInt];
           // System.out.println("over");
        }else {
            mouseOverHexan = null;
        }




        if(clicked && mouseOverHexan != null){
           /* HexCard.allHexans[randomInt].setXCords(mouseX);
            HexCard.allHexans[randomInt].setYCords(mouseY);*/


            mouseOverHexan.setXCords(mouseX-xPixelsofImage);//aby se mys vycentrovala na prostredek obrazku
            mouseOverHexan.setYCords(mouseY-yPixelsofImge);




        }

    }

    @Override
    public void mousePressed() {

        if(n==2){
            System.out.println("ahoj");
            HexCard.allHexans[randomInt].setXCords(boardX-xPixelsofImage);
            HexCard.allHexans[randomInt].setYCords(boardY-yPixelsofImge);
            listOfHexCards.add(HexCard.allHexans[randomInt]);
            generateCard();

        }



        clicked = !clicked;


    }

    public void fillWithHexColor(HexBoard boardHex){
        int[] rgbColor = boardHex.getColor();
        fill(rgbColor[0], rgbColor[1], rgbColor[2]);
    }



    public  void generateCard(){
        int min = 0;
        int max = HexCard.allHexans.length-1;
        boolean ocupied = true;

        while(ocupied){
            Random random = new Random();
            int r=(random.nextInt((max - min) + 1) + min);
            if(forbidenNumbers[r]){
                randomInt = r;
                forbidenNumbers[r]=false;
                ocupied=false;
            }
        }
        counter++;



    }

}


