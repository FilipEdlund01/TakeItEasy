
import processing.core.PApplet;







public class Main  {




    public static void main(String[] args) {
        StartStage.getInstance().buildCircles();
        StartStage.getInstance().buildRects();


        CicrcleSimulation simulation = new CicrcleSimulation();
        simulation.start();

        HexBuilder.getInstance().buildHexans();
        HexBuilder.getInstance().buildHexBoard();



        PApplet.main("UI");

    }







}





