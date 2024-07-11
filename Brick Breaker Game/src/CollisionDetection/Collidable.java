// 345184295 Yonatan Benchabbat
package CollisionDetection;

import GameElements.Ball;
import Geometry.Point;
import Geometry.Rectangle;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -05- 04
 */
public interface Collidable {
    /**
     * getCollisionRectangle returns the rectangle of the collidable object.
     * @return returns the collision rectangle of the collidable
     */
    Rectangle getCollisonRectangle();

    /**
     * hit denotes a hit and changes the velocity accordingly and notifies
     * hit listeners of a hit occurring.
     * @param hitter ball hitting object.
     * @param collision point of collision.
     * @param velocity velocity after collision.
     * @return returns the new velocity.
     */
    Velocity hit(Ball hitter, Point collision, Velocity velocity);
}
