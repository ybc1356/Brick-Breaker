//345184295 Yonatan Benchabbat
package GameMaintenance;

import GameElements.Sprite;
import biuoop.DrawSurface;
/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 18
 */
public class ScoreIndicator implements Sprite {
    private final int HORIZONTAL_BORDER_WIDTH = 800;
    private final int HORIZONTAL_BORDER_HEIGHT = 30;
    private final int TWO = 2;
    private final int FONT_SIZE = 17;
    private Counter currentScore;

    /**
     * constructor for ScoreIndicator class.
     * @param score score being passed to score indicator to display.
     */
    public ScoreIndicator(Counter score) {
        this.currentScore = score;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.drawText(HORIZONTAL_BORDER_WIDTH / TWO,
                HORIZONTAL_BORDER_HEIGHT / TWO,
                Integer.toString(this.currentScore.getValue()), FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }
}
