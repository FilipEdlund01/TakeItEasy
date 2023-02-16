import processing.core.PApplet;
import processing.event.MouseEvent;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Main extends PApplet {

    public static boolean b = false;
    static int randomInt;

    public boolean[] forbidenNumbers = new boolean[27];
    ArrayList<HexCard> listOfHexCards = new ArrayList<HexCard>();
    public static int WIDTH = 1600;
    public static int HEIGHT = 800;

    public static boolean isHoldingCard = false;

    double lengthY = Math.sqrt((HexCard.HEX_SIDE_SIZE * HexCard.HEX_SIDE_SIZE) + (HexCard.HEX_SIDE_SIZE / 2 * HexCard.HEX_SIDE_SIZE / 2)); // pocitani vzdalenosti od stredu k hrane pomoci pythagora


    private HexCard currentHexanThatIsMoved = null;


    int xPixelsofImage = 166 / 2;
    int yPixelsofImge = 140 / 2;

    int x;
    int y;


    int count = 0;

    int first_row_down;

    int second_row_down;
    int third_row_down;
    int fourth_row_down;
    int fifth_row_down;

    int first_up_diagonal;
    int second_up_diagonal;
    int third_up_diagonal;
    int fourth_up_diagonal;
    int fifth_up_diagonal;

    int first_down_diagonal;
    int second_down_diagonal;
    int third_down_diagonal;
    int fourth_down_diagonal;
    int fifth_down_diagonal;

    int RESULT;









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
        Arrays.fill(forbidenNumbers, true);
        // load all images
        for (HexCard hexan : HexCard.allHexans) {
            hexan.setImage(loadImage(hexan.getImageString()));
        }
        generateCard();
    }

    @Override
    public void draw() {

        background(255, 255, 255);


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
        ;

        showImage(HexCard.allHexans[randomInt]);
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

    public void fillWithHexColor(HexBoard boardHex) {
        int[] rgbColor = boardHex.getColor();
        fill(rgbColor[0], rgbColor[1], rgbColor[2]);
    }


    public void generateCard() {
        if (count == 19) { //because count is starting at 1
            System.out.println("konec hry");
            calculateScore();
            System.out.println("Tvoje score "+ RESULT);


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

    public void calculateScore() {
        boolean b = true;
        int a1;
        int a2;
        int a3;
        int a4;
        int a5;


        a1 = HexBoard.evaluateHexLine(0,0,HexBuilder.LINE_UP_INDEX);
        a2 = HexBoard.evaluateHexLine(0,1,HexBuilder.LINE_UP_INDEX);
        a3 = HexBoard.evaluateHexLine(0,2,HexBuilder.LINE_UP_INDEX);
        if(a1==a2 && a2 ==a3){
            first_row_down = a1*3;
        }else {
            first_row_down =0;
        }

        //evaluating second column down
        a1 = HexBoard.evaluateHexLine(1,0,HexBuilder.LINE_UP_INDEX);
        a2 = HexBoard.evaluateHexLine(1,1,HexBuilder.LINE_UP_INDEX);
        a3 = HexBoard.evaluateHexLine(1,2,HexBuilder.LINE_UP_INDEX);
        a4 = HexBoard.evaluateHexLine(1,3,HexBuilder.LINE_UP_INDEX);

        if(a1==a2 && a2 == a3 && a3 ==a4){
            second_row_down = a1*4;
        }else {
            second_row_down =0;
        }

        //evaluating third column down

        a1 = HexBoard.evaluateHexLine(2,0,HexBuilder.LINE_UP_INDEX);
        a2 = HexBoard.evaluateHexLine(2,1,HexBuilder.LINE_UP_INDEX);
        a3 = HexBoard.evaluateHexLine(2,2,HexBuilder.LINE_UP_INDEX);
        a4 = HexBoard.evaluateHexLine(2,3,HexBuilder.LINE_UP_INDEX);
        a5 = HexBoard.evaluateHexLine(2,4,HexBuilder.LINE_UP_INDEX);

        if(a1==a2 && a2 == a3 && a3 == a4 && a4 == a5){
            third_row_down = a1*5;
        }else {
            third_row_down =0;
        }

        //evaluating fourth column down
        a1 = HexBoard.evaluateHexLine(3,0,HexBuilder.LINE_UP_INDEX);
        a2 = HexBoard.evaluateHexLine(3,1,HexBuilder.LINE_UP_INDEX);
        a3 = HexBoard.evaluateHexLine(3,2,HexBuilder.LINE_UP_INDEX);
        a4 = HexBoard.evaluateHexLine(3,3,HexBuilder.LINE_UP_INDEX);

        if(a1==a2 && a2 == a3 && a3 ==a4){
            fourth_row_down = a1*4;
        }else {
            fourth_row_down =0;
        }

        //evaluating fifth column

        a1 = HexBoard.evaluateHexLine(4,0,HexBuilder.LINE_UP_INDEX);
        a2 = HexBoard.evaluateHexLine(4,1,HexBuilder.LINE_UP_INDEX);
        a3 = HexBoard.evaluateHexLine(4,2,HexBuilder.LINE_UP_INDEX);
        if(a1==a2 && a2 ==a3){
            fifth_row_down = a1*3;
        }else {
            fifth_row_down =0;
        }





            //evaluating the left down diagonal line from HexBoard[0][0]

             a1 = HexBoard.evaluateHexLine(0,0,HexBuilder.LINE_LEFT_DOWN_INDEX);
             a2 = HexBoard.evaluateHexLine(1,0,HexBuilder.LINE_LEFT_DOWN_INDEX);
             a3 = HexBoard.evaluateHexLine(2,0,HexBuilder.LINE_LEFT_DOWN_INDEX);


            if(a1==a2 && a2==a3){

                first_up_diagonal = a1*3;

            }else{
                first_up_diagonal= 0;
            }


        //evaluating the left down diagonal line from HexBoard[0][1]

        a1 = HexBoard.evaluateHexLine(0,1,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a2 = HexBoard.evaluateHexLine(1,1,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a3 = HexBoard.evaluateHexLine(2,1,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a4 = HexBoard.evaluateHexLine(3,0,HexBuilder.LINE_LEFT_DOWN_INDEX);


        if(a1==a2 && a2==a3 && a3 ==a4){

            second_up_diagonal = a1*4;

        }else{
            second_up_diagonal= 0;
        }

        //evaluating the left down diagonal line from HexBoard[0][2]

        a1 = HexBoard.evaluateHexLine(0,2,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a2 = HexBoard.evaluateHexLine(1,2,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a3 = HexBoard.evaluateHexLine(2,2,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a4 = HexBoard.evaluateHexLine(3,2,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a5 = HexBoard.evaluateHexLine(4,0,HexBuilder.LINE_LEFT_DOWN_INDEX);


        if(a1==a2 && a2 == a3 && a3 == a4 && a4 == a5){

            third_up_diagonal = a1*5;

        }else{
            third_up_diagonal= 0;
        }

        //evaluating the left down diagonal line from HexBoard[1][3]

        a1 = HexBoard.evaluateHexLine(1,3,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a2 = HexBoard.evaluateHexLine(2,3,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a3 = HexBoard.evaluateHexLine(3,2,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a4 = HexBoard.evaluateHexLine(4,1,HexBuilder.LINE_LEFT_DOWN_INDEX);



        if(a1==a2 && a2 == a3 && a3 == a4){

            fourth_up_diagonal = a1*4;

        }else{
            fourth_up_diagonal= 0;
        }

        //evaluating the left down diagonal line from HexBoard[2][4]

        a1 = HexBoard.evaluateHexLine(2,4,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a2 = HexBoard.evaluateHexLine(3,3,HexBuilder.LINE_LEFT_DOWN_INDEX);
        a3 = HexBoard.evaluateHexLine(4,2,HexBuilder.LINE_LEFT_DOWN_INDEX);




        if(a1==a2 && a2 == a3){

            fifth_up_diagonal = a1*3;

        }else{
            fifth_up_diagonal= 0;
        }

        //evaluating the right down diagonal line from HexBoard[2][0]
        a1 = HexBoard.evaluateHexLine(2,0,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a2 = HexBoard.evaluateHexLine(3,0,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a3 = HexBoard.evaluateHexLine(4,0,HexBuilder.LINE_RIGHT_DOWN_INDEX);




        if(a1==a2 && a2 == a3){

            first_down_diagonal= a1*3;

        }else{
            first_down_diagonal= 0;
        }
        //evaluating the right down diagonal line from HexBoard[1][0]

        a1 = HexBoard.evaluateHexLine(1,0,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a2 = HexBoard.evaluateHexLine(2,1,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a3 = HexBoard.evaluateHexLine(3,1,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a4 = HexBoard.evaluateHexLine(4,1,HexBuilder.LINE_RIGHT_DOWN_INDEX);




        if(a1==a2 && a2 == a3 && a3 == a4){

            second_down_diagonal= a1*4;

        }else{
            second_down_diagonal= 0;
        }
        //evaluating the right down diagonal line from HexBoard[0][0]

        a1 = HexBoard.evaluateHexLine(0,0,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a2 = HexBoard.evaluateHexLine(1,1,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a3 = HexBoard.evaluateHexLine(2,2,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a4 = HexBoard.evaluateHexLine(3,2,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a5 = HexBoard.evaluateHexLine(4,2,HexBuilder.LINE_RIGHT_DOWN_INDEX);




        if(a1==a2 && a2 == a3 && a3 ==a4 && a4 == a5){

            third_down_diagonal= a1*5;

        }else{
            third_down_diagonal= 0;
        }

        //evaluating the right down diagonal line from HexBoard[0][1]

        a1 = HexBoard.evaluateHexLine(0,1,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a2 = HexBoard.evaluateHexLine(1,2,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a3 = HexBoard.evaluateHexLine(2,3,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a4 = HexBoard.evaluateHexLine(3,3,HexBuilder.LINE_RIGHT_DOWN_INDEX);




        if(a1==a2 && a2 == a3 && a3 ==a4){

            fourth_down_diagonal= a1*4;

        }else{
            fourth_down_diagonal= 0;
        }

        //evaluating the right down diagonal line from HexBoard[0][2]

        a1 = HexBoard.evaluateHexLine(0,2,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a2 = HexBoard.evaluateHexLine(1,3,HexBuilder.LINE_RIGHT_DOWN_INDEX);
        a3 = HexBoard.evaluateHexLine(2,4,HexBuilder.LINE_RIGHT_DOWN_INDEX);





        if(a1==a2 && a2 == a3 ){

            fifth_down_diagonal= a1*3;

        }else{
            fifth_down_diagonal= 0;
        }

        RESULT = first_row_down+second_row_down+third_row_down+fourth_row_down+fifth_row_down+first_up_diagonal+second_up_diagonal+third_up_diagonal+fourth_up_diagonal+fifth_up_diagonal+first_down_diagonal+second_down_diagonal+third_down_diagonal+fourth_down_diagonal+fifth_down_diagonal;
        

    }
}


