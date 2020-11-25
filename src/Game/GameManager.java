package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Thread;


public class GameManager extends JPanel implements KeyListener, Runnable {

    private JPanel gamePanel;
    private Thread thread;
    public boolean gameStart;
    private HumanPaddle p1;
    private HumanPaddle p2;
    private Ball ball;
    private boolean running;
    private int p1Score;
    private int p2Score;
    private boolean p1Wins;
    private boolean p2Wins;



    public GameManager() {
        init();
    }

    // Initializes all of the fields above
    public void init() {
        gamePanel = new JPanel();
        this.addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true); // <--- This is important, it allows KeyListener to work
        gameStart = false;
        running = true;
        p1Score = 0;
        p2Score = 0;
        p1Wins = false;
        p2Wins = false;
        thread = new Thread(this);
        p1 = new HumanPaddle(1);
        p2 = new HumanPaddle(2);
        ball = new Ball();
        thread.start();
    }

    // Sets background color to black using the width and height of the frame
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        p1.draw(g);
        p2.draw(g);
        ball.draw(g);

        if(!gameStart) {
            g.drawString("Press Space to Start the Game", 400, 400);
        }

        // If ball passes the edge of the frame score goes up ball resets position
        if(ball.getX() < -10) {
            p2Score++;
            ball.resetPos();
        } else if(ball.getX() > 1010) {
            p1Score++;
            ball.resetPos();
        }

        // Scoreboard
        g.setFont(new Font("", Font.PLAIN, 32));
        g.drawString(String.valueOf(p1Score), 50, 100);
        g.drawString(String.valueOf(p2Score), 900, 100);

        if(p1Score == 7) {
            gameStart = false;
            p1Wins = true;
        } else if(p2Score == 7) {
            gameStart = false;
            p2Wins = true;
        }

        if(p1Wins) {
            g.drawString("Player 1 Wins!", 400, 350);
        } else if(p2Wins) {
            g.drawString("Player 2 Wins!", 400, 350);
        }

        if(!gameStart && (p1Score == 7 || p2Score == 7)) {
            g.drawString("Press Enter to restart the game", 400, 450);
        }

    }

    // Game Loop
    @Override
    public void run() {
        while(running) {
            if(gameStart) {
                p1.move();
                p2.move();
                ball.move();
                ball.checkCollision(p1, p2);
            }

            repaint();

            // Limits the game from running too fast
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameStart = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameStart = true;
            ball.resetPos();
            p1.resetPos(1);
            p2.resetPos(2);
            p1Score = 0;
            p2Score = 0;
            p1Wins = false;
            p2Wins = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_W) {
            p1.setUpAccel(true);
        } else if(e.getKeyCode() == KeyEvent.VK_S) {
            p1.setDownAccel(true);
        } else if(e.getKeyCode() == KeyEvent.VK_UP) {
            p2.setUpAccel(true);
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            p2.setDownAccel(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) {
            p1.setUpAccel(false);
        } else if(e.getKeyCode() == KeyEvent.VK_S) {
            p1.setDownAccel(false);
        } else if(e.getKeyCode() == KeyEvent.VK_UP) {
            p2.setUpAccel(false);
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            p2.setDownAccel(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
