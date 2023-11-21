package rd.awt_eyes.graphic;

import java.awt.*;

public class Eye{
    private int width;
    private int height;
    private int x;
    private int y;
    private Color color;
    Pupil pupil;

    public Pupil getPupil() {
        return pupil;
    }

    public Eye(int width, int height, Color color, Pupil pupil) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
        this.pupil = pupil;
    }


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


    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x - width / 2, y - height/2, width, height);
        pupil.paint(g);
    }
}
