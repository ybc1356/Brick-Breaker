// 345184295 Yonatan Benchabbat
package Levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 25
 */
public class WinScreen implements Animation{
    private KeyboardSensor keyboardSensor;
    private boolean exit;
    private final int FONT_SIZE = 32;
    private final int CENTER = 125;
    private final int score;
    public WinScreen(KeyboardSensor keyboardSensor, int score) {
        this.keyboardSensor = keyboardSensor;
        this.exit = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface drawSurface) {
        drawSurface.drawText(CENTER, drawSurface.getHeight() / 2,
                "You Win! Your Score is " + score, FONT_SIZE);
        if (this.keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
            this.exit = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.exit;
    }
}
