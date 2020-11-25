package Game;

import java.awt.*;

public class Ball {

    private double x, y , xVel, yVel;


    public Ball() {
        y = 360;
        x = 480;
        xVel = getRandomDirection() * getRandomSpeed();
        yVel = getRandomDirection() * getRandomSpeed();
    }

    public double getRandomSpeed() {
        return (Math.random() * 3 + 2);
    }

    public int getRandomDirection() {
        int rand = (int)(Math.random() - 2);
        if(rand == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval((int)x - 10, (int)y - 10, 20, 20);
    }

    public void checkCollision(HumanPaddle p1, HumanPaddle p2) {
        if(x <= 50) {
            if(y >= p1.getY() && y <= p1.getY() + 100) {
                xVel = -xVel;
            }
        } else if(x >= 955) {
            if(y >= p2.getY() && y <= p2.getY() + 100) {
                xVel = -xVel;
            }
        }
    }

    public void move() {
        x += xVel;
        y += yVel;

        if(y < 10) {
            yVel = -yVel;
        }
        if(y > 770) {
            yVel = -yVel;
        }
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public void resetPos() {
        x = 480;
        y = 360;
        xVel = getRandomDirection() * getRandomSpeed();
        yVel = getRandomDirection() * getRandomSpeed();
    }
}
