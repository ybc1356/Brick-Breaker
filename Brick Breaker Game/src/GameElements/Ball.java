// 345184295 Yonatan Benchabbat
package GameElements;

import CollisionDetection.CollisionInfo;
import GameMaintenance.GameEnvironment;
import CollisionDetection.Velocity;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Levels.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Yonatan Benchababt
 * @version 2.0
 * @since 2023 -05-03
 */

@SuppressWarnings("FieldCanBeLocal")
public class Ball implements Sprite {
    private final int MAX_TRAJECTORY_FACTOR = 1000;
    private final double THRESHOLD = 0.00001;
    private final int TOP = 0;
    private final int LEFT = 1;
    private final int BOTTOM = 2;
    private final int RIGHT = 3;
    private final int EDGES = 4;
    private final int CHANGE_DIRECTION = -1;
    private Point center;
    private int size;
    private Color color;
    private Velocity velocity;
    private Line trajectory;
    private GameEnvironment gameEnvironment;

    /**
     * constructor for Ball class.
     * @param center center of ball on Cartesian plane.
     * @param radius radius of ball.
     * @param color color of ball.
     */
    public Ball(Point center, int radius, Color color) {
        this.center = center;
        this.size = radius;
        this.color = color;
    }

    /**
     * setVelocity sets a velocity based on already existing velocity.
     * @param velocity velocity to be set.
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
        setTrajectory();
    }

    /**
     * setVelocity that sets velcity based on deltaX and deltaY.
     * @param x desired change in X.
     * @param y desired change in Y.
     */
    public void setVelocity(double x, double y) {
        this.velocity = new Velocity(x, y);
        setTrajectory();
    }

    /**
     * setTrajectory sets trajectory of ball based on velocity.
     */
    public void setTrajectory() {
        this.trajectory = new Line(this.center.getX(), this.center.getY(),
                this.center.getX()
                        + MAX_TRAJECTORY_FACTOR * this.velocity.getX(),
                this.center.getY()
                        + MAX_TRAJECTORY_FACTOR * this.velocity.getY());
    }

    /**
     * setGameEnvironment sets a game environment based on input.
     * @param game game environment being received by Ball class.
     */
    public void setGameEnvironment(GameEnvironment game) {
        this.gameEnvironment = game;
    }

    /**
     * getX returns x value of center of Ball.
     * @return  X value of center of ball.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * getX returns y value of center of Ball.
     * @return  Y value of center of ball.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * getColor returns color of ball.
     * @return returns the color.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * getSize returns size of ball.
     * @return  size of Ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * getVelocity returns current velocity of ball.
     * @return velocity of ball.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * moveOneStep moves the ball one step based on velocity until it
     * collides with an object.
     */
    public void moveOneStep() {
        CollisionInfo collision = firstCollision();
        this.center = this.velocity.applyToPoint(this.center);
        if (collision != null) {
            if (centerCollides(firstCollision())) {
                this.changeDirection(collision);
            }
        }
    }

    /**
     * firstCollision returns the first collision that occurs on the trajectory.
     * @return CollisionInfo object of first collision.
     */
    public CollisionInfo firstCollision() {
        return this.gameEnvironment.getClosestCollision(this.trajectory);
    }

    /**
     * centerCollides returns true if center of ball collides with given
     * collsion info.
     * @param collision CollisionInfo of possible collision.
     * @return boolean value for collision; true if a collision occurs.
     */
    public boolean centerCollides(CollisionInfo collision) {
        if (this.velocity.getX() < 0) {
            if (this.center.getX() + this.velocity.getX()
                    <= collision.collisionPoint().getX()) {
                return true;
            }
        }
        if (this.velocity.getX() > 0) {
            if (this.center.getX() + this.velocity.getX()
                    >= collision.collisionPoint().getX()) {
                return true;
            }
        }
        return false;
    }

    /**
     * changeDirections changes direction of ball if collision occurs.
     * @param firstCollision collision info of first possible collision.
     */
    public void changeDirection(CollisionInfo firstCollision) {
        Rectangle collisionRectangle =
                firstCollision.collisionObject().getCollisonRectangle();
        Line[] edges = collisionRectangle.getEdges();
        Line[] verticals = {edges[LEFT], edges[RIGHT]};
        Line[] horizontals = {edges[TOP], edges[BOTTOM]};
        Point collisionPoint = firstCollision.collisionPoint();
//        Paddle paddle = new Paddle(0, 30);
//        if (firstCollision.collisionObject().equals(paddle)) {
//           this.velocity =
//                   firstCollision.collisionObject().
//                           hit(this, firstCollision.collisionPoint(),
//                                   this.velocity);
//           return;
//        }
        // change the direction of velocity based on nature of the hit
        for (Line line: verticals) {
            if (trajectory.isIntersecting(line)) {
                if (trajectory.intersectionWith(line).equals(collisionPoint)) {
                    this.setVelocity(this.velocity.getX() * CHANGE_DIRECTION,
                            this.velocity.getY());
                    firstCollision.collisionObject().
                            hit(this, firstCollision.collisionPoint(),
                                    this.velocity);
                }
            }
        }
        for (Line line: horizontals) {
            if (trajectory.isIntersecting(line)) {
                if (trajectory.intersectionWith(line).equals(collisionPoint)) {
                    this.setVelocity(this.velocity.getX(),
                            this.velocity.getY() * CHANGE_DIRECTION);
                    firstCollision.collisionObject().
                            hit(this, firstCollision.collisionPoint(),
                                    this.velocity);
                }
            }
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.size);
    }
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * addToGame adds a ball to the game.
     * @param game game receiving the ball.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
    /**
     * removeFromGame adds a ball to the game if it falls below the screen.
     * @param game game balls is being removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
