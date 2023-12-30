import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// GameInterface class creates the game interface with different buttons
public class GameInterface extends JFrame {
    // initializing different features of interface
    private JComboBox<GameMode> modeComboBox;
    private JComboBox<String> speedComboBox;
    private JComboBox<ColorMode> colorComboBox;
    private JButton startButton;

    public GameInterface() {
        // setting up frame
        this.setTitle("Snake Interface");
        this.setPreferredSize(new Dimension(600, 600));
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new GridLayout(4, 3));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        // initializing features
        modeComboBox = new JComboBox<>(GameMode.values());
        speedComboBox = new JComboBox<>(new String[] {"Slow", "Normal", "Fast"});
        colorComboBox = new JComboBox<>(ColorMode.values());
        startButton = new JButton("START"); 
        JLabel modeLabel = new JLabel("Game Mode:");
        JLabel speedLabel = new JLabel("Game Speed:");
        JLabel applesLabel = new JLabel("Snake Color:");
        JLabel gameLabel = new JLabel("SNAKE");

        // changing backround and text color
        setColors(modeComboBox);
        setColors(speedComboBox);
        setColors(colorComboBox);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.RED);
        modeLabel.setForeground(Color.WHITE);
        speedLabel.setForeground(Color.WHITE);
        applesLabel.setForeground(Color.WHITE);
        gameLabel.setForeground(Color.GREEN);

        // adjusting text
        Font labelFont = new Font("ArcadeClassic", Font.BOLD, 15);
        modeLabel.setFont(labelFont);
        speedLabel.setFont(labelFont);
        applesLabel.setFont(labelFont);
        startButton.setFont(new Font("ArcadeClassic", Font.BOLD, 20));
        gameLabel.setFont(new Font("ArcadeClassic", Font.BOLD, 30));
        modeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        speedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        applesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // adding features to frame
        this.add(new JLabel());
        this.add(gameLabel);
        this.add(new JLabel());
        this.add(modeLabel);
        this.add(speedLabel);
        this.add(applesLabel);
        this.add(modeComboBox);
        this.add(speedComboBox);
        this.add(colorComboBox);
        this.add(new JLabel());
        this.add(startButton);
        this.add(new JLabel());

        // anonymous class defined in method parameter to start game when button is pressed
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // setting frame to visible after everything is added
        this.setVisible(true);
    }

    // setColors() method changes backround to black and foreground to white
    private void setColors(Component component) {
        component.setBackground(Color.BLACK);
        component.setForeground(Color.WHITE);
    }

    private void startGame() {
        GameMode selectedMode = (GameMode) modeComboBox.getSelectedItem();
        ColorMode selectedColor = (ColorMode) colorComboBox.getSelectedItem();
        String selectedSpeed = (String) speedComboBox.getSelectedItem();
        int delay;

        // setting delay based on user picked speed
        if (selectedSpeed.equals("Slow")) {
            delay = 100;
        }
        else if (selectedSpeed.equals("Normal")) {
            delay = 75;
        }
        else {
            delay = 50;
        }

        // starting game
        Snake snake = new Snake(3, 100, 300, Direction.Right);
        Apple apple = new Apple(600, 600);
        GameCanvas gameCanvas = new GameCanvas(snake, apple, delay, selectedColor, selectedMode);
        gameCanvas.startGame();

    }
}
