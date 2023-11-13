package rd.fractal_1;

import java.awt.geom.Point2D;

public class RenderTask implements Runnable {
    static int count;
    int id = count++;
    int yBegin, yEnd;
    Point2D.Double renderSize;
    double scale;
    int maxIterations;
    Point2D.Double constant;
    PaintMe paintMe;

    public Runnable setParams(PaintMe paintMe, int yBegin, int yEnd, Point2D.Double renderSize, double scale,
                              Point2D.Double constant, int maxIterations) {
        this.paintMe = paintMe;
        this.yBegin = yBegin;
        this.yEnd = yEnd;
        this.renderSize = renderSize;
        this.scale = scale;
        this.maxIterations = maxIterations;
        this.constant = constant;
        return this;
    }

    @Override
    public void run() {
        //System.out.format("%d : %d .. %d\n", id, yBegin, yEnd);
        for (int y = yBegin; y < yEnd; ++y) {
            for (int x = 0; x < renderSize.x; ++x) {
                // Compute pixel's coordinates
                double px = (x - renderSize.x / 2) * scale;
                double py = (y - renderSize.y / 2) * scale;
                // Compute color
                double iterations = paintMe.computeIterations(new Point2D.Double(px, py), constant, maxIterations);
                paintMe.setPixelColor(x, y, paintMe.gradientGetColor(iterations, maxIterations));
            }
        }
    }

}
