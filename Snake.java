import java.util.*;

public class Snake {
    private LinkedList<Cell> snakeBody; 
    private Direction direction;

    public Snake(int length, int x, int y, Direction direction) {
        snakeBody = new LinkedList<>();
        this.direction = direction;
        
        // creating snake body at intial x and y
        for (int i = 0; i < length; i++) {
            snakeBody.add(new Cell(x - i, y));
        }

    }

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
        
    }

    public void grow() {
        
    }
    
}
