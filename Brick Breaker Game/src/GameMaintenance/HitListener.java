//345184295 Yonatan Benchabbat
package GameMaintenance;
import GameElements.Ball;
import GameElements.Block;
/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 10
 */
public interface HitListener {
    /**
     * hitEvent is event of a hit.
     * @param beingHit Block being hit.
     * @param hitter Ball hitting the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
