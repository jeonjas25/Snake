/*
    Jason Jeong and Tony Liu
    1/25/24

    Advanced CS Topics Semester 1 Project
    Apple class defines the position of the apple.

*/

// import
import java.util.Random;

public class Apple {
    private int x;
    private int y;
    private int cellSize = 25;

    public Apple(int maxX, int maxY) {
       spawn(maxX, maxY);
    }

    // returns x coordinate
    public int getX() {
        return x;
    }

    // returns y coordinate
    public int getY() {
        return y;
    }

    // generating random coordinates within the specified coordinates
    public void spawn(int maxX, int maxY) {
        // using Random class to generate random numbers for coordinates
        Random random = new Random();
        this.x = random.nextInt(maxX / cellSize) * cellSize;
        this.y = random.nextInt(maxY / cellSize) * cellSize;
    }

}
