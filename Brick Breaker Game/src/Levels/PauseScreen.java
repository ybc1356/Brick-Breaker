// 345184295 Yonatan Benchabbat
package Levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 25
 */
public class PauseScreen implements Animation{
    private KeyboardSensor keyboardSensor;
    private boolean unpause;
    private final int FONT_SIZE = 32;
    private final int CENTER = 125;

    public PauseScreen(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
        this.unpause = false;
    }
    @Override
    public void doOneFrame(DrawSurface drawSurface) {
        drawSurface.drawText(CENTER, drawSurface.getHeight() / 2,
                "Paused -- press space to continue", FONT_SIZE);
        if (this.keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
            this.unpause = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.unpause;
    }
}
