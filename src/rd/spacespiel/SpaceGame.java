package rd.spacespiel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public class SpaceGame extends JPanel {

    private Spaceship spaceship = new Spaceship(new Point2D.Double(40, 40), 40);
    private Color background;
    public SpaceGame(int w, int h) {
        this.background = StartWindow.backgroundColor;
        setBackground(background);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'w') {
                    SpaceGame.this.spaceship.position.y += -10;
                }
                if (e.getKeyChar() == 's') {
                    SpaceGame.this.spaceship.position.y += 10;
                }
                if (e.getKeyChar() == 'a') {
                    SpaceGame.this.spaceship.position.x += -10;
                }
                if (e.getKeyChar() == 'd') {
                    SpaceGame.this.spaceship.position.x += 10;
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("release");
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        spaceshipDraw(g, spaceship);
    }

    static void spaceshipDraw(Graphics g, Spaceship spaceship) {
        Point2D.Double position = spaceship.position;
        int size = spaceship.size;
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(5));
        int[] pointsX = {(int)Math.round(position.x-size/2), (int)Math.round(position.x), (int)Math.round(position.x+size/2)};
        int[] pointsY = {(int)Math.round(position.y+size/2), (int)Math.round(position.y-size/2), (int)Math.round(position.y+size/2)};
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.setColor(Color.CYAN);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        int[] pointsX2 = {(int)Math.round(position.x-size/1.5), (int)Math.round(position.x+size/1.5),
                (int)Math.round(position.x-size/1.3), (int)Math.round(position.x+size/1.3)};
        int[] pointsY2 = {(int)Math.round(position.y), (int)Math.round(position.y),
                (int)Math.round(position.y+size/3), (int)Math.round(position.y+size/3)};

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(pointsX2, pointsY2, pointsX2.length);
        g2d.setColor(Color.CYAN);
        g2d.drawPolygon(pointsX2, pointsY2, pointsX2.length);
    }
}
