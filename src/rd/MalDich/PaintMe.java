package rd.MalDich;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Random;

public class PaintMe extends JFrame {
    private int width = 500, height = 500;
    private boolean isBackground = false;
    private Random random = new Random();

    public PaintMe () {
        super("PaintMe Canvas");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void paint (Graphics g) {
       paintBackground(g);

        paintOval(g);
    }

    public void paintBackground (Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(0,0, this.getWidth(),this.getHeight());
    }

    public void paintOval (Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(100, 200, 40, 60);
    }

    public void paintPolygon (Graphics g) {
        int[] xcoords = {100, 200, 150};
        int[] ycoords = {100, 100, 200};
        g.setColor(new Color(100, 150, 0));
        g.fillPolygon(xcoords, ycoords, 3);
    }

}
