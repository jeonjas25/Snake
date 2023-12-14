// SnakeGame class has the main() method
public class SnakeGame {
    public static void main(String[] args) {
        Snake snake = new Snake(3, 100, 300, Direction.Right);
        Apple apple = new Apple(600, 600);
        GameCanvas gameCanvas = new GameCanvas(snake, apple, 75, 1);
        gameCanvas.startGame();
    }
    
}
