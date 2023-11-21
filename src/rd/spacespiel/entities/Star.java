package rd.spacespiel.entities;

import java.awt.geom.Point2D;

public class Star implements Comparable<Star> {
    public Point2D.Double position;
    public int size;

    public Star(Point2D.Double position, int size) {
        this.position = position;
        this.size = size;
    }

    @Override
    public int compareTo(Star o) {
        if (this.collides == o.collides) {
            return 0;
        } else if(this.collides) {
            return -1;
        } else {
            return 1;
        }
    }
}
