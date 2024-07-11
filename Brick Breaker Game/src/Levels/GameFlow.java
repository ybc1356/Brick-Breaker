// 345184295 Yonatan Benchabbat
package Levels;

import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yonatan Benchababt
 * @version 3.0
 * @since 2023 -06- 25
 */
public class GameFlow {
    private final List<LevelInformation> levelInfos;
    private final List<GameLevel> levels;
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;

    public GameFlow(List<LevelInformation> levelInfos) {
        this.levelInfos = levelInfos;
        this.levels = new ArrayList<>();
        this.animationRunner = new AnimationRunner();
        this.keyboardSensor = this.animationRunner.getGui().getKeyboardSensor();
    }

    public void initialize() {
        for (LevelInformation information : levelInfos) {
            this.levels.add(new GameLevel(information, animationRunner));
        }
    }

    public void run() {
        int score = 0, remainingBalls;
        for (GameLevel level : levels) {
            level.initialize();
            level.setScore(score);
            level.run();
            score = level.getScore();
            remainingBalls = level.remainingBalls();
            if (remainingBalls == 0) {
                LoserScreen loserScreen = new LoserScreen(this.keyboardSensor,
                        score);
                animationRunner.run(loserScreen);
                if (keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
                    animationRunner.close(loserScreen);
                }
                return;
            }
        }
        WinScreen winScreen = new WinScreen(this.keyboardSensor, score);
        animationRunner.run(winScreen);
        if (keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
            animationRunner.close(winScreen);
        }
    }
}