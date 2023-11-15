package rd.awt_eyes;

import rd.awt_eyes.graphic.Face;

import javax.swing.*;
import java.awt.*;

public class PanelX extends JPanel {

    Face face;

    public Face getFace() {
        return face;
    }

    public PanelX(int width, int height) {
        setSize(width, height);
        System.out.format("getWidth: %d\n", getWidth());
        face = Face.createFace(getWidth() / 2, getHeight() / 2, getWidth() -300, getHeight()-300, Color.YELLOW, Color.LIGHT_GRAY, Color.BLUE);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        face.paint(g);

    }
}
