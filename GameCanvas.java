/*
    Jason Jeong and Tony Liu
    1/23/24

    Advanced CS Topics Semester 1 Project
    GameCanvas class creates the graphics of the game and implements the game logic.

*/

// import
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameCanvas extends JPanel implements ActionListener {
    // instance variables
    private final int screenHeight = 625;
    private final int screenWidth = 625;
    private int cellSize = 25;
    private int delay;
    private ColorMode colorMode;
    private int score = 0;
    private Snake snake;
    private Apple apple;
    private boolean isRunning = false;
    private GameMode gameMode;
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private Color randomColor;
    private Random random;
    private Timer timer;
    private JButton settingsButton;

    public GameCanvas(Snake snake, Apple apple, int delay, ColorMode colorMode, GameMode gameMode) {
        this.snake = snake;
        this.apple = apple;
        this.delay = delay;
        this.colorMode = colorMode;
        this.gameMode = gameMode;
        this.random = new Random();

        // if selected color is random, a random color is chosen for the snake
        if (colorMode == ColorMode.Random) {
            randomColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        }

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
        obstacles.clear();
        this.remove(settingsButton);
        // reselecting new random color
        if (colorMode == ColorMode.Random) {
            randomColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        }
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
            drawObstacles(g);
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
        // if selected color is normal, the snake will be green
        if (colorMode == ColorMode.Normal) {
            g.setColor(Color.GREEN);
            for (Cell cell : snake.getSnakeBody()) {
                if (cell != null) {
                    g.fillRect(cell.getX(), cell.getY(), cellSize, cellSize);
                }
            }
        }
        // if selected color is random, the snake will be the earlier determined random color
        else if (colorMode == ColorMode.Random) {
            g.setColor(randomColor);
            for (Cell cell : snake.getSnakeBody()) {
                if (cell != null) {
                    g.fillRect(cell.getX(), cell.getY(), cellSize, cellSize);
                }
            }
        }
        // if selected color is rainbow, each snake cell will keep changing colors
        else {
            for (Cell cell : snake.getSnakeBody()) {
                if (cell != null) {
                    g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    g.fillRect(cell.getX(), cell.getY(), cellSize, cellSize);
                }
            }
        }

    }

    // drawApple() will draw the apple
    public void drawApple(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(apple.getX(), apple.getY(), cellSize, cellSize);
    }

    // drawObstacle() will draw all obstacles added to the obstacle list
    public void drawObstacles(Graphics g) {
        g.setColor(new Color(102, 51, 0));
        // iterating through list and drawing each obstacle
        for (Obstacle obstacle : obstacles) {
            g.fillRect(obstacle.getX(), obstacle.getY(), cellSize, cellSize);
        }
    }

    // spawnObstacle() will add a new obstacle to the obstacle list with random coordinates
    public void spawnObstacle() {
        int obstacleX;
        int obstacleY;
        boolean spawnOnSnake;
        boolean spawnOnApple;

        // infinite while loop keeps generating new coordinates until the coordinates do not overlap with the snake and apple
        while (true) {
            spawnOnSnake = false;
            spawnOnApple = false;
            obstacleX = random.nextInt(screenHeight / cellSize) * cellSize;
            obstacleY = random.nextInt(screenHeight / cellSize) * cellSize;
            // checks if new obstacle will be on the snake's body
            for (Cell cell : snake.getSnakeBody()) {
                if ((cell.getX() == obstacleX) && (cell.getY() == obstacleY)) {
                    spawnOnSnake = true;
                    break;
                }
            }
            // checks if new obstacle will be on the apple
            if ((apple.getX() == obstacleX) && (apple.getY() == obstacleY)) {
                spawnOnApple = true;
            }
            // exits the loop if new coordinates are not on snake and apple
            if (!spawnOnSnake && !spawnOnApple) {
                break;
            }
        }

        obstacles.add(new Obstacle(obstacleX, obstacleY));
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
            // if the current gamemode is obstacle and apples eaten is odd, calls spawnObstacle()
            if ((gameMode == GameMode.Obstacle) && (score % 2 != 0)) {
                spawnObstacle();
            }
            boolean spawnOnSnake;
            // infinite while loop to ensure apple does not spawn on snake (same logic as obstacles)
            while (true) {
                apple.spawn(screenWidth, screenHeight);
                spawnOnSnake = false;
                for (Cell cell : snake.getSnakeBody()) {
                    if ((cell.getX() == apple.getX()) && (cell.getY() == apple.getY())) {
                        spawnOnSnake = true;
                        break;
                    }
                }
                if (!spawnOnSnake) {
                    break;
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

        // checks if snake's head collides with any obstacles
        for (Obstacle obstacle : obstacles) {
            if ((head.getX() == obstacle.getX()) && (head.getY() == obstacle.getY())) {
                isRunning = false;
                timer.stop();
            }
        }

    }

    // endGame() method ends the game and displays end message with a settings button
    public void endGame(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("ArcadeClassic", Font.BOLD, 70));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString("Game Over", (screenWidth - metrics.stringWidth("Game Over")) / 2, screenHeight / 2);
        
        // initializing new settings button
        settingsButton = new JButton("Settings");
        settingsButton.setBackground(Color.GRAY);
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setFont(new Font("ArcadeClassic", Font.BOLD, 20));

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            // closing GameCanvas
            SwingUtilities.getWindowAncestor(GameCanvas.this).dispose();
            // creating new GameInterface
            GameInterface gameInterface = new GameInterface();
            }
        });

        // manually set the button's position
        int buttonX = (screenWidth - settingsButton.getPreferredSize().width) / 2;
        int buttonY = screenHeight / 2 + 50;  // Adjust the Y-coordinate as needed

        // set the bounds for the button
        settingsButton.setBounds(buttonX, buttonY, settingsButton.getPreferredSize().width, settingsButton.getPreferredSize().height);

        // add the button to the GameCanvas
        this.add(settingsButton);
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
