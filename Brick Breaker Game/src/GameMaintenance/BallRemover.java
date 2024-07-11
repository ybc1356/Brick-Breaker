// 345184295 Yonatan Benchabbat
package GameMaintenance;

import GameElements.Ball;
import GameElements.Block;
import Levels.GameLevel;
/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 13
 */
public class BallRemover implements HitListener {
    private final int SINGLE_BALL = 1;
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * Constructor for ball remover.
     * @param game game being managed by ball remover
     * @param remainingBalls remaining balls in the game.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(SINGLE_BALL);
    }
}
