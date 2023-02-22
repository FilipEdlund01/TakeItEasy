
import g4p_controls.GCScheme;
import g4p_controls.GEditableTextControl;
import g4p_controls.GEvent;
import g4p_controls.GTextField;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Main extends PApplet {

    GTextField textField;

    public int count;
    int n;


    static boolean[] forbidenNumbers = new boolean[27];

    /*public static int WIDTH = 1600;
    public static int HEIGHT = 800;*/

    public static boolean isHoldingCard = false;
    ArrayList<GTextField> textFieldLetters = new ArrayList<GTextField>();

    double lengthY = Math.sqrt((HexCard.HEX_SIDE_SIZE * HexCard.HEX_SIDE_SIZE) + (HexCard.HEX_SIDE_SIZE / 2 * HexCard.HEX_SIDE_SIZE / 2)); // pocitani vzdalenosti od stredu k hrane pomoci pythagora


    private HexCard currentHexanThatIsMoved = null;




    /*int xPixelsofImage = 166 / 2;
    int yPixelsofImge = 140 / 2;*/

    int x;
    int y;


    public boolean showMainStage = false;
    static int randomInt;
    private boolean firstCircleClicked = false;
    private boolean secondBooleanClicked = false;

    public static void main(String[] args) {
        StartStage.getInstance().buildCircles();


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
        size(Constants.WIDTH.getValue(), Constants.HEIGHT.getValue(), "processing.awt.PGraphicsJava2D");

    }

    public void textfield_change(GTextField source, GEvent event) { //_CODE_:textfield2:400022:
        println("textfield - GTextField >> GEvent." + event + " @ " + millis());
    } //_CODE_:textfield2:400022:



    // Create all the GUI controls.
// autogenerated do not edit
    /*public void createGUI(){
        G4P.messagesEnabled(false);
        G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
        G4P.setCursor(ARROW);


       G window1 = new GWindow(this, "Window title", 0, 0, 240, 120, false, JAVA2D);
        window1.addDrawHandler(this, "win_draw1");
        textfield2 = new GTextField(window1.papplet, 40, 45, 160, 30, G4P.SCROLLBARS_NONE);
        textfield2.setOpaque(true);
        textfield2.addEventHandler(this, "textfield2_change1");
    }*/

    // Variable declarations
// autogenerated do not edit


    @Override
    public void setup() {

       // customGUI();
        textField = new GTextField(this, Constants.textFieldx0.getValue(), Constants.textFieldy0.getValue(), Constants.textFieldXdistance.getValue(), Constants.textFieldYdistance.getValue()) ;

        // set the default text
        ///textField.setText("Type here");

        // set the font size and color
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setOpaque(true);
        textField.setLocalColorScheme(GCScheme.RED_SCHEME);
        textField.setOpaque(true);
        textField.addEventHandler(this,"textfield_change");
        textField.addA

        textFieldLetters.add(textField);






        //fill(40,66,159);

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
        if(firstCircleClicked){


            for(GTextField t : textFieldLetters){
                 t.draw();

            }

          //  textField.addEventHandler(textField, "handleTextEvents");


        }

        if (!showMainStage) {

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

        for(GTextField f : textFieldLetters){
            f.addEventHandler(textField,"S");
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
        //  textField.addEventHandler(key,String.valueOf(keyCode));
        for(GTextField f : textFieldLetters){
            //f.isTextEditEnabled();
            //f.addEventHandler(f, String.valueOf(key));
            //f.keyEvent(f,String.valueOf(key));
            //f.te

        }

       //handleTextEvents(keyCode,key);

       // handleTextEvents(textField,event.getKey());


    }

    public void handleTextEvents(GEditableTextControl source, GEvent event) {
        if (event.getType() == textField.keyEvent(String);) {
            String key = event.getKeyChar() + "";
            if (key.matches("[A-Za-z]")) { // only accept letters
                source.insertString(key);
            }
        }
    }
    }


}





