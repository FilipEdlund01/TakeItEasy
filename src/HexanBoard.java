import processing.core.PImage;

public class HexanBoard {

    static HexanBoard[][] hexanBoard = new HexanBoard[HexanBuilder.getInstance().initLines.length][HexanBuilder.getInstance().initLines.length];
    int x;
    int y;
    boolean isOccupied;
    public HexanBoard(int x, int y) {
        this.x = x;
        this.y = y;
        this.isOccupied = false;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

}
