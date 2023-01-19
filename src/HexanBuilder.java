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
    public HexanBoard[][] buildHexanBoard(){
        HexanBoard[][] hexanBoard = new HexanBoard[HexanBuilder.getInstance().initLines.length][HexanBuilder.getInstance().initLines.length];


        int xCords = 0;
        int yCords = 0;


        for (int x = 0; x < HexanBuilder.getInstance().initLines.length; x++) {
            for (int y = 0; y < HexanBuilder.getInstance().initLines.length; y++) {
                hexanBoard[y][x] = new HexanBoard(xCords, yCords);
                yCords += HexanCard.DISTANCE_BETWEEN_HEX*2;

            }
        }

        return hexanBoard;
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
