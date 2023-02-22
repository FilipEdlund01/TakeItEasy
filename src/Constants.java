public enum Constants {
    // better to store the values here because
    // we must not change them in every class(the values)
    // if we want to change them
    WIDTH(1600),
    HEIGHT(800),
    xPixelsofImage(166 / 2),
    yPixelsofImage(140 / 2),
    textFieldx0(100),
    textFieldy0(100),
    textFieldXdistance(200),
    textFieldYdistance(30);


    private final int value;
    Constants(int value) {
        this.value = value;

    }

    public int getValue() {
        return value;
    }
}
