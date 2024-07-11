// 345184295 Yonatan Benchabbat
package GameElements;

import CollisionDetection.Collidable;
import CollisionDetection.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import Levels.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -05- 04
 */
public class Paddle implements Collidable, Sprite {
    private biuoop.KeyboardSensor keyboardSensor;
    private final int PADDLE_HEIGHT = 10;
    private final int PARTS = 5;
    private final int STARTING_X = 400;
    private final int STARTING_Y = 560;
    private final int SPEED = 5;
    private Color color;
    private Geometry.Rectangle rectangle;
    private Point upperLeft;
    private int speed;
    private int width;
    /**
     * Paddle constructor.
     */
    public Paddle(int paddleSpeed, int paddleWidth) {
        this.speed = paddleSpeed;
        this.width = paddleWidth;
        this.upperLeft = new Geometry.Point(STARTING_X, STARTING_Y);
        this.rectangle = new Geometry.Rectangle(this.upperLeft, width,
                PADDLE_HEIGHT);
        this.color = Color.yellow;
    }

    /**
     * sets or initializes a keyboard sensor to detect movement for paddle.
     * @param keyboardSensor keyboard sensor being added to paddle.
     */
    public void setKeyboardSensor(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * moveLeft moves the paddle to the left.
     */
    public void moveLeft() {
        this.upperLeft = new Geometry.Point(this.upperLeft.getX() - speed,
                this.upperLeft.getY());
        this.rectangle = new Geometry.Rectangle(this.upperLeft, width,
                PADDLE_HEIGHT);

    }

    /**
     * moveRight moves the paddle right.
     */
    public void moveRight() {
        this.upperLeft = new Geometry.Point(this.upperLeft.getX() + this.speed,
                this.upperLeft.getY());
        this.rectangle = new Geometry.Rectangle(this.upperLeft, width,
                PADDLE_HEIGHT);
    }
    @Override
    public Rectangle getCollisonRectangle() {
        return this.rectangle;
    }
    @Override
    public Velocity hit(Ball hitter, Geometry.Point point, Velocity velocity) {
        int partLength = this.width / PARTS;
        int partHit = (int) (point.getX() / partLength);
        switch (partHit) {
            case 0:
                velocity = velocity.fromAngleAndSpeed(300, SPEED);
                break;
            case 1:
                velocity = velocity.fromAngleAndSpeed(330, SPEED);
                break;
            case 2:
                velocity = velocity.fromAngleAndSpeed(360, SPEED);
                break;
            case 3:
                velocity = velocity.fromAngleAndSpeed(30, SPEED);
                break;
            case 4:
                velocity = velocity.fromAngleAndSpeed(60, SPEED);
                break;
        }
        return velocity;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }
    @Override
    public void timePassed() {
        if (keyboardSensor.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboardSensor.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * addToGame adds the paddle to a given game.
     * @param game game paddle is being added to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
