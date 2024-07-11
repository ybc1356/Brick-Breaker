// 345184295 Yonatan Benchabbat
package GameMaintenance;

import CollisionDetection.Collidable;
import CollisionDetection.CollisionInfo;
import Geometry.Line;
import Geometry.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -05- 04
 */
@SuppressWarnings("FieldCanBeLocal")
public class GameEnvironment {
    private List<Collidable> collidablesList;
    private final int FIRST = 0;

    /**
     * Constructor for GameEnvironment.
     */
    public GameEnvironment() {
        this.collidablesList = new ArrayList<>();
    }

    /**
     * addCollidable adds a collidable to the collidables list of the game
     * environment.
     * @param collidable
     */
    public void addCollidable(Collidable collidable) {
        this.collidablesList.add(collidable);
    }

    /**
     * copies all colidables from a given GameEnvironment to this one.
     * @param beingCopied GameEnvironment being copied.
     */
    public void copy(GameEnvironment beingCopied) {
        this.collidablesList = new ArrayList<>(beingCopied.collidablesList);
        Collections.copy(beingCopied.collidablesList, this.collidablesList);
    }
    /**
     * getClosestCollision gets the first/closest collision along a given
     * trajectory.
     * @param trajectory trajectory of given object.
     * @return returns the CollisionInfo of closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> collisionInfos = possibleCollisions(trajectory);
        if (collisionInfos.isEmpty()) {
            return null;
        }
        CollisionInfo firstCollision = collisionInfos.get(FIRST);
        int collisionCounter = FIRST;
        while (firstCollision.collisionPoint().equals(trajectory.start())) {
            collisionCounter++;
            firstCollision = collisionInfos.get(collisionCounter);
        }

        for (CollisionInfo collision: collisionInfos) {
            if (trajectory.start().distance(firstCollision.collisionPoint())
                    > trajectory.start().distance(collision.collisionPoint())) {

                // again preventing counting bounce as closest collision
                if (!(collision.collisionPoint().equals(trajectory.start()))) {
                    firstCollision = collision;
                }
            }
        }
        return firstCollision;
    }

    /**
     * possibleCollisions returns a list of possible collisions along a given
     * trajectory. Used to iterate through in order to determine the closest
     * collision.
     * @param trajectory trajectory of a given object.
     * @return returns a list of possible collisions.
     */
    public List<CollisionInfo> possibleCollisions(Line trajectory) {
        List<CollisionInfo> possibleCollisions = new ArrayList<>();
        for (Collidable c: this.collidablesList) {
            Point point = trajectory.closestIntersectionToStartOfLine(c.getCollisonRectangle());
            if (point != null) {
                CollisionInfo collision = new CollisionInfo(point, c);
                possibleCollisions.add(collision);
            }
        }
        return possibleCollisions;
    }

    /**
     * removeCollidable removes a collidable from the collidables list
     * effectively removing it from the game.
     * @param collidable collidable to be removed from.
     */
    public void removeCollidable(Collidable collidable) {
        collidablesList.remove(collidable);
    }
}
