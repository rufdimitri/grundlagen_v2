package rd.spacespiel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

public class SpaceGame extends JPanel implements ActionListener {

    private Spaceship spaceship = new Spaceship(new Point2D.Double(40, 40), 40);
    private Color background;
    private boolean isLeftPressed, isRightPressed, isUpPressed, isDownPressed;
    private double speedX = 0;
    private double speedY = 0;
    private double maxSpeed = 20;
    private double acceleration = 1.0;
    private int delay = 40;
    private Timer timer = new Timer(delay, this);
    private int width, height;


    public SpaceGame(int w, int h) {
        width = w;
        height = h;
        this.background = StartWindow.backgroundColor;
        setBackground(background);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    isLeftPressed = true;
                } else if (key == KeyEvent.VK_UP) {
                    isUpPressed = true;
                } else if (key == KeyEvent.VK_RIGHT) {
                    isRightPressed = true;
                } else if (key == KeyEvent.VK_DOWN) {
                    isDownPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    isLeftPressed = false;
                } else if (key == KeyEvent.VK_UP) {
                    isUpPressed = false;
                } else if (key == KeyEvent.VK_RIGHT) {
                    isRightPressed = false;
                } else if (key == KeyEvent.VK_DOWN) {
                    isDownPressed = false;
                }
            }
        });
        timer.start();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isLeftPressed || isRightPressed) {
            if (isLeftPressed) {
                speedX -= acceleration;
                if(speedX < -maxSpeed) {
                    speedX = -maxSpeed;
                }
            }
            if (isRightPressed) {
                speedX += acceleration;
                if(speedX > maxSpeed) {
                    speedX = maxSpeed;
                }
            }
        } else {
            speedX *= 0.9; //if no keys pressed, automatic slow down
        }
        if (isUpPressed || isDownPressed) {
            if (isUpPressed) {
                speedY -= acceleration;
                if(speedY < -maxSpeed) {
                    speedY = -maxSpeed;
                }
            }
            if (isDownPressed) {
                speedY += acceleration;
                if(speedY > maxSpeed) {
                    speedY = maxSpeed;
                }
            }
        } else {
            speedY *= 0.9;  //if no keys pressed, automatic slow down
        }

        //speedY += acceleration/2; //gravitation
        spaceship.position.x += speedX;
        spaceship.position.y += speedY;
        if (spaceship.position.x < 0) {
            spaceship.position.x += width;
        }
        if (spaceship.position.x > width) {
            spaceship.position.x -= width;
        }
        if (spaceship.position.y < 0) {
            spaceship.position.y += height;
        }
        if (spaceship.position.y > height) {
            spaceship.position.y -= height;
        }

        repaint();
    }
}
