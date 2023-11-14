package rd.awt_leinwand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Leinwand extends JFrame implements MouseListener, MouseMotionListener {
    private Pinsel pinsel = null;
    private int width, height;
    boolean background = false;

    public Leinwand() {
        super("Leinwand");

        pinsel = new Pinsel(0,0,7,7, Color.WHITE);

        width = height = 500;
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addMouseListener(this);
        addMouseMotionListener(this);

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        if (!background) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
            background = true;
        }
        pinsel.paint(g);
    }

    public void male(int x, int y) {
        pinsel.setxPos(x);
        pinsel.setyPos(y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
        male(e.getX(), e.getY());
        pinsel.resetPrevPos();
        System.out.println(pinsel);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exited");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println(pinsel);
        male(e.getX(), e.getY());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
