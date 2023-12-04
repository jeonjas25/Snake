import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends JPanel implements ActionListener {
    // instance variables
    private final int cellSize = 25;
    private final int screenHeight = 625;
    private final int screenWidth = 625;
    private Snake snake;
    private Apple apple;

    public GameCanvas(Snake snake, Apple apple) {
        this.snake = snake;
        this.apple = apple;

        // setting up frame and panel
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.addKeyListener(new MyKeyAdapter());
        JFrame gameFrame = new JFrame("Snake Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(this);
        gameFrame.setVisible(true);
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
    }

    // calling all the methods in the overidden paintComponent() method
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGameBoard(g);
        drawSnakeCell(g);
        drawApple(g);
    }

    // drawGameBoard() will draw the gridlines
    public void drawGameBoard(Graphics g) {
        g.setColor(Color.DARK_GRAY);

        // draw horizontal and vertical grid lines
        for (int i = 0; i < screenHeight / cellSize; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, screenHeight);
            g.drawLine(0, i * cellSize, screenWidth, i * cellSize);
        }
        
    }

    // drawSnakeCell() will draw the snake body
    public void drawSnakeCell(Graphics g) {
        g.setColor(Color.GREEN);
        for (Cell cell : snake.getSnakeBody()) {
            if (cell != null) {
                g.fillRect(cell.getX(), cell.getY(), cellSize, cellSize);
            }
        }

    }

    // drawApple() will draw the apple
    public void drawApple(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(apple.getX(), apple.getY(), cellSize, cellSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            
        }
    }
    
}
