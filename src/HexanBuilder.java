import java.util.LinkedList;

// Singleton
public class HexanBuilder {

    private final static HexanBuilder hexanBuilderInstance = new HexanBuilder();

    public int[][] initLines;
    private HexanBuilder(){
        this.initLines = new int[][]{
                //po smeru hodinovych rucicek
                new int[]{9,8,2},
                new int[]{9,3,2},
                new int[]{9,3,6},
                new int[]{9,4,2},
                new int[]{9,4,6},
                new int[]{9,8,7},
                new int[]{9,3,7},
                new int[]{9,4,7},
                new int[]{9,8,6},


                new int[]{5,3,2},
                new int[]{5,8,7},
                new int[]{5,8,2},
                new int[]{5,8,6},
                new int[]{5,4,7},
                new int[]{5,3,6},
                new int[]{5,4,6},
                new int[]{5,3,7},
                new int[]{5,4,2},

                new int[]{1,8,7},
                new int[]{1,3,7},
                new int[]{1,8,6},
                new int[]{1,8,2},
                new int[]{1,3,6},
                new int[]{1,4,6},
                new int[]{1,3,2},
                new int[]{1,4,7},
                new int[]{1,4,2},




        };
    }

    public static HexanBuilder getInstance(){
        return hexanBuilderInstance;
    }

    public void buildHexans(){

        int width = 0;
        int height = 0;
        int index_line = 0;
        for (int i = 0; i < this.initLines.length; i++) {
            int[] init_lines = HexanBuilder.getInstance().initLines[index_line];

            if(height < Main.HEIGHT - HexanCard.HEXAN_SIDE_SIZE * 4){
                height += HexanCard.HEXAN_SIDE_SIZE*2;
            }else {
                width += HexanCard.HEXAN_SIDE_SIZE * 2;
            }

            HexanCard.allHexans[i] = new HexanCard(width, height, init_lines, "hex"+init_lines[0]+init_lines[1]+init_lines[2]+".png");
            index_line++;
        }
    }
    public void buildHexanBoard(){

        final int yCordStart = Main.HEIGHT/3;
        int xCords = Main.WIDTH/12;
        int yCords = yCordStart;

        int[] columnYMovement = new int[]{1, 2, 1, 0, -1}; // last is -1 to avoid index out of bounds
        int[] boardColumnLength = new int[]{3, 4, 5, 4, 3};
        HexanBoard.hexanBoard = new HexanBoard[boardColumnLength.length][];

        for (int x = 0; x < boardColumnLength.length; x++) {
            HexanBoard[] column = new HexanBoard[boardColumnLength[x]];
            for (int y = 0; y < boardColumnLength[x]; y++) {
                column[y] = new HexanBoard(xCords, yCords);
                yCords += HexanCard.DISTANCE_BETWEEN_HEX*2;
            }
            HexanBoard.hexanBoard[x] = column;

            yCords = yCordStart - HexanCard.DISTANCE_BETWEEN_HEX * columnYMovement[x];

            xCords+=HexanCard.HEXAN_SIDE_SIZE*1.5;
        }
    }

    public void printLines(){

        for (int[] initLine : this.initLines) {
            for (int n = 0; n <= 2; n++) {
                System.out.print(initLine[n]);
            }
            System.out.println("       ");

        }
    }




    public int numberOfHexans(){
        return this.initLines.length;
    }



}
