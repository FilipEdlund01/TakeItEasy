public class Circle {

    private int CordX;
    private int CordY;

    private int Diameter;
    int[] DEFAULT_CIRCLE_COLOR = new int[]{33,78,223};
    int[] CIRCLE_COLOR_2 = new int[]{0,255,255};

    public int[] getCIRCLE_COLOR_2() {
        return CIRCLE_COLOR_2;
    }

    public static Circle[] getAllCircels() {
        return allCircels;
    }

    public int[] getDefaultCircleColor() {
        return DEFAULT_CIRCLE_COLOR;
    }

    public int getCordX() {
        return CordX;
    }

    public int getCordY() {
        return CordY;
    }

    public int getDiameter() {
        return Diameter;
    }

    public void setDiameter(int diameter) {
        Diameter = diameter;
    }

    public void setCordX(int cordX) {
        CordX = cordX;
    }

    public static Circle[] allCircels = new Circle[2];
    public Circle(int xCords, int yCords, int diameter) {


        this.CordX = xCords;
        this.CordY = yCords;
        this.Diameter=diameter;


    }
}
