package Game;

import javax.swing.*;


public class Display {

    private JFrame frame;
    private int width;
    private int height;
    private String title;
    private GameManager gamePanel;


    public Display(String title, int width, int height) {
        this.title = title;
        this.height = height;
        this.width = width;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        gamePanel = new GameManager();
        frame.add(gamePanel);
        frame.setVisible(true);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
