/*
    Jason Jeong and Tony Liu
    1/25/24

    Advanced CS Topics Semester 1 Project
    Obstacle class defines the position of an obstacle.

*/

public class Obstacle {
    private int x;
    private int y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

     // returns x coordinate
     public int getX() {
        return x;
    }

    // returns y coordinate
    public int getY() {
        return y;
    }

    // sets x coordinate
    public void setX(int x) {
        this.x = x;
    }

    // sets y coordinate
    public void setY(int y) {
        this.y = y;
    }
    
}
