/*import g4p_controls.GCScheme;
import g4p_controls.GEvent;
import g4p_controls.GTextField;*/
import processing.core.PApplet;
import processing.event.MouseEvent;


//import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;




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





