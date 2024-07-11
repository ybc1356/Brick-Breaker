// 345184295 Yonatan Benchabbat
package GameMaintenance;

import GameElements.Ball;
import GameElements.Block;
import Levels.GameLevel;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 10
 */
public class BlockRemover implements HitListener {
    private final int SINGLE_BLOCK = 1;
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * Constructor for block remover.
     * @param game game being managed by block remover
     * @param remainingBlocks remaining blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        this.remainingBlocks.decrease(SINGLE_BLOCK);
    }
}
