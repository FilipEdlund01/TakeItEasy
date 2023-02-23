public class Rectangle {
    private int CordX;
    private int CordY;

    private int WIDHT;
    private int HEIGHT;



    public int getCordX() {
        return CordX;
    }

    public int getCordY() {
        return CordY;
    }


    public int getWIDHT() {
        return WIDHT;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setCordX(int cordX) {
        CordX = cordX;
    }

    public void setCordY(int cordY) {
        CordY = cordY;
    }

    public void setWIDHT(int WIDHT) {
        this.WIDHT = WIDHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }
    public static Rectangle[] allRectangels = new Rectangle[2];

    public Rectangle(int xCords, int yCords, int widht,int height) {


        this.CordX = xCords;
        this.CordY = yCords;
        this.WIDHT = widht;
        this.HEIGHT =height;


    }
    public void BiuldRectangle(){
        allRectangels[0].setCordX(0);

    }
}
