import processing.core.PImage;

public class HexanCard {
    private int[] lines;
    private HexanCard[] neighbours;
    public static HexanCard[] allHexans = new HexanCard[HexanBuilder.getInstance().numberOfHexans()];
    public static int HEXAN_SIDE_SIZE = 80;
    public static int DISTANCE_BETWEEN_HEX = (int) Math.sqrt((Math.pow(HEXAN_SIDE_SIZE, 2) - Math.pow(HEXAN_SIDE_SIZE/2,2)));

    private int x;
    private int y;
    private String imageString;
    private PImage image;
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public HexanCard(int x, int y, int[] lines, String imageString) {
        this.x = x;
        this.y = y;
        this.imageString = imageString;
        this.lines = lines;
        this.neighbours = new HexanCard[9];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImageString() {
        return imageString;
    }

    public int[] getLines() {
        return lines;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    public PImage getImage() {
        return this.image;
    }
}
