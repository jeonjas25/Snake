public class SnakeGame {
    private static final Direction Right = null;

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        Snake snake = new Snake(4, 100, 300, Right);
        Apple apple = new Apple(600, 600);
        GameCanvas gameCanvas = new GameCanvas(snake, apple);
        
    }
    
}
