// 345184295 Yonatan Benchabbat
package GameElements;

import biuoop.DrawSurface;
/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -05- 04
 */
public interface Sprite {
    /**
     * drawOn draws sprites on a given surface.
     * @param drawSurface surface being drawn on.
     */
    void drawOn(DrawSurface drawSurface);
    /**
     * time passed tells sprites to perform actions for time passed.
     */
    void timePassed();
}
