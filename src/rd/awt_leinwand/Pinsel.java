package rd.awt_leinwand;

import java.awt.*;

public class Pinsel extends Canvas {
    private int xPos, yPos;
    private int xPosPrev, yPosPrev;
    public int breite, hoehe;
    public Color color;

    Pinsel(int x, int y, int b, int h, Color c) {
        xPos = x;
        yPos = y;
        breite = b;
        hoehe = h;
        color = c;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPosPrev = this.xPos;
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPosPrev = this.yPos;
        this.yPos = yPos;
    }

    public void resetPrevPos() {
        xPosPrev = xPos;
        yPosPrev = yPos;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(xPos- breite/2, yPos - hoehe/2, breite, hoehe);
        g.drawLine(xPosPrev, yPosPrev, xPos, yPos);
    }

    @Override
    public String toString() {
        return "Pinsel{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", xPosPrev=" + xPosPrev +
                ", yPosPrev=" + yPosPrev +
                '}';
    }
}
