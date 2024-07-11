//345184295 Yonatan Benchabbat
package GameMaintenance;

import GameElements.Ball;

/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 10
 */
public interface HitNotifier {
    /**
     * addHitListener adds a hit listener.
     * @param hitListener hit listener being added.
     */
    void addHitListener(HitListener hitListener);
    /**
     * removeHitListener adds a hit listener.
     * @param hitListener hit listener being removed.
     */
    void removeHitListener(HitListener hitListener);

    /**
     * notifyHit notifies that a Hit Listener. has been hit by a given hitter.
     * @param hitter hitter doing the hitting.
     */
    void notifyHit(Ball hitter);
}
