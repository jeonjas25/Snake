/*
    Jason Jeong and Tony Liu
    1/23/24

    Advanced CS Topics Semester 1 Project
    Cell class defines the position of a snake body cell.

*/

public class Cell {
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // get methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // set methods
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
