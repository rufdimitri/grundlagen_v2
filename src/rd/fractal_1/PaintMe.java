package rd.fractal_1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PaintMe extends JFrame {
    private int width = 500, height = 500;
    private boolean isBackground = false;
    private Random random = new Random();
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private BufferedImage graphicsContext;
    private JLabel contextRender;
    private JPanel contentPanel = new JPanel();

    double constantXMin = -0.67;
    double constantXMax = -constantXMin;
    double constantYMin = -0.67;
    double constantYMax = -constantYMin;
    double constantX = constantXMin;
    double constantY = constantYMin;
    double deltaX = 0.001;
    double deltaY = 0.001;
    Runnable swingTask = () -> {
        SwingUtilities.invokeLater(this.renderTask);
    };
    Runnable renderTask = () -> {
        if (deltaX > 0 && constantX + deltaX > constantXMax
                || deltaX < 0 && constantX + deltaX < constantXMin) {
                deltaX = -deltaX;
        }
        constantX += deltaX;

        if (deltaY > 0 && constantY + deltaY > constantYMax
                || deltaY < 0 && constantY + deltaY < constantYMin) {
            deltaY = -deltaY;
        }
        constantY += deltaY;
        render(new Point2D.Double(width, height), new Point2D.Double(constantX, constantY), 500);
        executorService.schedule(this.swingTask, 1, TimeUnit.MILLISECONDS);
    };


    public PaintMe () {
        super("PaintMe Canvas");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        graphicsContext = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        contextRender = new JLabel(new ImageIcon(graphicsContext));

        contentPanel.add(contextRender);

        contentPanel.setLayout(new CardLayout());
        this.setContentPane(contentPanel);
        this.pack();

        setVisible(true);
        renderTask.run();
    }

    Point2D.Double computeNextJuliaMenge(Point2D.Double current, Point2D.Double constant) {
        //Zn²
        double zr = current.x * current.x - current.y * current.y;
        double zi = 2.0 * current.x * current.y;

        //Add constant
        return new Point2D.Double(zr + constant.x, zi + constant.y);
    }



    /**
     * Returns the mod squared
     * @param g
     */
    double mod2(Point2D.Double z) {
        return z.x * z.x + z.y * z.y;
    }

    /**
     * Computes sequence elements until mod exceeds threshold or max iteration is reached
     * @param g
     */
    int computeIterations(Point2D.Double z0, Point2D.Double constant, int maxIteration) {
        Point2D.Double zn = z0;
        int iteration = 0;
        while (mod2(zn) < 4.0 && iteration < maxIteration) {
            zn = computeNextJuliaMenge(zn ,constant);
            //System.out.println("x = " + zn.x + " y = " + zn.y);
            iteration++;
        }
        //System.out.println("-----------------");
        return iteration;
    }

    /**
     * Computes pixels color
     * @param g
     */
    void render(Point2D.Double renderSize, Point2D.Double constant, int maxIterations) {
        this.setTitle("constX = " + constant.x + " constY = " + constant.y);

        //Compute the scale of the coordinates
        double scale = 1.0 / (renderSize.y / 2.0);
        for(int y = 0; y < renderSize.y; ++y) {
            for(int x = 0; x < renderSize.x; ++x) {
                //Compute pixel's coordinates
                double px = (x - renderSize.x / 2) * scale;
                double py = (y - renderSize.y / 2) * scale;
                //Compute color
                double iterations = computeIterations(new Point2D.Double(px, py), constant, maxIterations);
                setPixelColor(x, y, gradientGetColor(iterations, maxIterations));
            }
        }
        contextRender.repaint();
    }

    Color gradientGetColor(double gradientValue, double maxGradientValue) {
        int rgbPart = (int)Math.round(255*3 * gradientValue/maxGradientValue);
        int green = rgbPart > 255 ? 255 : rgbPart;
        rgbPart -= green;
        int blue = rgbPart > 255 ? 255 : rgbPart;
        rgbPart -= blue;
        int red = rgbPart > 255 ? 255 : rgbPart;
        rgbPart -= red;

        return new Color(red, green, blue);
    }

    int graphicX(int x) {
        //return x + width/2;
        return x;
    }
    int graphicY(int y) {
        return height - y;
        //return height/2 - y;
    }


    void setPixelColor(int x, int y, Color color) {
        Graphics2D g2d = (Graphics2D) graphicsContext.getGraphics();
        Color prevColor = g2d.getColor();
        g2d.setColor(color);
        Rectangle2D.Double pixel = new Rectangle2D.Double(graphicX(x),graphicY(y),1,1);
        g2d.fill(pixel);
        g2d.setColor(prevColor);
    }

    void paintBackground (Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, this.getWidth(),this.getHeight());
    }

    void paintOval (Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(100, 200, 40, 60);
    }


}