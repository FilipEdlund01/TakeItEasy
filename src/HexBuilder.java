// Singleton
public class HexBuilder {
    // singleton
    private final static HexBuilder hexBuilderInstance = new HexBuilder();
    public final static int LINE_UP_INDEX = 0;
    public final static int LINE_RIGHT_DOWN_INDEX = 1;
    public final static int LINE_LEFT_DOWN_INDEX = 2;

    public int[][] initLines;
    private HexBuilder(){
        this.initLines = new int[][]{
                //po smeru hodinovych rucicek

                // 0 index nahore; 1 index v pravo dole; 2
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

    public static HexBuilder getInstance(){
        return hexBuilderInstance;
    }

    public void buildHexans(){

        int width = 0;
        int height = 0;
        int index_line = 0;
        for (int i = 0; i < this.initLines.length; i++) {
            int[] init_lines = HexBuilder.getInstance().initLines[index_line];

            if(height < Constants.HEIGHT.getValue() - HexCard.HEX_SIDE_SIZE * 4){
                height += HexCard.HEX_SIDE_SIZE *2;
            }else {
                width += HexCard.HEX_SIDE_SIZE * 2;
            }

            HexCard.allHexans[i] = new HexCard(0, 50, init_lines, "images/hex"+init_lines[0]+init_lines[1]+init_lines[2]+".png");
            index_line++;
        }
    }



    public void buildHexBoard(){

        final int yCordStart = Constants.HEIGHT.getValue()/3;
        int xCords = Constants.WIDTH.getValue()/2;
        int yCords = yCordStart;

        int[] columnYMovement = new int[]{1, 2, 1, 0, -1}; // last is -1 to avoid index out of bounds
        int[] boardColumnLength = new int[]{3, 4, 5, 4, 3};
        HexBoard.hexBoard = new HexBoard[boardColumnLength.length][];

        for (int x = 0; x < boardColumnLength.length; x++) {
            HexBoard[] column = new HexBoard[boardColumnLength[x]]; //

            for (int y = 0; y < boardColumnLength[x]; y++) {
                column[y] = new HexBoard(x, y, xCords, yCords);
               yCords += HexCard.DISTANCE_BETWEEN_HEX*2;

                //yCords += (HexCard.HEX_SIDE_SIZE-10)*2;
            }
            HexBoard.hexBoard[x] = column;

            yCords = yCordStart - HexCard.DISTANCE_BETWEEN_HEX * columnYMovement[x];

            xCords += HexCard.HEX_SIDE_SIZE * 1.5;
        }


    }








    public int numberOfHex(){
        return this.initLines.length;
    }



}
