package Levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 25
 */
public class LoserScreen implements Animation{
    private KeyboardSensor keyboardSensor;
    private boolean exit;
    private final int FONT_SIZE = 32;
    private final int CENTER = 125;
    private final int score;
    public LoserScreen(KeyboardSensor keyboardSensor, int score) {
        this.keyboardSensor = keyboardSensor;
        this.exit = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface drawSurface) {
        drawSurface.drawText(CENTER, drawSurface.getHeight() / 2,
                "Game Over. Your score is " + score, FONT_SIZE);
        if (this.keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
            this.exit = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.exit;
    }
}
