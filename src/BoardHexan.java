public class BoardHexan {


  //  public static BoardHexan[] all_BoardHexans = new BoardHexan[HexanBuilder.getInstance().numberOfHexans()];
    public static int BOARD_HEXAN_SIDE_SIZE = 80;
    private float x;
    private float y;

    public int hexagon_index;

    public BoardHexan(float x, float y, int hexagon_index) {
        this.x = x;
        this.y = y;
        this.hexagon_index = hexagon_index;
    }



    public static int getBoardHexanSideSize() {
        return BOARD_HEXAN_SIDE_SIZE;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getHexagon_index() {
        return hexagon_index;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
