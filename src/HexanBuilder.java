// Singleton
public class HexanBuilder {

    private final static HexanBuilder hexan_builder_instance = new HexanBuilder();

    private int[][] init_lines;
    private HexanBuilder(){
        this.init_lines = new int[][]{
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
        return hexan_builder_instance;
    }

    public void buildHexans(){
        final int margin = 70;

        int index_line = 0;
        for (int i = 0; i < this.init_lines.length; i++) {
            Hexan.all_hexans[i] = new Hexan(200, 200, HexanBuilder.getInstance().init_lines[index_line]);

            index_line++;
        }
    }


    public int numberOfHexans(){
        return this.init_lines.length;
    }



}
