package TwentyFortyEight;

public class Cell {

    private int x;
    private int y;
    private int value;
    public int[] colors;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        value = 0;
    }

    public int getValue()  {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int[] getColors() {
        if (value == 2) {
            colors = new int[]{236,228,219};
        }
        else if (value == 4) {
            colors = new int[]{232,217,186};
        }
        else if (value == 8) {
            colors = new int[]{221, 171, 122};
        }
        else if (value == 16) {
            colors = new int[]{231, 149, 103};
        }
        else if (value == 32) {
            colors = new int[]{229, 125, 96};
        }
        else if (value == 64) {
            colors = new int[]{228, 109, 78};
        }
        else if (value == 128) {
            colors = new int[]{236, 208, 105};
        }
        else if (value == 256) {
            colors = new int[]{229, 125, 96};
        }
        else if (value == 512) {
            colors = new int[]{230, 201, 101};
        }
        else if (value == 1024) {
            colors = new int[]{232, 198, 91};
        }
        else if (value == 2048) {
            colors = new int[]{231, 196, 79};
        }
        else {
            colors = new int[]{0,0,0};
        }
        return colors;
    }

}