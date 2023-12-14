import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends JPanel implements ActionListener {
    // instance variables
    private final int screenHeight = 625;
    private final int screenWidth = 625;
    private int cellSize = 25;
    private int delay;
    private int numApples;
    private int score = 0;
    private Snake snake;
    private Apple apple;
    private boolean isRunning = false;
    private GameMode gameMode;
    Timer timer;

    public GameCanvas(Snake snake, Apple apple, int delay, int numApples, GameMode gameMode) {
        this.snake = snake;
        this.apple = apple;
        this.delay = delay;
        this.numApples = numApples;
        this.gameMode = gameMode;

        // setting up frame and panel
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.addKeyListener(new MyKeyAdapter());
        this.setFocusable(true);
        JFrame gameFrame = new JFrame("Snake Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(this);
        gameFrame.setVisible(true);
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
    }

    // startGame() starts the game and the timer
    public void startGame() {
        isRunning = true;
        timer = new Timer(delay, this);
        timer.start();
    }

    // restartGame() resets the game and starts again
    public void restartGame() {
        score = 0;
        snake = new Snake(3, 100, 300, Direction.Right);
        apple = new Apple(600, 600);
        startGame();
    }

    // calling all the methods in the overidden paintComponent() method
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isRunning) {
            drawGridlines(g);
            drawSnakeCell(g);
            drawApple(g);
            displayScore(g);
        }
        else {
            displayScore(g);
            endGame(g);
        }
    }

    // drawGridlines() will draw the gridlines
    public void drawGridlines(Graphics g) {
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

    // displayScore() writes the current score  
    public void displayScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("ArcadeClassic", Font.BOLD, 30));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString("Score: " + score, (screenWidth - metrics.stringWidth("Score: " + score)) / 2, g.getFont().getSize());
    }

    // checkApple() checks if the apple is eaten
    public void checkApple() {
        Cell head = snake.getSnakeBody().get(0);
        if ((head.getX() == apple.getX()) && (head.getY() == apple.getY())) {
            snake.grow();
            score++;
            apple.spawn(screenWidth, screenHeight);
            // checks if new apple will be on the snake's body
            for (int i = snake.getSnakeBody().size() - 1; i > 0; i--) {
                Cell currentCell = snake.getSnakeBody().get(i);
                if ((currentCell.getX() == apple.getX()) && (currentCell.getY() == apple.getY())) {
                    apple.spawn(screenWidth, screenHeight);
                }
            }
        }
    }
    
    // checkCollisions() checks if the snake's head collides either with its body or with the game border and ends the game if a collision occurs
    public void checkCollisions() {
        Cell head = snake.getSnakeBody().get(0);

        // checks if snake's head collides with body
        for (int i = snake.getSnakeBody().size() - 1; i > 0; i--) {
            Cell currentCell = snake.getSnakeBody().get(i);
            if ((head.getX() == currentCell.getX()) && (head.getY() == currentCell.getY())) {
                isRunning = false;
                timer.stop();
            }
        }

        // checks if snake's head collides with any of the game's borders
        if (head.getX() < 0) {
            isRunning = false;
            timer.stop();
        }
        if (head.getX() > screenWidth) {
            isRunning = false;
            timer.stop();
        }
        if (head.getY() < 0) {
            isRunning = false;
            timer.stop();
        }
        if (head.getY() > screenHeight) {
            isRunning = false;
            timer.stop();
        }
    
    }

    // endGame() method ends the game and displays end message
    public void endGame(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("ArcadeClassic", Font.BOLD, 70));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString("Game Over", (screenWidth - metrics.stringWidth("Game Over")) / 2, screenHeight / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            checkCollisions();
            checkApple();
            snake.move();
        }
        repaint();

    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // using switch statement to change snake's direction based on which button is pressed (right, left, up, down)
            // makes sure the direction only changes if snake is not making a 180 turn
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.Right) {
                        snake.setDirection(Direction.Left);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.Left) {
                        snake.setDirection(Direction.Right);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.Down) {
                        snake.setDirection(Direction.Up);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.Up) {
                        snake.setDirection(Direction.Down);
                    }
                    break;
                // restarts game if enter is pressed
                case KeyEvent.VK_ENTER:
                    if (!isRunning) {
                        restartGame();
                    }
                    break;
            }

        }

    }
    
}
