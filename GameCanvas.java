import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    private final int cellSize = 10;
    private final int screenHeight = 600;
    private final int screenWidth = 600;
    private Snake snake;
    private Apple apple;

    public GameCanvas(Snake snake, Apple apple) {
        this.snake = snake;
        this.apple = apple;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGameBoard(g);
        drawSnakeCell(g);
        drawApple(g);
    }

    public void drawGameBoard(Graphics g) {
        g.setColor(Color.WHITE);
        
        // Draw horizontal lines
        for (int y = 0; y < getHeight(); y += cellSize) {
            g.drawLine(0, y, screenWidth, y);
        }
    
        // Draw vertical lines
        for (int x = 0; x < getWidth(); x += cellSize) {
            g.drawLine(x, 0, x, screenHeight);
        }


    }

    public void drawSnakeCell(Graphics g) {
        g.setColor(Color.GREEN);

        for (Cell cell : snake.getSnakeBody()) {
            if (cell != null) {
                g.drawRect(cell.getX(), cell.getY(), cellSize, cellSize);
            }
        }

    }

    public void drawApple(Graphics g) {
        g.setColor(Color.RED);
        apple.spawn(screenWidth, screenHeight);
        g.fillOval(apple.getX(), apple.getY(), cellSize, cellSize);
    }
    
}
