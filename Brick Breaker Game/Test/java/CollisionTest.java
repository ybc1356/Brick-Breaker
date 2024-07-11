import GameMaintenance.GameEnvironment;
import GameElements.Ball;
import GameElements.Block;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

public class CollisionTest {
    private final static  int TOP = 0;
    private final static int LEFT = 1;
    private final static int BOTTOM = 2;
    private final static int RIGHT = 3;
    public static void main(String[] args) {
        Point center = new Point(400, 400);
        Point upperLeft1 = new Point(200, 200);
        Point upperLeft2 = new Point(300, 200);
        Point upperLeft3 = new Point(400, 200);
        Point topRight = new Point(750, 600);
        Point topLeft = new Point(0, 600);
        Point bottomLeft = new Point(0, 50);
        Ball ball = new Ball(center, 3, Color.black);
        ball.setVelocity(4,4);
        Block[] borders = new Block[4];
        borders[TOP] = new Block(new Rectangle(topLeft, 800, 50));
        borders[LEFT] = new Block(new Rectangle(topLeft, 50, 600));
        borders[BOTTOM] = new Block(new Rectangle(bottomLeft, 800, 50));
        borders[RIGHT] = new Block(new Rectangle(topRight, 50, 600));
        Block block1 = new Block(new Rectangle(upperLeft1, 100, 100));
        block1.setColor(Color.red);
        Block block2 = new Block(new Rectangle(upperLeft2, 100, 100));
        block2.setColor(Color.green);
        Block block3 = new Block(new Rectangle(upperLeft3, 100, 100));
        block3.setColor(Color.yellow);
        GameEnvironment gameEnvironment = new GameEnvironment();
        gameEnvironment.addCollidable(block1);
        gameEnvironment.addCollidable(block2);
        gameEnvironment.addCollidable(block3);
        gameEnvironment.addCollidable( borders[TOP]);
        gameEnvironment.addCollidable( borders[BOTTOM]);
        gameEnvironment.addCollidable( borders[LEFT]);
        gameEnvironment.addCollidable( borders[RIGHT]);
        borders[TOP].setColor(Color.orange);
        borders[LEFT].setColor(Color.orange);
        borders[RIGHT].setColor(Color.orange);
        borders[BOTTOM].setColor(Color.orange);

        ball.setGameEnvironment(gameEnvironment);
        GUI gui = new GUI("Collision Test", 800, 600);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            ball.drawOn(surface);
            block1.drawOn(surface);
            block2.drawOn(surface);
            block3.drawOn(surface);
            borders[TOP].drawOn(surface);
            borders[LEFT].drawOn(surface);
            borders[RIGHT].drawOn(surface);
            borders[BOTTOM].drawOn(surface);
            ball.moveOneStep();
            gui.show(surface);
            sleeper.sleepFor(40);
        }
    }
}
