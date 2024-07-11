// 345184295 Yonatan Benchabbat
package Levels;

import java.util.List;
import CollisionDetection.Velocity;
import GameElements.Sprite;
import GameElements.Block;
/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 25
 */
public interface LevelInformation {
    /**
     * numberOfBalls denotes the number of balls in a level.
     * @return the number of balls
     */
    int numberOfBalls();
    /**
     * intialBallVelocities is the intial velocity of each ball in the level.
      * @return a list with the velocities of each ball.
     */
    List<Velocity> initialBallVelocities();
    /**
     * paddleSpeed is the starting speed of the paddle for each level.
     * @return speed of paddle.
     */
    int paddleSpeed();
    /**
     * paddleWidth is the width of the paddle for the level.
     * @return the width of the paddle.
     */
    int paddleWidth();
    /**
     * levelName returns the name of the level.
     * @return String containing the name of the level.
     */
    String levelName();
    /**
     * getBackground gets the background for the level.
     * @return Sprite that is the background of that level.
     */
    Sprite getBackground();
    /**
     * blocks returns a list with the blocks of the level.
     * @return a list containing all blocks in the level.
     */
    List<Block> blocks();
    /**
     * numberOfBlocksToRemove returns the number of blocks before the level
     * is considered cleared.
     * @return integer of blocks that need to be removed for level to be
     * considered cleared.
     */
    int numberOfBlocksToRemove();
}
