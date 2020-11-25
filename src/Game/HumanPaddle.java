package Game;

import java.awt.*;

public class HumanPaddle {

    private double y, yVel;
    private boolean upAccel, downAccel;
    final double GRAVITY = 0.94;
    private int x;


    public HumanPaddle(int playerNum) {
        upAccel = false;
        downAccel = false;
        y = 360;

        if(playerNum == 1) {
            x = 20;
        } else {
            x = 955;
        }
    }


    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, (int)y, 20, 100);
    }


    public void move() {
        if(upAccel) {
            yVel -= 2;
        } else if(downAccel) {
            yVel += 2;
        } else if(!upAccel && ! downAccel) {
            yVel *= GRAVITY;
        }

        y += yVel;

        if(yVel >= 5) {
            yVel = 5;
        } else if(yVel <= -5) {
            yVel = -5;
        }

        if(y < 0) {
            y = 0;
        }

        if(y > 670) {
            y = 670;
        }
    }

    public void setUpAccel(boolean input) {
        upAccel = input;
    }

    public void setDownAccel(boolean input) {
        downAccel = input;
    }

    public void resetPos(int playerNum) {
        y = 360;
        upAccel = false;
        downAccel = false;
        if(playerNum == 1) {
            x = 20;
        } else {
            x = 955;
        }
    }


    public int getY() {
        return (int)y;
    }
}
