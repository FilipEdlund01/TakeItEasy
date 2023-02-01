// Singleton
public class HexBuilder {
    // singleton
    private final static HexBuilder hexBuilderInstance = new HexBuilder();

    public int[][] initLines;
    private HexBuilder(){
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

    public static HexBuilder getInstance(){
        return hexBuilderInstance;
    }

    public void buildHexans(){

        int width = 0;
        int height = 0;
        int index_line = 0;
        for (int i = 0; i < this.initLines.length; i++) {
            int[] init_lines = HexBuilder.getInstance().initLines[index_line];

            if(height < Main.HEIGHT - HexCard.HEX_SIDE_SIZE * 4){
                height += HexCard.HEX_SIDE_SIZE *2;
            }else {
                width += HexCard.HEX_SIDE_SIZE * 2;
            }

            HexCard.allHexans[i] = new HexCard(width, height, init_lines, "images/hex"+init_lines[0]+init_lines[1]+init_lines[2]+".png");
            index_line++;
        }
    }

    private void setupNeighbours(){
        for (HexBoard[] hexanBoardColumn: HexBoard.hexBoard) {
            for (HexBoard hexanBoard: hexanBoardColumn) {
                for (int i = 0; i < hexanBoard.neighbours.length; i++) {

                }
            }
        }
    }
    private void addNeighborsToHexBoard() {
        for (int x = 0; x < HexBoard.hexBoard.length; x++) {
            for (int y = 0; y < HexBoard.hexBoard[x].length; y++) {
                HexBoard hexBoard = HexBoard.getHexanBoard(x, y);

                HexBoard.hexBoard[x][y].neighbours[0] = HexBoard.getHexanBoard(x, y - 1); // top
                HexBoard.hexBoard[x][y].neighbours[1] = HexBoard.getHexanBoard(x, y + 1); // bottom


                if(x < 2) {
                    HexBoard.hexBoard[x][y].neighbours[2] = HexBoard.getHexanBoard(x + 1, y); // right top
                    HexBoard.hexBoard[x][y].neighbours[3] = HexBoard.getHexanBoard(x + 1, y + 1); // right bottom
                    HexBoard.hexBoard[x][y].neighbours[4] = HexBoard.getHexanBoard(x - 1, y - 1); // left top
                    HexBoard.hexBoard[x][y].neighbours[5] = HexBoard.getHexanBoard(x - 1, y); // left bottom

                }else if(x == 2){
                    HexBoard.hexBoard[x][y].neighbours[2] = HexBoard.getHexanBoard(x + 1, y - 1);
                    HexBoard.hexBoard[x][y].neighbours[3] = HexBoard.getHexanBoard(x + 1, y);
                    HexBoard.hexBoard[x][y].neighbours[4] = HexBoard.getHexanBoard(x - 1, y - 1);
                    HexBoard.hexBoard[x][y].neighbours[5] = HexBoard.getHexanBoard(x - 1, y);


                }else{
                    HexBoard.hexBoard[x][y].neighbours[2] = HexBoard.getHexanBoard(x + 1, y - 1);
                    HexBoard.hexBoard[x][y].neighbours[3] = HexBoard.getHexanBoard(x + 1, y);
                    HexBoard.hexBoard[x][y].neighbours[4] = HexBoard.getHexanBoard(x - 1, y);
                    HexBoard.hexBoard[x][y].neighbours[5] = HexBoard.getHexanBoard(x - 1, y + 1);
                }
            }

        }
    }

    public void buildHexBoard(){

        final int yCordStart = Main.HEIGHT/3;
        int xCords = Main.WIDTH/2;
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

        this.addNeighborsToHexBoard();
    }



    public void printLines(){
        for (int[] initLine : this.initLines) {
            for (int n = 0; n <= 2; n++) {
                System.out.print(initLine[n]);
            }
            System.out.println("       ");

        }
    }




    public int numberOfHex(){
        return this.initLines.length;
    }



}
