package rd.awt_leinwand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Leinwand extends JFrame implements MouseListener, MouseMotionListener {
    private Pinsel pinsel = null;
    private Pinsel eraser = null;
    private int width, height;
    boolean background = false;
    private boolean mouseLeftDown = false;
    private boolean mouseRightDown = false;

    public Leinwand() {
        super("Leinwand");

        pinsel = new Pinsel(0,0,7,7, Color.WHITE);
        eraser = new Pinsel(0,0,50,50, Color.BLACK);

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
        eraser.paint(g);
    }

    public void drawP(int x, int y) {
        pinsel.setxPos(x);
        pinsel.setyPos(y);
    }

    public void eraseP(int x, int y) {
        eraser.setxPos(x);
        eraser.setyPos(y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
        System.out.println(e.getButton());
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseLeftDown = true;
            drawP(e.getX(), e.getY());
            pinsel.resetPrevPos();
            System.out.println(pinsel);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            mouseRightDown = true;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseLeftDown = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            mouseRightDown = false;
        }
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
        if (mouseLeftDown) {
            drawP(e.getX(), e.getY());
        }
        if (mouseRightDown) {
            eraseP(e.getX(), e.getY());
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
