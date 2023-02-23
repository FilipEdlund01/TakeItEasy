import processing.core.PApplet;
import processing.event.MouseEvent;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

public class UI extends PApplet {
    Rectangle rectangle = new Rectangle(0,0,0,0);


    public int count;
    int n;


    static boolean[] forbidenNumbers = new boolean[27];



    public static boolean isHoldingCard = false;


    double lengthY = Math.sqrt((HexCard.HEX_SIDE_SIZE * HexCard.HEX_SIDE_SIZE) + (HexCard.HEX_SIDE_SIZE / 2 * HexCard.HEX_SIDE_SIZE / 2)); // pocitani vzdalenosti od stredu k hrane pomoci pythagora


    private HexCard currentHexanThatIsMoved = null;


    public boolean showMainStage = false;
    static int randomInt;
    private boolean firstCircleClicked = false;
    private boolean secondBooleanClicked = false;


    String inputText = "";
    boolean typing = false;
    int cursorPos = 0;
    int cursorBlink = 0;
    public int rectX;

    public void setRectX(int rectX) {
        this.rectX = rectX;
    }
    /*public static void setRectX(int rectX) {
        this.rectX = rectX;
    }*/



    @Override
    public void windowResizable(boolean resizable) {
        super.windowResizable(true);
    }

    @Override
    public void settings() {
        size(Constants.WIDTH.getValue(), Constants.HEIGHT.getValue(), "processing.awt.PGraphicsJava2D");

    }






    @Override
    public void setup() {



        try {
            API.getInstance().readFromDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        API.getInstance().InsertNewUserToDatabase();

        Arrays.fill(forbidenNumbers, true);
        StartStage.getInstance().setF(createFont("Arial", 50, true));


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


        frameRate(100);



        background(255, 255, 255);
        strokeWeight(3);
        stroke(0, 0, 0);

        // set no fill color

        // draw an empty rectangle




        if(firstCircleClicked){
            fill(255,255,255);
            rect(Rectangle.allRectangels[0].getCordX(),Rectangle.allRectangels[0].getCordY(), Rectangle.allRectangels[0].getWIDHT(), Rectangle.allRectangels[0].getHEIGHT());

            fill(0);
            textSize(25);
            text(inputText, Constants.WIDTH.getValue()/2, Constants.HEIGHT.getValue()/2+Constants.rectYdistance.getValue()-5);
            textSize(30);
            text(StartStage.getInstance().prinsStringForUser(0), Constants.WIDTH.getValue()/2,Constants.HEIGHT.getValue()/2+Constants.rectYdistance.getValue()+30);




           /* for(GTextField t : textFieldLetters){
                 t.draw();

            }*/
            // textField.draw();




        }

        if (!showMainStage) {
            fill(0, 0, 255);

            textFont(StartStage.getInstance().f);
            //textAlign(CENTER);
            //text(StartStage.getInstance().printWelcomeText(),width/2,60);

            textAlign(CENTER);
            text(StartStage.getInstance().logIn(), Circle.allCircels[0].getCordX(), Circle.allCircels[0].getCordY() + Circle.allCircels[0].getDiameter());
            textAlign(CENTER);
            text(StartStage.getInstance().register(), Circle.allCircels[1].getCordX(), Circle.allCircels[1].getCordY() + Circle.allCircels[1].getDiameter());


            // println(StartStage.getInstance().printWelcomeText());

            for (int i = 0; i < Circle.allCircels.length; i++) {
                circle(Circle.allCircels[i].getCordX(), Circle.allCircels[i].getCordY(), Circle.allCircels[i].getDiameter());
            }


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
    public void mouseClicked() {
        if (StartStage.getInstance().overCircle(mouseX, mouseY, Circle.allCircels[0].getCordX(), Circle.allCircels[0].getCordY(), Circle.allCircels[0].getDiameter())) {
            System.out.println("First circle");
            rectangleSimulation rectsim = new rectangleSimulation();
            rectsim.start();
            // showMainStage = true;

            firstCircleClicked=true;

        }
        if (StartStage.getInstance().overCircle(mouseX, mouseY, Circle.allCircels[1].getCordX(), Circle.allCircels[1].getCordY(), Circle.allCircels[1].getDiameter())) {
            System.out.println("Second circle");
            // showMainStage = true;
        }

    }

    @Override
    public void mousePressed() {
        if (mouseX > HexCard.allHexans[randomInt].getXCords() && mouseX < HexCard.allHexans[randomInt].getXCords() + HexCard.HEX_SIDE_SIZE * 2 &&
                mouseY > HexCard.allHexans[randomInt].getYCords() && mouseY < HexCard.allHexans[randomInt].getYCords() + HexCard.HEX_SIDE_SIZE * 2) {
            currentHexanThatIsMoved = HexCard.allHexans[randomInt];
            isHoldingCard = true;
        }







    }


    @Override
    public void mouseDragged(MouseEvent event) {
        if (currentHexanThatIsMoved != null) {
            currentHexanThatIsMoved.setXCords(mouseX - Constants.xPixelsofImage.getValue());//aby se mys vycentrovala na prostredek obrazku
            currentHexanThatIsMoved.setYCords(mouseY - Constants.yPixelsofImage.getValue());
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

        if (StartStage.getInstance().overCircle(mouseX, mouseY, Circle.allCircels[0].getCordX(), Circle.allCircels[0].getCordY(), Circle.allCircels[0].getDiameter()) || (StartStage.getInstance().overCircle(mouseX, mouseY, Circle.allCircels[1].getCordX(), Circle.allCircels[1].getCordY(), Circle.allCircels[1].getDiameter()))) {
            //fill(204, 102, 0);

            //pushStyle();  // Start a new style
            // strokeWeight(40);
            fill(204, 153, 0);
            //circle(Circle.allCircels[i].getCordX(), Circle.allCircels[i].getCordY(), Circle.allCircels[i].getDiameter());
        } else {
            fill(40, 66, 159);
        }


    }



    public void fillWithHexColor(HexBoard boardHex) {
        int[] rgbColor = boardHex.getColor();
        fill(rgbColor[0], rgbColor[1], rgbColor[2]);
    }

    public void generateCard() {
        if (count == 19) { //because count is starting at 1
            System.out.println("konec hry");
            int result = CalculateScore.calculateScore();
            System.out.println("Tvoje score " + result);


        }
        int min = 0;
        int max = HexCard.allHexans.length - 1;
        boolean ocupied = true;

        while (ocupied) {
            Random random = new Random();
            int r = (random.nextInt((max - min) + 1) + min);
            if (forbidenNumbers[r]) {
                randomInt = r;
                forbidenNumbers[r] = false;
                ocupied = false;
            }
        }

    }
    @Override
    public void keyPressed() {

        // Start typing on any key press
        typing = true;

        // Handle keyboard input
        if (key >= ' ') {
            inputText = inputText.substring(0, cursorPos) + key + inputText.substring(cursorPos);
            cursorPos++;
        } else if (keyCode == BACKSPACE && cursorPos > 0) {
            inputText = inputText.substring(0, cursorPos - 1) + inputText.substring(cursorPos);

        } else if(keyCode == ENTER){

            //chain of responsibility find if user exist in database
            loginHandler  userExist = new userExist();
            loginHandler userDontExist = new userDontExist();

            userExist.setNext(userDontExist);
            userExist.handle(new Request(inputText));
        }
    }



}
