// 345184295 Yonatan Benchabbat
package CollisionDetection;

import Geometry.Point;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -05- 04
 */
public class CollisionInfo {
    private Point point;
    private Collidable object;
    /**
     * CollisionInfo constructor.
     * @param p point of collision.
     * @param collidable object being collided with.
     */
    public CollisionInfo(Point p, Collidable collidable) {
        this.point = p;
        this.object = collidable;
    }
    /**
     * collisionPoint gets point of collision.
     * @return returns point of collision.
     */
    public Point collisionPoint() {
        return this.point;
    }
    /**
     * collisionObject returns object of collision.
     * @return object of collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}
