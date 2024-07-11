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
public class LevelThree implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Block background;
    private List<Block> blockList;
    private int numberOfBlocks;
    private final int BLOCK_WIDTH = 49;
    private final int BLOCK_HEIGHT = 25;
    private final int FIRST_ROW_Y = 300;
    public LevelThree() {
        this.numberOfBalls = 2;
        this.ballVelocities = new ArrayList<>();
        this.ballVelocities.add(new Velocity(-4, -2));
        this.ballVelocities.add(new Velocity(4, -2));
        this.paddleSpeed = 10;
        this.paddleWidth = 70;
        this.levelName = "Level 3";
        this.background = new Block(new Rectangle(new Point(0, 0),
                1000, 1000));
        background.setColor(Color.red);
        this.blockList = new ArrayList<>();
        this.numberOfBlocks = 40;
        for (int i = 0; i < numberOfBlocks; i++) {
            if (i < 6) {
                this.blockList.add(new Block(
                        new Point((770 - BLOCK_WIDTH - i * BLOCK_WIDTH),
                                FIRST_ROW_Y)));
                blockList.get(i).setColor(Color.white);
            } else if (i < 13) {
                this.blockList.add(new Block(
                        new Point((770 - BLOCK_WIDTH - ((i - 6) * BLOCK_WIDTH)),
                                FIRST_ROW_Y - BLOCK_HEIGHT)));
                blockList.get(i).setColor(Color.blue);
            } else if (i < 22) {
                this.blockList.add(new Block(
                        new Point((770 - BLOCK_WIDTH - ((i - 13) * BLOCK_WIDTH)),
                                FIRST_ROW_Y - (2 * BLOCK_HEIGHT))));
                blockList.get(i).setColor(Color.yellow);
            } else if (i < 31) {
                this.blockList.add(new Block(
                        new Point((770 - BLOCK_WIDTH - ((i - 22) * BLOCK_WIDTH)),
                                FIRST_ROW_Y - (3 * BLOCK_HEIGHT))));
                blockList.get(i).setColor(Color.cyan);
            } else {
                this.blockList.add(new Block(
                        new Point((770 - BLOCK_WIDTH - ((i - 31) * BLOCK_WIDTH)),
                                FIRST_ROW_Y - (4 * BLOCK_HEIGHT))));
                blockList.get(i).setColor(Color.pink);
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
