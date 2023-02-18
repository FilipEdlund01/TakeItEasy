import processing.core.PApplet;
import processing.event.MouseEvent;
import java.lang.Math;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;


public class Main extends PApplet {

    public  int count;
     int n;





    static boolean[] forbidenNumbers = new boolean[27];

    public static int WIDTH = 1600;
    public static int HEIGHT = 800;

    public static boolean isHoldingCard = false;

    double lengthY = Math.sqrt((HexCard.HEX_SIDE_SIZE * HexCard.HEX_SIDE_SIZE) + (HexCard.HEX_SIDE_SIZE / 2 * HexCard.HEX_SIDE_SIZE / 2)); // pocitani vzdalenosti od stredu k hrane pomoci pythagora


    private HexCard currentHexanThatIsMoved = null;


    int xPixelsofImage = 166 / 2;
    int yPixelsofImge = 140 / 2;

    int x;
    int y;




    public boolean showMainStage = false;
    static int randomInt;

    public static void main(String[] args) {


        CicrcleSimulation simulation = new CicrcleSimulation();
        simulation.start();

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
        try {
            API.getInstance().readFromDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Arrays.fill(forbidenNumbers, true);
        StartStage.getInstance().setF(createFont("Arial",50,true));


        // load all images
        for (HexCard hexan : HexCard.allHexans) {
            hexan.setImage(loadImage(hexan.getImageString()));
        }
        // set up the first image
         generateCard();
      //  System.out.println("papa");
    }

    @Override
    public void draw() {



        background(255, 255, 255);

        if(!showMainStage) {
            textFont(StartStage.getInstance().f);
            textAlign(CENTER);
            text(StartStage.getInstance().printWelcomeText(),width/2,60);

           // println(StartStage.getInstance().printWelcomeText());
            circle(StartStage.getInstance().getCircleX(), StartStage.getInstance().getCircleY(), StartStage.getInstance().getCircleDiameter());
           // text(StartStage.getInstance().printWelcomeText(),WIDTH/2, 50);
        }

        if (showMainStage) {


            // draw board
            for (HexBoard[] hexanBoardColumn : HexBoard.hexBoard) {
                for (HexBoard hexanBoard : hexanBoardColumn) {
                    this.fillWithHexColor(hexanBoard);
                    hexagon(hexanBoard.getXCords(), hexanBoard.getYCords(), HexCard.HEX_SIDE_SIZE);
                    if (hexanBoard.occupiedHexCard != null) {
                        showImage(hexanBoard.occupiedHexCard);
                    }
                }
            }


            showImage(HexCard.allHexans[randomInt]);
        }
    }


    public void showImage(HexCard hexan) {
        image(hexan.getImage(), hexan.getXCords(), hexan.getYCords());
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
    public void mousePressed() {
        if (mouseX > HexCard.allHexans[randomInt].getXCords() && mouseX < HexCard.allHexans[randomInt].getXCords() + HexCard.HEX_SIDE_SIZE * 2 &&
                mouseY > HexCard.allHexans[randomInt].getYCords() && mouseY < HexCard.allHexans[randomInt].getYCords() + HexCard.HEX_SIDE_SIZE * 2) {
            currentHexanThatIsMoved = HexCard.allHexans[randomInt];
            isHoldingCard = true;
        }

        if (StartStage.getInstance().overCircle(mouseX,mouseY)) {
            System.out.println("ahoj");
            showMainStage=true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (currentHexanThatIsMoved != null) {
            currentHexanThatIsMoved.setXCords(mouseX - xPixelsofImage);//aby se mys vycentrovala na prostredek obrazku
            currentHexanThatIsMoved.setYCords(mouseY - yPixelsofImge);
        }
        for (int i = 0; i < HexBoard.hexBoard.length; i++) {
            for (int j = 0; j < HexBoard.hexBoard[i].length; j++) {
                if (HexBoard.hexBoard[i][j].getXCords() + HexCard.HEX_SIDE_SIZE > mouseX && HexBoard.hexBoard[i][j].getYCords() + lengthY > mouseY && HexBoard.hexBoard[i][j].getXCords() - HexCard.HEX_SIDE_SIZE < mouseX && HexBoard.hexBoard[i][j].getYCords() - lengthY < mouseY) {

                    HexBoard.hexBoard[i][j].color = HexBoard.SELECT_HEX_COLOR;
                } else {
                    HexBoard.hexBoard[i][j].color = HexBoard.DEFAULT_HEX_COLOR;

                }
            }
        }
    }


    @Override
    public void mouseReleased() {
        //   System.out.println(HexBoard.evaluateLine(0,0,0));
        if (!isHoldingCard) return;

        HexBoard foundHexBoard = null;

        for (int i = 0; i < HexBoard.hexBoard.length; i++) {
            for (int j = 0; j < HexBoard.hexBoard[i].length; j++) {
                if (HexBoard.hexBoard[i][j].getXCords() + HexCard.HEX_SIDE_SIZE > mouseX && HexBoard.hexBoard[i][j].getYCords() + lengthY > mouseY && HexBoard.hexBoard[i][j].getXCords() - HexCard.HEX_SIDE_SIZE < mouseX && HexBoard.hexBoard[i][j].getYCords() - lengthY < mouseY) {

                    if (HexBoard.hexBoard[i][j].occupiedHexCard != null || foundHexBoard != null) {
                        currentHexanThatIsMoved.resetToStartingCords();
                        currentHexanThatIsMoved = null;
                        isHoldingCard = false;
                        return;
                    }

                    foundHexBoard = HexBoard.hexBoard[i][j];


                }

            }
        }


        if (foundHexBoard != null) {
            currentHexanThatIsMoved.xCords = foundHexBoard.xCords - HexCard.HEX_SIDE_SIZE + HexCard.PUT_INBOARD_PIXEL_BIAS_X;
            currentHexanThatIsMoved.yCords = foundHexBoard.yCords - HexCard.HEX_SIDE_SIZE + HexCard.PUT_INBOARD_PIXEL_BIAS_Y;
            foundHexBoard.color = HexBoard.DEFAULT_HEX_COLOR;
            foundHexBoard.occupiedHexCard = currentHexanThatIsMoved;


            count++;

           generateCard();
        }

        currentHexanThatIsMoved = null;
        isHoldingCard = false;

    }

    @Override
    public void mouseMoved() {
        if(StartStage.getInstance().overCircle(mouseX,mouseY)){
            fill(204, 102, 0);
        }else{
            fill(0,0,0);
        }
    }

    public void fillWithHexColor(HexBoard boardHex) {
        int[] rgbColor = boardHex.getColor();
        fill(rgbColor[0], rgbColor[1], rgbColor[2]);
    }

    public  void  generateCard() {
        if (count == 19) { //because count is starting at 1
            System.out.println("konec hry");
            int result = CalculateScore.calculateScore();
            System.out.println("Tvoje score "+ result);


        }
        int min = 0;
        int max = HexCard.allHexans.length - 1;
        boolean ocupied = true;

        while (ocupied) {
            Random random = new Random();
            int r = (random.nextInt((max - min) + 1) + min);
            if (forbidenNumbers[r]) {
                randomInt =r;
                forbidenNumbers[r] = false;
                ocupied = false;
            }
        }

    }





}


