package TwentyFortyEight;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import java.util.Random;

public class App extends PApplet {

    public static final int GRID_SIZE = 4; // 4x4 grid
    public static final int CELL_SIZE = 100; // Cell size in pixels
    public static final int CELL_BUFFER = 8; // Space between cells
    public static final int WIDTH = GRID_SIZE * CELL_SIZE;
    public static final int HEIGHT = GRID_SIZE * CELL_SIZE;
    public static final int TIMER_HEIGHT = 100;
    public static final int FPS = 30;
    public static Random random = new Random();

    private Cell[][] board;

    private Timer timer = new Timer();
    // Feel free to add any additional methods or attributes you want. Please put
    // classes in different files.

    public App() {
        this.board = new Cell[4][4];
        timer.start();
    }

    /**
     * Initialise the setting of the window size.
     */
    @Override
    public void settings() {
        size(WIDTH, HEIGHT+TIMER_HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player
     * and map elements.
     */
    @Override
    public void setup() {
        frameRate(FPS);
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = new Cell(y, x);
            }
        }
    }

    /** (3, 1)         // mouseX: 100 mouseY: 300
     * [. . . .]
     * [. . . .]
     * [. . . .]
     * [. . . .]
     */

    /**
     * Receive key pressed signal from the keyboard.
     */
    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKey() == 'r') {
            timer.reset();
            timer.start();
            System.out.println("Timer reset");
        }
        else if (event.getKeyCode() == UP) {
            move(0);
        }
        else if (event.getKeyCode() == DOWN) {
            move(1);
        }
        else if (event.getKeyCode() == LEFT) {
            move(2);
        }
        else if (event.getKeyCode() == RIGHT) {
            move(3);
        }
    }

    /**
     * Receive key released signal from the keyboard.
     */
    @Override
    public void keyReleased() {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == PConstants.LEFT) {
            Cell pressedCell = getCellFromMousePosition(e.getX(), e.getY());
            int value = (random.nextInt(2) + 1) * 2;
            pressedCell.setValue(value);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public void move(int direction) {
        // UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3
        int[] directions = new int[2];
        if (direction == 0) {
            directions = new int[]{-1,0};
            System.out.println("UP");
            for (int y = 1; y < GRID_SIZE; y++) {
                for (int x = 0; x < GRID_SIZE; x++) {
                    moveCell(x, y, directions);
                }
            }
        }
        else if (direction == 1) {
            directions = new int[]{1,0};
            System.out.println("DOWN");
            for (int y = GRID_SIZE - 2; y >= 0; y--) {
                for (int x = 0; x < GRID_SIZE; x++) {
                    moveCell(x, y, directions);
                }
            }
        }
        else if (direction == 2) {
            directions = new int[]{0,-1};
            System.out.println("LEFT");
            for (int y = 0; y < GRID_SIZE; y++) {
                for (int x = 1; x < GRID_SIZE; x++) {
                    moveCell(x, y, directions);
                }
            }
        }
        else if (direction == 3) {
            directions = new int[]{0,1};
            System.out.println("RIGHT");
            for (int y = 0; y < GRID_SIZE; y++) {
                for (int x = GRID_SIZE - 2; x >= 0; x--) {
                    moveCell(x, y, directions);
                }
            }
        }
    }

    public void moveCell(int x, int y, int[] directions) {
        if (board[y][x].getValue() != 0) {
            int newY = y + directions[0];
            int newX = x + directions[1];

            if (newX >= 0 && newX < GRID_SIZE && newY >=0 && newY < GRID_SIZE) {
                board[newY][newX].setValue(board[y][x].getValue());
                board[y][x].clearCell();
            }
        }
    }

    public Cell getCellFromMousePosition(int x, int y) {
        return board[y/CELL_SIZE][x/CELL_SIZE];
    }

    public boolean isHoveringCell(int x, int y) {
        if (mouseX > x*CELL_SIZE && mouseX < (x+1)*CELL_SIZE 
                && mouseY > y*CELL_SIZE && mouseY < (y+1)*CELL_SIZE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Draw all elements in the game by current frame.
     */
    @Override
    public void draw() {
        background(255); 
        textSize(40); // SIZE OF TEXT
        strokeWeight(15);  // WIDTH OF BORDER OF SHAPES
        stroke(156, 139, 124); // COLOUR OF BORDER OF SHAPES
        
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (isHoveringCell(x, y)) {
                    fill(232, 207, 184); // SETTING THE COLOUR, NOT DRAWING IT
                } else {
                    fill(189, 172, 151);
                }
                
                rect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE); // DRAWING HAPPENS HERE
                Cell cell = board[y][x];

                if (cell.getValue() > 0) {
                    int[] colors = cell.getColors();
                    fill(colors[0], colors[1], colors[2]); // COLOR OF CELL
                    rect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    fill(0, 0, 0); // TEXT COLOUR
                    text(String.valueOf(cell.getValue()), (x+0.4f)*CELL_SIZE, (y+0.6f)*CELL_SIZE);
                }
            }
        }

        fill(0, 0, 0); // TEXT COLOUR 
        text("Timer: ", 10, GRID_SIZE*CELL_SIZE + 65);
        text(String.format("%02d:%02d", timer.getMinutes(), timer.getSeconds()), 150, GRID_SIZE*CELL_SIZE + 65);
    }

    public static void main(String[] args) {
        PApplet.main("TwentyFortyEight.App");
    }

}
