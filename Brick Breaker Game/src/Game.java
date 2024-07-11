// 345184295 Yonatan Benchabbat
import Levels.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Yonatan Benchababt
 * @version 3.0
 * @since 2023 -06- 25
 */
public class Game {
    /**
     * main runs the game.
     * @param args arguments being passed to the game
     */
    public static void main(String[] args) {
        List<LevelInformation> list = new ArrayList<>();
        for (String arg : args) {
            switch (arg) {
                case "1" -> list.add(new LevelOne());
                case "2" -> list.add(new LevelTwo());
                case "3" -> list.add(new LevelThree());
                default -> {
                }
            }
        }
        if (list.isEmpty()) {
            list.add(new LevelOne());
            list.add(new LevelTwo());
            list.add(new LevelThree());
        }
        GameFlow gameFlow = new GameFlow(list);
        gameFlow.initialize();
        gameFlow.run();
    }
}
