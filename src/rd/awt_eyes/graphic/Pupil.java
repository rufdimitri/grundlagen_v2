package rd.awt_eyes.graphic;

import java.awt.*;

public class Pupil {
    private int width;
    private int height;
    private int x;
    private int y;
    private Color color;
    private Stroke stroke = new BasicStroke(10);

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Pupil(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }


    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x - width / 2, y - height/2, width, height);
        g.setColor(color);
        ((Graphics2D)g).setStroke(stroke);
        g.drawOval(x - width / 2, y - height/2, width, height);
    }
}
