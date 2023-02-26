public class CicrcleSimulation extends Thread{

    int diameter;
    int delta =1;


    @Override
    public void run() {
        // code to be executed in the new thread

        if(Circle.allCircels[0].getDiameter()<=301){
            for (int i =1; i <500; i++){
                int x1 = Circle.allCircels[0].getCordX();
                int x2 = Circle.allCircels[1].getCordX();
                //System.out.println("New thread running");
                // StartStage.getInstance().setCircleDiameter(i);
                Circle.allCircels[0].setCordX(x1-i);
                Circle.allCircels[1].setCordX(x2+i);
                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }



        }else{
            for (int i =1000; i >=200; i--){
                //System.out.println("New thread running");
                // StartStage.getInstance().setCircleDiameter(i);
                Circle.allCircels[0].setDiameter(i);
                Circle.allCircels[1].setDiameter(i);
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            diameter = 200;

            while (true) {
                diameter += delta;
                if (diameter > 300) {
                    delta = -delta;  // start decreasing diameter
                } else if (diameter <= 200) {
                    delta = -delta;  // start increasing diameter
                }
                // StartStage.getInstance().setCircleDiameter(diameter);
                Circle.allCircels[0].setDiameter(diameter);
                Circle.allCircels[1].setDiameter(diameter);

                try {
                    Thread.sleep(10);  // pause for 50 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        }

        }



