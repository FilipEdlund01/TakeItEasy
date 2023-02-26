import processing.core.PApplet;
import processing.event.MouseEvent;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

public class UI extends PApplet {

    private static  boolean mouseOverCircle1 = false;
    private static  boolean mouseOverCircle2 = false;
    Rectangle rectangle = new Rectangle(0,0,0,0);
    int R;
    int G;
    int B;


    public int count;
    int n;


    static boolean[] forbidenNumbers = new boolean[27];



    public static boolean isHoldingCard = false;


    double lengthY = Math.sqrt((HexCard.HEX_SIDE_SIZE * HexCard.HEX_SIDE_SIZE) + (HexCard.HEX_SIDE_SIZE / 2 * HexCard.HEX_SIDE_SIZE / 2)); // pocitani vzdalenosti od stredu k hrane pomoci pythagora


    private HexCard currentHexanThatIsMoved = null;


    public boolean showMainStage = false;





    static int randomInt;
    private boolean firstCircleClicked = false;
    private boolean secondCircleClicked = false;


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
private final static UI ui = new UI();

public static UI getInstance(){
    return ui;
}


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

        testMainSTAGE();
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
            text(StartStage.getInstance().getMessage(), Constants.WIDTH.getValue()/2,Constants.HEIGHT.getValue()/2+Constants.rectYdistance.getValue()+30);



        }
        if(secondCircleClicked){
            fill(255,255,255);
            rect(Rectangle.allRectangels[0].getCordX(),Rectangle.allRectangels[0].getCordY(), Rectangle.allRectangels[0].getWIDHT(), Rectangle.allRectangels[0].getHEIGHT());

            fill(0);
            textSize(25);
            text(inputText, Constants.WIDTH.getValue()/2, Constants.HEIGHT.getValue()/2+Constants.rectYdistance.getValue()-5);
            textSize(30);
            text(StartStage.getInstance().getMessage(), Constants.WIDTH.getValue()/2,Constants.HEIGHT.getValue()/2+Constants.rectYdistance.getValue()+30);



        }

        if (!showMainStage) {

            textFont(StartStage.getInstance().f);
            textAlign(CENTER);

            for (int i = 0; i < Circle.allCircels.length; i++) {

                    this.fillCircleWithColour2(Circle.allCircels[i]);

                if (mouseOverCircle1 && i == 0) {
                    this.fillCircleWithColour(Circle.allCircels[i]);
                }
                    if (mouseOverCircle2 && i==1) {
                        this.fillCircleWithColour(Circle.allCircels[i]);
                    }
                    if(i==0){
                        text(StartStage.getInstance().logIn(), Circle.allCircels[i].getCordX(), Circle.allCircels[i].getCordY() + Circle.allCircels[i].getDiameter());

                    }
                    if(i==1){
                        text(StartStage.getInstance().register(), Circle.allCircels[i].getCordX(), Circle.allCircels[i].getCordY() + Circle.allCircels[i].getDiameter());
                    }

                    circle(Circle.allCircels[i].getCordX(), Circle.allCircels[i].getCordY(), Circle.allCircels[i].getDiameter());



                }







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

            rectangleSimulation rectsim1 = new rectangleSimulation(1);
            rectsim1.start();
            // showMainStage = true;
            StartStage.getInstance().setMessage("Please type your username and hit enter");

            firstCircleClicked=true;
            secondCircleClicked=false;


        }
        if (StartStage.getInstance().overCircle(mouseX, mouseY, Circle.allCircels[1].getCordX(), Circle.allCircels[1].getCordY(), Circle.allCircels[1].getDiameter())) {
            System.out.println("kokot");
            rectangleSimulation rectsim2 = new rectangleSimulation(2);
            rectsim2.start();
            StartStage.getInstance().setMessage("Please type username you want to have");
            firstCircleClicked=false;
            secondCircleClicked=true;
          //  API.getInstance().CheckPassword("filip","kk");
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
        checkIfMouseOverCircle();



    }
    private void checkIfMouseOverCircle(){
        mouseOverCircle1=false;
        mouseOverCircle2=false;
        if(StartStage.getInstance().overCircle(mouseX, mouseY, Circle.allCircels[0].getCordX(), Circle.allCircels[0].getCordY(), Circle.allCircels[0].getDiameter())){
            mouseOverCircle1=true;
            mouseOverCircle2=false;
        }
        if((StartStage.getInstance().overCircle(mouseX, mouseY, Circle.allCircels[1].getCordX(), Circle.allCircels[1].getCordY(), Circle.allCircels[1].getDiameter()))) {
            mouseOverCircle2=true;
            mouseOverCircle1=false;


        }






    }

    public void fillCircleWithColour(Circle circle){
        int [] rgb = circle.getDefaultCircleColor();
        fill(rgb[0],rgb[1],rgb[2]);


    }

    public void fillCircleWithColour2(Circle circle){
        int [] rgb = circle.getCIRCLE_COLOR_2();
        fill(rgb[0],rgb[1],rgb[2]);


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
        handleTextField();



    }
    public void handleTextField(){
        // Start typing on any key press
        typing = true;

        // Handle keyboard input
        if (key >' ')  {

            inputText = inputText.substring(0, cursorPos) + inputText.substring(cursorPos)+key;
            cursorPos++;
        } else if (keyCode == BACKSPACE && cursorPos > 0) {
            inputText = inputText.substring(0, cursorPos - 1) + inputText.substring(cursorPos);
            cursorPos--;
        } else if(keyCode == ENTER&& !inputText.isEmpty()){

            switch(StartStage.getInstance().getMessage()) {  // get text written below the text field
                case "Please type your username and hit enter", "User not found":
                    //System.out.println("kokot");
                    userExist.getInstance().handle(new Request(inputText));
                    if (userExist.getInstance().b) {
                        cursorPos = 0;
                        inputText = "";
                        StartStage.getInstance().setMessage("User found, type password");
                       // userExist.getInstance().setNext(passwordHandler.getInstance());

                    }
                    break;
                case "User found, type password","incorrect password":
                    passwordHandler.getInstance().handle(new Request(inputText));
                    if (passwordHandler.getInstance().b) {
                        cursorPos = 0;
                        inputText = "";
                        StartStage.getInstance().setMessage("");
                        System.out.println("found password");
                        rectangleSimulation rectsim = new rectangleSimulation(3);
                        CicrcleSimulation circlesimulation = new CicrcleSimulation();
                        circlesimulation.start();
                        rectsim.start();




                    }
                    break;
                case "Please type username you want to have","User with same name already exist ): try something else":
                    handleNewUser.getInstance().handle(new Request(inputText));
                    if(handleNewUser.getInstance().b){
                        cursorPos = 0;
                        inputText = "";
                        StartStage.getInstance().setMessage("Please type password you want to have");




                    }
                    break;

                case "Please type password you want to have":

                    System.out.println(StartStage.getInstance().getUserName());

                    API.getInstance().InsertNewUserIntoDataBase(StartStage.getInstance().getUserName(), inputText);

                       rectangleSimulation rectsim = new rectangleSimulation(3);
                        CicrcleSimulation circlesimulation = new CicrcleSimulation();
                        circlesimulation.start();
                        rectsim.start();






                    break;
            }





            System.out.println(StartStage.getInstance().getMessage());

        }


    }
    private void testMainSTAGE(){
    if(Rectangle.allRectangels[0].getCordY() == -Rectangle.allRectangels[0].getHEIGHT()*2){
        inputText="";
        showMainStage=true;
    }
    }



}
