public class rectangleSimulation extends Thread{

    @Override
    public void run() {
        // code to be executed in the new thread

        for (int i =0; i < Constants.HEIGHT.getValue()/2; i++) {
           Rectangle.allRectangels[0].setCordY(i);

            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
