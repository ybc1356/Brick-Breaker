// 345184295 Yonatan Benchabbat

package GameElements;
import CollisionDetection.Collidable;
import GameMaintenance.HitListener;
import GameMaintenance.HitNotifier;
import CollisionDetection.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import Levels.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -05- 02
 */
@SuppressWarnings("FieldCanBeLocal")
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;
    private final int BLOCK_WIDTH = 49;
    private final int BLOCK_HEIGHT = 25;
    /**
     * Constructor for block.
     * @param rectangle rectangle being used to create a block.
     */
    public Block(Rectangle rectangle) {
        this.hitListeners = new ArrayList<>();
        this.rectangle = rectangle;
    }
    /**
     * Block constructor using just an upperLeft point and using default
     * height and width.
     * @param upperLeft Point of upper left corner of block.
     */
    public Block(Point upperLeft) {
        this.hitListeners = new ArrayList<>();
        this.rectangle = new Rectangle(upperLeft, BLOCK_WIDTH, BLOCK_HEIGHT);
    }

    /**
     * setColor determines the color of the block.
     * @param color colro of block.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    @Override
    public Rectangle getCollisonRectangle() {
        return this.rectangle;
    }
    @Override
    public Velocity hit(Ball hitter, Point collision, Velocity velocity) {
        this.notifyHit(hitter);
        return velocity;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }
    @Override
    public void timePassed() {
    }

    /**
     * addToGame adds the block to a given game.
     * @param game game block is being added to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * removeFromGame removes the block from game.
     * @param game game block is being removed from
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hitListener) {
        this.hitListeners.add(hitListener);
    }

    @Override
    public void removeHitListener(HitListener hitListener) {
        this.hitListeners.remove(hitListener);
    }
    @Override
    public void notifyHit(Ball hitter) {
        List<HitListener> listenersCopy = new ArrayList<>(this.hitListeners);
        for (HitListener hl: listenersCopy) {
            hl.hitEvent(this, hitter);
        }
    }
}