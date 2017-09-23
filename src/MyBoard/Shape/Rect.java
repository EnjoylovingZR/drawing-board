package MyBoard.Shape;

import MyBoard.Shape.Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Rect extends Shape implements Serializable {

    public Rect() {

    }

    //矩形的构造方法
    public Rect(int x1, int y1, int x2, int y2, Color color, int width, int flag, String stamp) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = width;
        this.flag = flag;
        this.stamp = stamp;
    }

    //重写父类的Draw方法，实现矩形的绘制
    public void Draw(Graphics2D g) {
        g.setColor(this.color);
        if (flag == 1) {
            float[] dash = {5, 5}; //短划线图案
            BasicStroke bs = new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f); //实例化新画刷
            g.setStroke(bs); //设置新的画刷
        } else if (flag == 0) {
            g.setStroke(new BasicStroke(width));
        } else if (flag == -1) {
            float[] dash = {2, 2}; //短划线图案
            BasicStroke bs = new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 10.0f, dash, 0.0f); //实例化新画刷
            g.setStroke(bs); //设置新的画刷
        }
        g.drawRect(x1, y1, x2, y2);
    }

}
