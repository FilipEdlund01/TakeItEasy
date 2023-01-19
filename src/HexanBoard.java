import processing.core.PImage;

import java.util.List;

public class HexanBoard {

    static HexanBoard[][] hexanBoard;
    int x;
    int y;
    boolean isOccupied;
    public HexanBoard(int x, int y) {
        this.x = x;
        this.y = y;
        this.isOccupied = false;
    }

    public void setX(int x) {

    }

    public void setY(int y) {

    }

    public int getX() {
        return this.x;
    }


    public int getY() {
        return this.y;
    }

}
