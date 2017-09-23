package MyBoard.Listener;

import MyBoard.*;
import MyBoard.Shape.*;
import MyBoard.Shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class PanelListener implements MouseListener, MouseMotionListener {
    public Graphics2D g;
    public int x1, y1, x2, y2, ox, oy, x3, y3;
    public ButtonGroup bg;
    public String command;
    public Color color;
    public initFrame iF;
    public ArrayList list;
    public boolean flag = true;
    public String stamp;
    public static final Stroke s1 = new BasicStroke(1);
    public static final Stroke s2 = new BasicStroke(10);
    public static final Stroke s3 = new BasicStroke(15);
    public Random r = new Random();

    public PanelListener(Graphics g2, ButtonGroup bg2, initFrame frame, ArrayList list) {
        g = (Graphics2D) g2;
        bg = bg2;
        iF = frame;
        this.list = list;
    }

    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        if (iF.popStatus) {
            command = "pop";
        } else {
            ButtonModel bm = bg.getSelection();
            command = bm.getActionCommand();
            stamp = String.valueOf(System.currentTimeMillis());
        }
    }

    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        if ("pic10".equals(command)) {
            Shape line = new Line(x1, y1, x2, y2, g.getColor(), iF.w, iF.flag, stamp);
            line.Draw(g);
            list.add(line);
        }
        else if ("pic12".equals(command)) {
            Shape rect = new Rect(Math.min(x2, x1), Math.min(y2, y1), Math.abs(x2 - x1), Math.abs(y1 - y2), g.getColor(), iF.w, iF.flag, stamp);
            rect.Draw(g);
            list.add(rect);
        }//绘制椭圆
        else if ("pic14".equals(command)) {
            Shape oval = new Oval(Math.min(x2, x1), Math.min(y2, y1), Math.abs(x2 - x1), Math.abs(y1 - y2), g.getColor(), iF.w, iF.flag, stamp);
            oval.Draw(g);
            list.add(oval);
        } else if ("pic15".equals(command)) {
            Shape roundrect = new RoundRect(Math.min(x2, x1), Math.min(y2, y1), Math.abs(x2 - x1), Math.abs(y1 - y2), 40, 40, g.getColor(), iF.w, iF.flag, stamp);
            roundrect.Draw(g);
            list.add(roundrect);
        }//绘制曲线
        else if ("pic13".equals(command)) {

            if (flag) {
                Shape line = new Line(x1, y1, x2, y2, g.getColor(), iF.w, iF.flag, stamp);
                line.Draw(g);
                list.add(line);
                flag = false;
                x3 = x2;
                y3 = y2;
                ox = x1;
                oy = y1;
            } else {
                Shape line = new Line(x3, y3, x2, y2, g.getColor(), iF.w, iF.flag, stamp);
                line.Draw(g);
                list.add(line);
                x3 = x2;
                y3 = y2;
            }
        }
        //取色功能
        else if ("pic4".equals(command)) {
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            try {
                Robot robot = new Robot();
                Rectangle rect = new Rectangle(x, y, 1, 1);
                BufferedImage bi = robot.createScreenCapture(rect);
                int c = bi.getRGB(0, 0);
                Color color = new Color(c);
                iF.c = color;
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        } else if ("pop".equals(command)) {
            int temp;
            Random r = new Random();
            temp = r.nextInt(10) % 10 + 1;
            Bubble bubble = new Bubble(x2, y2, temp);
            String picString = "/" + temp + ".png";
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(picString));
            bubble.getjButton().setIcon(imageIcon);
            bubble.getjButton().setOpaque(false);
            bubble.getjButton().setBorder(null);
            bubble.getjButton().setContentAreaFilled(false);
            bubble.getjButton().setBorderPainted(false);
            iF.panelcenter.add(bubble.getjButton());
            iF.bubbles.add(bubble);
            bubble.start();
        }
    }

    public void mouseClicked(MouseEvent e) {
        //多边形图形双击封闭
        int count = e.getClickCount();
        if (count == 2 && "pic13".equals(command)) {
            Shape line = new Line(ox, oy, x2, y2, g.getColor(), iF.w, iF.flag, stamp);
            line.Draw(g);
            list.add(line);
            flag = true;
        }
    }

    public void mouseEntered(MouseEvent e) {
        color = iF.c;
        g.setColor(color);
        g.setStroke(s1);
    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //画笔功能
        if ("pic6".equals(command)) {
            Shape line = new Line(x1, y1, x, y, g.getColor(), iF.w, iF.flag, stamp);
            line.Draw(g);
            list.add(line);
            x1 = x;
            y1 = y;
        }
        //橡皮擦功能
        else if ("pic2".equals(command)) {
            Color tempColor = g.getColor();
            iF.c = Color.white;
            g.setColor(iF.c);
            g.setStroke(s3);
            Shape line = new Line(x1, y1, x, y, g.getColor(), iF.w * 15, iF.flag, stamp);
            line.Draw(g);
            list.add(line);
            x1 = x;
            y1 = y;
            g.setColor(tempColor);
            iF.c=tempColor;
        }
        //刷子功能
        else if ("pic7".equals(command)) {
            g.setStroke(s2);
            Shape line = new Line(x1, y1, x, y, g.getColor(), iF.w * 10, iF.flag, stamp);
            line.Draw(g);
            list.add(line);
            x1 = x;
            y1 = y;
        }
        //喷桶功能
        else if ("pic8".equals(command)) {
            for (int i = 0; i < 30; i++) {
                int xp = r.nextInt(31) - 15;
                int yp = r.nextInt(31) - 15;
                Shape line = new Line(x + xp, y + yp, x + xp, y + yp, g.getColor(), iF.w, iF.flag, stamp);
                line.Draw(g);
                list.add(line);
            }
        } else if ("pic9".equals(command)) {
            String input = "";
            input = JOptionPane.showInputDialog("输入文字");
            Shape Text = new Text(x, y, input, g.getColor(), iF.f, iF.font, stamp);
            Text.Draw(g);
            list.add(Text);
        }
    }

    public void mouseMoved(MouseEvent e) {

    }
}
