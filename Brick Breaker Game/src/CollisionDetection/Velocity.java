// 345184295 Yonatan Benchabbat
package CollisionDetection;
import Geometry.Point;

/**
 * @author Yonatan Benchababt
 * @version 2.0
 * @since 2023 -05-05
 */
public class Velocity {
    private double x;
    private double y;

    /**
     * constructor for velocity.
     * @param deltaX change in x.
     * @param deltaY change in y.
     */
    public Velocity(double deltaX, double deltaY) {
        this.x = deltaX;
        this.y = deltaY;
    }

    /**
     * getX returns x value of velocity.
     * @return the change in x.
     */
    public double getX() {
        return this.x;
    }
    /**
     * getY returns y value of velocity.
     * @return the change in y.
     */
    public double getY() {
        return this.y;
    }

    /**
     * applyToPoint applies the velocity to a given point.
     * @param point point being changed.
     * @return new point with new x and y.
     */
    public Point applyToPoint(Point point) {
        double x = point.getX() + this.x;
        double y = point.getY() + this.y;
        return new Point(x, y);
    }

    /**
     * fromAngleAndSpeed sets velocity.
     * @param angle angle used for velocity.
     * @param speed speed of velocity.
     * @return new velocity after speed and angle have been used to calculate
     * change in x and y.
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        double x = speed * Math.cos(angle);
        double y = -(speed * Math.sin(angle));
        return new Velocity(x, y);
    }
}
