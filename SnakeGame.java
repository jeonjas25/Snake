import javax.swing.*;
import java.awt.*;
public class SnakeGame {
    private static final Direction Right = null;

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        JFrame gameFrame = new JFrame("Snake Game");
        JPanel gamePanel = new JPanel();

        Snake snake = new Snake(3, 100, 300, Right);
        Apple apple = new Apple(600, 600);
        GameCanvas gameCanvas = new GameCanvas(snake, apple);

        // setting up Frame and Panel
        gamePanel.setBackground(Color.BLACK);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(600, 600);
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
    }
    
}
