package MyBoard.Shape;

import MyBoard.Shape.Shape;

import java.awt.*;
import java.io.Serializable;

public class Text extends Shape implements Serializable {
    public String str;//文字
    public int f;
    public String font;

    public Text(int x, int y, String str, Color color, int f, String font, String stamp) {
        this.x1 = x;
        this.y1 = y;
        this.str = str;
        this.f = f;
        this.font = font;
        this.stamp = stamp;
    }

    public void Draw(Graphics2D g) {
        g.setFont(new Font(font, Font.PLAIN, f));
        g.drawString(str, x1, y1);
    }
}
