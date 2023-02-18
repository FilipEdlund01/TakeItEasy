import processing.core.PImage;

import java.util.Arrays;
import java.util.Random;

public class HexCard extends Hex {
    static int randomInt;

    private int[] lines;
    private HexCard[] neighbours;
    public static HexCard[] allHexans = new HexCard[HexBuilder.getInstance().numberOfHex()];

    private String imageString;
    private PImage image;

    private int startingCordX;
    private int startingCordY;
    public static int PUT_INBOARD_PIXEL_BIAS_X = -2;
    public static int PUT_INBOARD_PIXEL_BIAS_Y = 8;



    public final static int LINE_UP_INDEX = 0;
    public final static int LINE_RIGHT_DOWN_INDEX = 1;
    public final static int LINE_LEFT_DOWN_INDEX = 2;


    public HexCard(int xCords, int yCords, int[] lines, String imageString) {
        super(xCords, yCords);

        this.startingCordX = xCords;
        this.startingCordY = yCords;


        this.imageString = imageString;
        this.lines = lines;
        this.neighbours = new HexCard[9];


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

    public void resetToStartingCords(){
        super.xCords = this.startingCordX;
        super.yCords = this.startingCordY;
    }

}
