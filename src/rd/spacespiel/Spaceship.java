package rd.spacespiel;

import java.awt.geom.Point2D;

public class Spaceship {
    Point2D.Double position;
    int size;

    public Spaceship(Point2D.Double position, int size) {
        this.position = position;
        this.size = size;
    }
}
