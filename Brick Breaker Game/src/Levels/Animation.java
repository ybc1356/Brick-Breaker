// 345184295 Yonatan Benchabbat
package Levels;

import biuoop.DrawSurface;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 25
 */
public interface Animation {
    /**
     * doOneFrame draws a surface for a single frame.
     * @param drawSurface surface being drawn.
     */
    void doOneFrame(DrawSurface drawSurface);

    /**
     * shouldStop returns a boolean based on stopping conditions provided.
     * @return returns boolean when true stops program.
     */
    boolean shouldStop();
}
