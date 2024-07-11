// 345184295 Yonatan Benchabbat
package GameMaintenance;

import GameElements.Sprite;
import biuoop.DrawSurface;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -05- 04
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Constructor for creating SpriteCollection.
     */
    public SpriteCollection() {
        this.spriteList = new LinkedList<>();
    }
    /**
     * addSprite adds sprite to sprite collection.
     * @param sprite sprite to be added.
     */
    public void addSprite(Sprite sprite) {
        this.spriteList.add(sprite);
    }

    /**
     * copy creates a copy of this sprite collection.
     * @param beingCopied location of values being copied
     */
    public void copy(SpriteCollection beingCopied) {
        this.spriteList = new LinkedList<>(beingCopied.spriteList);
        Collections.copy(beingCopied.spriteList, this.spriteList);
    }

    /**
     * notifyAllTimePassed notifies all sprites in collection that time has
     * passed.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite:this.spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * drawAllOn draws all sprites on surface.
     * @param surface surface to be drawn on.
     */
    public void drawAllOn(DrawSurface surface) {
        for (Sprite sprite:this.spriteList) {
            sprite.drawOn(surface);
        }
    }

    /**
     * removeSprite removes sprite from the sprite collection.
     * @param sprite sprite to be removed.
     */
    public void removeSprite(Sprite sprite) {
        spriteList.remove(sprite);
    }
}
