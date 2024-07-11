// 345184295 Yonatan Benchabbat
package Levels;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 25
 */
public class AnimationRunner {
    private final int FPS = 60;
    private final int MPF = 1000;
    private final int MINIMUM = 0;
    private final int GAME_WIDTH = 800;
    private final int GAME_HEIGHT = 600;
    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor for AnimationRunner.
     */
    public AnimationRunner() {
        this.gui = new GUI("Game", GAME_WIDTH, GAME_HEIGHT);
        this.framesPerSecond = FPS;
    }
    /**
     * run is the delegate of an animation that takes care of running the
     * program.
     * @param animation animation being run.
     */
    public void run (Animation animation) {
        // FPS - frames per second
        // MPF - milliseconds per frame
        int millisecondsPerFrame = MPF / FPS;
        Sleeper sleeper = new Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface surface = gui.getDrawSurface();
            animation.doOneFrame(surface);
            gui.show(surface);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > MINIMUM) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * close closes the program and ensures it is done smoothly by running
     * one more frame.
     * @param animation animation being closed.
     */
    public void close(Animation animation) {
        // FPS - frames per second
        // MPF - milliseconds per frame
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = MPF / FPS;
        long startTime = System.currentTimeMillis();
        DrawSurface surface = gui.getDrawSurface();
        animation.doOneFrame(surface);
        gui.show(surface);
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > MINIMUM) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
        gui.close();
    }

    public GUI getGui() {
        return this.gui;
    }
}
