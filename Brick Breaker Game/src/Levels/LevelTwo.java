// 345184295 Yonatan Benchabbat
package Levels;

import CollisionDetection.Velocity;
import GameElements.Block;
import GameElements.Sprite;
import Geometry.Point;
import Geometry.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 25
 */
public class LevelTwo implements LevelInformation {
    private final double DEFAULT_DELTA_X = 2;
    private final double CHANGE_IN_DELTA = 0.3;
    private final int DEFAULT_DELTA_Y = -2;
    private final int BLOCK_WIDTH = 49;
    private final int numberOfBalls;
    private final List<Velocity> ballVelocities;
    private final int paddleSpeed;
    private final int paddleWidth;
    private final String levelName;
    private final Block background;
    private final List<Block> blockList;
    private final int numberOfBlocks;
    public LevelTwo() {
        this.numberOfBalls = 10;
        this.ballVelocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            if (i < numberOfBalls / 2) {
                this.ballVelocities.add(new Velocity(
                        -DEFAULT_DELTA_X + (i * CHANGE_IN_DELTA),
                        DEFAULT_DELTA_Y - (i * CHANGE_IN_DELTA)));
            } else {
                this.ballVelocities.add(new Velocity(
                        DEFAULT_DELTA_X
                                + ((9 * CHANGE_IN_DELTA) / ((double) i / 2)),
                        DEFAULT_DELTA_Y
                                + ((5 * CHANGE_IN_DELTA) / ((double) i / 2))));
            }
        }
        this.paddleSpeed = 10;
        this.paddleWidth = 700;
        this.levelName = "Wide Easy";
        this.background = new Block(new Rectangle(new Point(0, 0),
                1000, 1000));
        background.setColor(Color.white);
        this.blockList = new ArrayList<>();
        this.numberOfBlocks = 15;
        for (int i = 0; i < numberOfBlocks; i++) {
            this.blockList.add(new Block(new Point(30 + (i * BLOCK_WIDTH),
                    200)));
            if (i < 2) {
                blockList.get(i).setColor(Color.red);
            } else if (i < 4) {
                blockList.get(i).setColor(Color.orange);
            } else if (i < 6) {
                blockList.get(i).setColor(Color.yellow);
            } else if (i < 9) {
                blockList.get(i).setColor(Color.green);
            } else if (i < 11) {
                blockList.get(i).setColor(Color.blue);
            } else if (i < 13) {
                blockList.get(i).setColor(Color.pink);
            } else {
                blockList.get(i).setColor(Color.cyan);
            }
        }
    }
    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocks;
    }
}
