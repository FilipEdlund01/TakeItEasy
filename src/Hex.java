public abstract class Hex {
    public static int HEX_SIDE_SIZE = 80;
    public static int DISTANCE_BETWEEN_HEX = (int) Math.sqrt((Math.pow(HEX_SIDE_SIZE, 2) - Math.pow(HEX_SIDE_SIZE / 2, 2)));

    public int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getXCords() {
        return xCords;
    }

    public int getYCords() {
        return yCords;
    }

    int xCords;
    int yCords;

    public Hex(int xCords, int yCords) {
        this.xCords = xCords;
        this.yCords = yCords;
    }

    public void setXCords(int xCords) {
        this.xCords = xCords;
    }

    public void setYCords(int yCords) {
        this.yCords = yCords;
    }
}
