public class HexBoard extends Hex{

    static HexBoard[][] hexBoard;
    static int[] DEFAULT_HEX_COLOR = new int[]{169,169,169};

    int x;
    int y;
    boolean isOccupied;

    int[] color;

    // index
    final static int TOP_NEIGHBOUR = 0;
    final static int BOTTOM_NEIGHBOUR = 1;
    final static int RIGHT_TOP_NEIGHBOUR = 2;
    final static int RIGHT_BOTTOM_NEIGHBOUR = 3;
//    final static int LEFT_TOP_NEIGHBOUR = 4;
    HexBoard[] neighbours = new HexBoard[6];


    public HexBoard(int x, int y, int xCords, int yCords) {
        super(xCords, yCords);

        this.x = x;
        this.y = y;

        this.isOccupied = false;
        this.color = HexBoard.DEFAULT_HEX_COLOR;
    }

    public static HexBoard getHexanBoard(int x, int y) {
        if (y < 0 || x < 0 || x >= HexBoard.hexBoard.length || y >= HexBoard.hexBoard[x].length)
            return null;
        return HexBoard.hexBoard[x][y];
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
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

    public boolean isOccupied() {
        return isOccupied;
    }

    public int[] getColor() {
        return color;
    }

}
