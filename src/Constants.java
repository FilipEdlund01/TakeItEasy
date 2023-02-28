public enum Constants {
    // better to store the values here because
    // we must not change them in every class(the values)
    // if we want to change them
    WIDTH(1600),
    HEIGHT(800),
    xPixelsofImage(166 / 2),
    yPixelsofImage(140 / 2),
    rectx0(WIDTH.getValue()/2-100),
    recty0(-100), // so it cant be seen at first
    rectXdistance(200),
    rectYdistance(30),
    diameter(1000), //circle diameter at beginning
    LINE_UP_INDEX (0),
    LINE_RIGHT_DOWN_INDEX (1),
    LINE_LEFT_DOWN_INDEX(2),
    userFound(0);


    private final int value;
    Constants(int value) {
        this.value = value;

    }

    public int getValue() {
        return value;
    }
}
