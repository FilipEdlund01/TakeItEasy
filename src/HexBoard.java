public class HexBoard extends Hex{

    static HexBoard[][] hexBoard;
    static int[] DEFAULT_HEX_COLOR = new int[]{169,169,169};
    static int[] SELECT_HEX_COLOR = new int[]{255, 0, 0};

    int x;
    int y;

    HexCard occupiedHexCard;

    int[] color;

    HexBoard[] neighbours = new HexBoard[6];

    final static int TOP_NEIGHBOUR = 0;
    final static int BOTTOM_NEIGHBOUR = 1;
    final static int RIGHT_TOP_NEIGHBOUR = 2;
    final static int RIGHT_BOTTOM_NEIGHBOUR = 3;
    final static int LEFT_TOP_NEIGHBOUR = 4;




    public HexBoard(int x, int y, int xCords, int yCords) {
        super(xCords, yCords);

        this.x = x;
        this.y = y;

        this.occupiedHexCard = null;

        this.color = HexBoard.DEFAULT_HEX_COLOR;
    }

    public static HexBoard getHexanBoard(int x, int y) {
        if (y < 0 || x < 0 || x >= HexBoard.hexBoard.length || y >= HexBoard.hexBoard[x].length)
            return null;
        return HexBoard.hexBoard[x][y];
    }


    public void setColor(int[] color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int[] getColor() {
        return color;
    }

    public static int evaluateLine(int x, int y, int index){
        HexBoard currentHexBoard = HexBoard.getHexanBoard(x, y);
        if(currentHexBoard == null) return -1;

        int score = 0;

        boolean isValid = true;
        while(currentHexBoard != null){
            if(currentHexBoard.occupiedHexCard == null ) return -1;
            if(currentHexBoard.occupiedHexCard.getLines()[HexBuilder.LINE_UP_INDEX] != currentHexBoard.neighbours[HexBoard.TOP_NEIGHBOUR].occupiedHexCard.getLines()[HexBuilder.LINE_UP_INDEX]){
                isValid = false;
                break;
            }
           currentHexBoard = currentHexBoard.neighbours[HexBoard.BOTTOM_NEIGHBOUR];
       };
       if(isValid){
           score++;
       }
       return score;


    }
    public static int evaluateHexLine(int x , int y, int index){
        HexBoard currentHexboard = HexBoard.getHexanBoard(x,y);
        return currentHexboard.occupiedHexCard.getLines()[index];
    }
}
