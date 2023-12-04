import java.util.*;

// Snake class will define the characteristics and methods of the snake
public class Snake {
    private LinkedList<Cell> snakeBody; 
    private Direction direction;
    private int cellSize = 25;

    public Snake(int length, int x, int y, Direction direction) {
        snakeBody = new LinkedList<>();
        this.direction = direction;
        
        // creating snake body at intial x and y using loop
        for (int i = 0; i < length; i++) {
            snakeBody.add(new Cell(x - (i * cellSize), y));
        }

    }

    // get and set methods
    public LinkedList<Cell> getSnakeBody() {
        return snakeBody;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        Cell head = snakeBody.get(0);
        int headX = head.getX();
        int headY = head.getY();

        // using switch statement to update the coordinates of the snake's head based on its direction
        switch (direction) {
            case Up:
                headY -= cellSize;
                break;
            case Down:
                headY += cellSize;
                break;
            case Left:
                headX -= cellSize;
            case Right:
                headX += cellSize;
                break;
        }

        
    }

    public void grow() {

    }
    
}
