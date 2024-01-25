/*
    Jason Jeong and Tony Liu
    1/25/24

    Advanced CS Topics Semester 1 Project
    Snake class defines the characteristics and methods of the snake.

*/

// import
import java.util.*;

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

    // retruns snake's body
    public LinkedList<Cell> getSnakeBody() {
        return snakeBody;
    }

    // returns direction
    public Direction getDirection() {
        return direction;
    }

    // sets direction
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // move() method moves the snake's body
    public void move() {
        Cell head = snakeBody.get(0);

        // for loop iterates through the snake's body to update the position of the cells
        for (int i = snakeBody.size() - 1; i > 0; i--) {
            Cell currentCell = snakeBody.get(i);
            Cell previousCell = snakeBody.get(i - 1);
            currentCell.setX(previousCell.getX());
            currentCell.setY(previousCell.getY());
        }

        // using switch statement to update the coordinates of the snake's head based on its direction
        switch (direction) {
            case Up:
                head.setY(head.getY() - cellSize);
                break;
            case Down:
                head.setY(head.getY() + cellSize);
                break;
            case Left:
                head.setX(head.getX() - cellSize);
                break;
            case Right:
                head.setX(head.getX() + cellSize);
                break;
        }

    }

    // grow() method adds a cell to the snake's body
    public void grow() {
        Cell lastCell = snakeBody.getLast();

        // using switch statement to determine where to add the new cell based on the snake's current direction
        switch (direction) {
            case Up:
                snakeBody.add(new Cell(lastCell.getX(), lastCell.getY() - cellSize));
                break;
            case Down:
                snakeBody.add(new Cell(lastCell.getX(), lastCell.getY() + cellSize));
                break;
            case Left:
                snakeBody.add(new Cell(lastCell.getX() + cellSize, lastCell.getY()));
                break;
            case Right:
                snakeBody.add(new Cell(lastCell.getX() - cellSize, lastCell.getY()));
                break;
        }
        
    }
    
}
