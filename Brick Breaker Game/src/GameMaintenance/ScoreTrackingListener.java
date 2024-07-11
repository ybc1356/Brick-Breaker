//345184295 Yonatan Benchabbat
package GameMaintenance;

import GameElements.Ball;
import GameElements.Block;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 10
 */
public class ScoreTrackingListener implements  HitListener {
    private final int BLOCK_HIT = 5;
    private Counter currentScore;

    /**
     * ScoreTrackingListener constructor.
     * @param scoreCounter score counter for score tracker to update.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * increase increases the current score by a  specific number.
     * @param number number being added to current score.
     */
    public void increase(int number) {
            this.currentScore.increase(number);
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.currentScore.increase(BLOCK_HIT);
    }
}
