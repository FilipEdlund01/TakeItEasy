public class Hexan {
    private int[] lines;
    private Hexan[] neighbours;
    public static Hexan[] all_hexans = new Hexan[HexanBuilder.getInstance().numberOfHexans()];
    public static int HEXAN_SIDE_SIZE = 80;
    private int x;
    private int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Hexan(int x, int y, int[] lines) {
        this.x = x;
        this.y = y;

        this.lines = lines;
        this.neighbours = new Hexan[9];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getLines() {
        return lines;
    }
}
