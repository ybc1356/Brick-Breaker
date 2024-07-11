// 345184295 Yonatan Benchabbat
package Geometry;

/**
 * @author Yonatan Benchababt
 * @version 2.0
 * @since 2023 -05-03
 */
public class Point {
    private final double THRESHOLD = 0.00001;
    private double x;
    private double y;
    // constructor of Geometry.Point
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // distance - measures distance
    public double distance(Point compare) {
        return Math.sqrt(((this.x - compare.x) * (this.x - compare.x))
                + ((this.y - compare.y) * (this.y - compare.y)));
        }
    // equals returns true if x and y of points are equal otherwise false
    public boolean equals(Point compare) {
        double differenceOfX = Math.abs(this.x - compare.x);
        double differenceOfY = Math.abs(this.y - compare.y);
        return (differenceOfX <= THRESHOLD) && (differenceOfY <= THRESHOLD);
    }
    // returns x value of point
    public double getX() {
        return x;
    }
    // returns y value of point
    public double getY() {
        return y;
    }
}