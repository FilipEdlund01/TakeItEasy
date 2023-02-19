public class Circle {

    private int CordX;
    private int CordY;

    private int Diameter;

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

    public static Circle[] allCircels = new Circle[2];
    public Circle(int xCords, int yCords, int diameter) {


        this.CordX = xCords;
        this.CordY = yCords;
        this.Diameter=diameter;


    }
}
