package rd.awt_eyes.graphic;


import rd.awt_eyes.PanelX;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Face  {
    private int width;
    private int height;
    private int x;
    private int y;
    private Color color;
    private Eye lEye;
    private Eye rEye;

    public PanelX getPanelX() {
        return panelX;
    }

    private PanelX panelX;

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
        updateEyePos();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Face(PanelX panelX, int x, int y, int width, int height, Color color, Eye lEye, Eye rEye) {
        this.panelX = panelX;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.lEye = lEye;
        this.rEye = rEye;
        updateEyePos();
    }

    private void updateEyePos() {
        lEye.setX(getLEyePosX());
        lEye.setY(getEyePosY());
        rEye.setX(getREyePosX());
        rEye.setY(getEyePosY());
    }

    public static Face createFace(PanelX panelX, int x, int y, int width, int height, Color faceColor, Color eyeColor, Color pupilColor) {
        int pupilSize = width / 10;
        Pupil lPupil = new Pupil(pupilSize, pupilSize, pupilColor);
        Pupil rPupil = new Pupil(pupilSize, pupilSize, pupilColor);
        Eye lEye = new Eye(pupilSize * 3, (int)Math.round(pupilSize * 1.5), eyeColor, lPupil);
        Eye rEye = new Eye(pupilSize * 3, (int)Math.round(pupilSize * 1.5), eyeColor, rPupil);
        return new Face(panelX, x, y, width, height, faceColor, lEye, rEye);
    }

    int getLEyePosX() {
        return x - width / 4;
    }

    int getREyePosX() {
        return x + width / 4;
    }

    int getEyePosY() {
        return y - height / 10;
    }

    public void updatePupilPos(int mouseX, int mouseY) {
        updatePupilPos(mouseX, mouseY, lEye, this);
        updatePupilPos(mouseX, mouseY, rEye, this);
    }


    static void updatePupilPos(int mouseX, int mouseY, Eye eye, Face face) {
        Pupil pupil = eye.getPupil();
        double distMax = (double)face.width / 2;
        int dXMouseEye = mouseX-eye.getX();
        int dYMouseEye = mouseY-eye.getY();
        double distFact =  Math.sqrt(dXMouseEye*dXMouseEye + dYMouseEye*dYMouseEye);
        double m = distFact / distMax;
        //int
    }





    static void updatePupilPos2(int mouseX, int mouseY, Eye eye, Face face) {
        Pupil pupil = eye.getPupil();
        int dX = mouseX - eye.getX();
        int dY = mouseY - eye.getY();
        double dC = Math.sqrt(dX*dX + dY*dY);
        double dC2 = Math.max(eye.getWidth(), eye.getHeight()) / 2.0;
        double m = dC2 / dC;
        int dX2 = (int)Math.round(dX * m);
        int dY2 = (int)Math.round(dY * m);
        pupil.setX(eye.getX() + dX2);
        pupil.setY(eye.getY() + dY2);
        double pupilW2 = pupil.getWidth() / 2.0;
        double pupilH2 = pupil.getHeight() / 2.0;
        double eyeW2 = eye.getWidth() / 2.0;
        double eyeH2 = eye.getHeight() / 2.0;
        Rectangle2D.Double pupilRect = new Rectangle2D.Double(pupil.getX()-pupilW2, pupil.getY()-pupilH2,
                pupil.getX() + pupilW2, pupil.getY() + pupilH2);
        Rectangle2D.Double eyeRect = new Rectangle2D.Double(eye.getX()-eyeW2, eye.getY()-eyeH2,
                eye.getX() + eyeW2, eye.getY() + eyeH2);

        if (pupilRect.x < eyeRect.x) {
            pupil.setX((int) Math.round(eyeRect.x + pupilW2));
        } else if (pupilRect.width > eyeRect.width) {
            pupil.setX((int) Math.round(eyeRect.width - pupilW2));
        }
        if (pupilRect.y < eyeRect.y) {
            pupil.setY((int) Math.round(eyeRect.y + pupilH2));
        } else if (pupilRect.height > eyeRect.height) {
            pupil.setY((int) Math.round(eyeRect.height - pupilH2));
        }
    }


    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x - width / 2, y - height/2, width, height);
        lEye.paint(g);
        rEye.paint(g);
    }
}
