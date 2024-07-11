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
public class LevelOne implements LevelInformation{
    private int numberOfBalls;
    private List<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Block background;
    private List<Block> blockList;
    private int numberOfBlocks;
    public LevelOne() {
        this.numberOfBalls = 1;
        this.ballVelocities = new ArrayList<>();
        this.ballVelocities.add(new Velocity( 0.03, -1.5));
        this.paddleSpeed = 20;
        this.paddleWidth = 100;
        this.levelName = "Direct Hit";
        this.background = new Block(new Rectangle(new Point(0, 0),
                1000, 1000));
        background.setColor(Color.green);
        this.blockList = new ArrayList<>();
        this.blockList.add(new Block(new Point(380, 200)));
        blockList.get(0).setColor(Color.blue);
        this.numberOfBlocks = 1;
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
