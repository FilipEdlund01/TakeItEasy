public class rectangleSimulation extends Thread{



    @Override
    public void run() {
        // code to be executed in the new thread

        if(Rectangle.allRectangels[0].getCordY()>=Constants.HEIGHT.getValue()/2){
            for (int i =Constants.HEIGHT.getValue()/2; i >= -Rectangle.allRectangels[0].getHEIGHT()*2; i--){
                Rectangle.allRectangels[0].setCordY(i);

                try {
                    sleep(5);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }else{
            for (int i =0; i <= Constants.HEIGHT.getValue()/2; i++) {
                Rectangle.allRectangels[0].setCordY(i);

                try {
                    sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }


    }




}
