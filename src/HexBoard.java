public class HexBoard extends Hex{

    static HexBoard[][] hexBoard;
    static int[] DEFAULT_HEX_COLOR = new int[]{169,169,169};
    static int[] SELECT_HEX_COLOR = new int[]{255, 0, 0};

    int x;
    int y;

    HexCard occupiedHexCard;

    int[] color;

    HexBoard[] neighbours = new HexBoard[6];






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





    public int[] getColor() {
        return color;
    }


    public static int evaluateHexLine(int x , int y, int index){
        HexBoard currentHexboard = HexBoard.getHexanBoard(x,y);
        return currentHexboard.occupiedHexCard.getLines()[index];
    }
}
