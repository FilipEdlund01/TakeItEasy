import processing.core.PImage;

public class HexCard extends Hex {
    int xCords;
    int yCords;
    private int[] lines;
    private HexCard[] neighbours;
    public static HexCard[] allHexans = new HexCard[HexBuilder.getInstance().numberOfHex()];

    private String imageString;
    private PImage image;

    public HexCard(int xCords, int yCords, int[] lines, String imageString) {
        super(xCords, yCords);
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

}
