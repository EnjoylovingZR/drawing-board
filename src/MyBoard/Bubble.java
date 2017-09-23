package MyBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bubble extends Thread {
    private JButton jButton;
    private Integer tx, ty;
    private int xx, yy;
    private int pic;
    private boolean RUN_STATE = true;
    private int speed;

    public Bubble(int x, int y, int pic) {
        this.pic = pic;
        jButton = new JButton();
        int rand = (int) (Math.random() * 8);
        Random r = new Random();
        speed = r.nextInt(3) % 3 + 1;
        if (System.currentTimeMillis() % 3 == 0) {
            tx = speed;
        } else {
            tx = -speed;
        }
        if (System.currentTimeMillis() % 5 == 0) {
            ty = speed;
        } else {
            ty = -speed;
        }
        this.xx = x;
        this.yy = y;
    }

    public boolean isRUN_STATE() {
        return RUN_STATE;
    }

    public void setRUN_STATE(boolean RUN_STATE) {
        this.RUN_STATE = RUN_STATE;
    }

    public JButton getjButton() {
        return jButton;
    }

    public void setjButton(JButton jButton) {
        this.jButton = jButton;
    }

    public Point getLocation() {
        Point point = new Point();
        point.x = xx;
        point.y = yy;
        return point;
    }

    @Override
    public void run() {
        Point startPoint = getLocation();
        jButton.setBounds(startPoint.x, startPoint.y, 30, 30);
        while (RUN_STATE) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int newC;
            Random r = new Random();
            newC = r.nextInt(10) % 10 + 1;
            while (newC == pic) {
                Random r1 = new Random();
                newC = r1.nextInt(10) % 10 + 1;
            }
            pic = newC;
            String picString = "/" + pic + ".png";
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(picString));
            if (startPoint.x <= 0) {
                tx = -tx;
                jButton.setIcon(imageIcon);
            }
            if (startPoint.x >= 955) {
                tx = -tx;
                jButton.setIcon(imageIcon);
            }
            if (startPoint.y <= 0) {
                ty = -ty;
                jButton.setIcon(imageIcon);
            }
            if (startPoint.y >= 512) {
                ty = -ty;
                jButton.setIcon(imageIcon);
            }
            jButton.setBounds(startPoint.x, startPoint.y, 30, 30);
            startPoint.x += tx;
            startPoint.y += ty;
        }
    }
}
