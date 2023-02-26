public class rectangleSimulation extends Thread{
public int Circlenumber;
rectangleSimulation(int circlenumber){
    this.Circlenumber =circlenumber;
}




    @Override
    public void run() {
        // code to be executed in the new thread

        if(Circlenumber==3){
            for (int i =Constants.HEIGHT.getValue()/2; i >= -Rectangle.allRectangels[0].getHEIGHT()*2; i--){
              //  UI.getInstance().showMainStage=true;// after the simulation ends show main game stage
               // UI.getInstance().setShowMainStage(true);
                Rectangle.allRectangels[0].setCordY(i);

                try {
                    sleep(5);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }


        }else if(Circlenumber==1){
            for (int i =0; i <= Constants.HEIGHT.getValue()/2; i++) {
                Rectangle.allRectangels[0].setCordY(i);

                try {
                    sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }else if(Circlenumber==2){
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
