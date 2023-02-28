public class CalculateScore {

    static int first_row_down;

    static int second_row_down;
    static int third_row_down;
    static int fourth_row_down;
    static int fifth_row_down;

    static int first_up_diagonal;
    static int second_up_diagonal;
    static int third_up_diagonal;
    static int fourth_up_diagonal;
    static int fifth_up_diagonal;

    static int first_down_diagonal;
    static int second_down_diagonal;
    static int third_down_diagonal;
    static int fourth_down_diagonal;
    static int fifth_down_diagonal;

    public static int RESULT;

    public int getRESULT() {
        return RESULT;
    }

    public static int calculateScore() {
        boolean b = true;
        int a1;
        int a2;
        int a3;
        int a4;
        int a5;


        a1 = HexBoard.evaluateHexLine(0,0,Constants.LINE_UP_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(0,1,Constants.LINE_UP_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(0,2,Constants.LINE_UP_INDEX.getValue());
        if(a1==a2 && a2 ==a3){
            first_row_down = a1*3;
        }else {
            first_row_down =0;
        }

        //evaluating second column down
        a1 = HexBoard.evaluateHexLine(1,0,Constants.LINE_UP_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(1,1,Constants.LINE_UP_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(1,2,Constants.LINE_UP_INDEX.getValue());
        a4 = HexBoard.evaluateHexLine(1,3,Constants.LINE_UP_INDEX.getValue());

        if(a1==a2 && a2 == a3 && a3 ==a4){
            second_row_down = a1*4;
        }else {
            second_row_down =0;
        }

        //evaluating third column down

        a1 = HexBoard.evaluateHexLine(2,0,Constants.LINE_UP_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(2,1,Constants.LINE_UP_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(2,2,Constants.LINE_UP_INDEX.getValue());
        a4 = HexBoard.evaluateHexLine(2,3,Constants.LINE_UP_INDEX.getValue());
        a5 = HexBoard.evaluateHexLine(2,4,Constants.LINE_UP_INDEX.getValue());

        if(a1==a2 && a2 == a3 && a3 == a4 && a4 == a5){
            third_row_down = a1*5;
        }else {
            third_row_down =0;
        }

        //evaluating fourth column down
        a1 = HexBoard.evaluateHexLine(3,0,Constants.LINE_UP_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(3,1,Constants.LINE_UP_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(3,2,Constants.LINE_UP_INDEX.getValue());
        a4 = HexBoard.evaluateHexLine(3,3,Constants.LINE_UP_INDEX.getValue());

        if(a1==a2 && a2 == a3 && a3 ==a4){
            fourth_row_down = a1*4;
        }else {
            fourth_row_down =0;
        }

        //evaluating fifth column

        a1 = HexBoard.evaluateHexLine(4,0,Constants.LINE_UP_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(4,1,Constants.LINE_UP_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(4,2,Constants.LINE_UP_INDEX.getValue());
        if(a1==a2 && a2 ==a3){
            fifth_row_down = a1*3;
        }else {
            fifth_row_down =0;
        }





        //evaluating the left down diagonal line from HexBoard[0][0]

        a1 = HexBoard.evaluateHexLine(0,0,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(1,0,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(2,0,Constants.LINE_LEFT_DOWN_INDEX.getValue());


        if(a1==a2 && a2==a3){

            first_up_diagonal = a1*3;

        }else{
            first_up_diagonal= 0;
        }


        //evaluating the left down diagonal line from HexBoard[0][1]

        a1 = HexBoard.evaluateHexLine(0,1,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(1,1,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(2,1,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a4 = HexBoard.evaluateHexLine(3,0,Constants.LINE_LEFT_DOWN_INDEX.getValue());


        if(a1==a2 && a2==a3 && a3 ==a4){

            second_up_diagonal = a1*4;

        }else{
            second_up_diagonal= 0;
        }

        //evaluating the left down diagonal line from HexBoard[0][2]

        a1 = HexBoard.evaluateHexLine(0,2,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(1,2,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(2,2,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a4 = HexBoard.evaluateHexLine(3,2,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a5 = HexBoard.evaluateHexLine(4,0,Constants.LINE_LEFT_DOWN_INDEX.getValue());


        if(a1==a2 && a2 == a3 && a3 == a4 && a4 == a5){

            third_up_diagonal = a1*5;

        }else{
            third_up_diagonal= 0;
        }

        //evaluating the left down diagonal line from HexBoard[1][3]

        a1 = HexBoard.evaluateHexLine(1,3,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(2,3,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(3,2,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a4 = HexBoard.evaluateHexLine(4,1,Constants.LINE_LEFT_DOWN_INDEX.getValue());



        if(a1==a2 && a2 == a3 && a3 == a4){

            fourth_up_diagonal = a1*4;

        }else{
            fourth_up_diagonal= 0;
        }

        //evaluating the left down diagonal line from HexBoard[2][4]

        a1 = HexBoard.evaluateHexLine(2,4,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(3,3,Constants.LINE_LEFT_DOWN_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(4,2,Constants.LINE_LEFT_DOWN_INDEX.getValue());




        if(a1==a2 && a2 == a3){

            fifth_up_diagonal = a1*3;

        }else{
            fifth_up_diagonal= 0;
        }

        //evaluating the right down diagonal line from HexBoard[2][0]
        a1 = HexBoard.evaluateHexLine(2,0,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(3,0,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(4,0,Constants.LINE_RIGHT_DOWN_INDEX.getValue());




        if(a1==a2 && a2 == a3){

            first_down_diagonal= a1*3;

        }else{
            first_down_diagonal= 0;
        }
        //evaluating the right down diagonal line from HexBoard[1][0]

        a1 = HexBoard.evaluateHexLine(1,0,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(2,1,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(3,1,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a4 = HexBoard.evaluateHexLine(4,1,Constants.LINE_RIGHT_DOWN_INDEX.getValue());




        if(a1==a2 && a2 == a3 && a3 == a4){

            second_down_diagonal= a1*4;

        }else{
            second_down_diagonal= 0;
        }
        //evaluating the right down diagonal line from HexBoard[0][0]

        a1 = HexBoard.evaluateHexLine(0,0,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(1,1,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(2,2,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a4 = HexBoard.evaluateHexLine(3,2,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a5 = HexBoard.evaluateHexLine(4,2,Constants.LINE_RIGHT_DOWN_INDEX.getValue());




        if(a1==a2 && a2 == a3 && a3 ==a4 && a4 == a5){

            third_down_diagonal= a1*5;

        }else{
            third_down_diagonal= 0;
        }

        //evaluating the right down diagonal line from HexBoard[0][1]

        a1 = HexBoard.evaluateHexLine(0,1,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(1,2,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(2,3,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a4 = HexBoard.evaluateHexLine(3,3,Constants.LINE_RIGHT_DOWN_INDEX.getValue());




        if(a1==a2 && a2 == a3 && a3 ==a4){

            fourth_down_diagonal= a1*4;

        }else{
            fourth_down_diagonal= 0;
        }

        //evaluating the right down diagonal line from HexBoard[0][2]

        a1 = HexBoard.evaluateHexLine(0,2,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a2 = HexBoard.evaluateHexLine(1,3,Constants.LINE_RIGHT_DOWN_INDEX.getValue());
        a3 = HexBoard.evaluateHexLine(2,4,Constants.LINE_RIGHT_DOWN_INDEX.getValue());





        if(a1==a2 && a2 == a3 ){

            fifth_down_diagonal= a1*3;

        }else{
            fifth_down_diagonal= 0;
        }

        return RESULT = first_row_down+second_row_down+third_row_down+fourth_row_down+fifth_row_down+first_up_diagonal+second_up_diagonal+third_up_diagonal+fourth_up_diagonal+fifth_up_diagonal+first_down_diagonal+second_down_diagonal+third_down_diagonal+fourth_down_diagonal+fifth_down_diagonal;


    }
}
