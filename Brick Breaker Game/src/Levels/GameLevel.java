// 345184295 Yonatan Benchabbat
package Levels;

import CollisionDetection.Collidable;
import CollisionDetection.Velocity;
import GameElements.Ball;
import GameElements.Block;
import GameElements.Paddle;
import GameElements.Sprite;
import GameMaintenance.*;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * @author Yonatan Benchababt
 * @version 3.0
 * @since 2023 -06- 25
 */
public class GameLevel implements Animation {
    private final int X_OF_FIRST_LETTER = 550;
    private final int Y_OF_FIRST_LETTER = 20;
    private final int STARTING_X = 400;
    private final int STARTING_Y = 500;
    private final int BALL_SIZE = 4;
    private final int MINIMUM = 0;
    private final int VERTICAL_BORDER_WIDTH = 30;
    private final int VERTICAL_BORDER_HEIGHT = 600;
    private final int HORIZONTAL_BORDER_HEIGHT = 30;
    private final int HORIZONTAL_BORDER_WIDTH = 800;
    private final int GAME_WIDTH = 800;
    private final int GAME_HEIGHT = 600;
    private final int NUMBER_OF_BORDERS = 4;
    private final int TOP = 0;
    private final int LEFT = 1;
    private final int BOTTOM = 2;
    private final int RIGHT = 3;
    private final int NO_MORE_BLOCKS = 0;
    private final int NO_MORE_BALLS = 0;
    private final int ALL_BLOCKS_CLEARED = 100;
    private final int FONT_SIZE = 17;
    private final String PAUSE_BUTTON = "p";
    private final BallRemover ballRemover;
    private final BlockRemover blockRemover;
    private final Counter remainingBalls;
    private final Counter remainingBlocks;
    private final SpriteCollection sprites;
    private final SpriteCollection remainingSprites;
    private final GameEnvironment environment;
    private final GameEnvironment currentEnvironment;
    private final Counter score;
    private final ScoreTrackingListener scoreTrackingListener;
    private final AnimationRunner animationRunner;
    private boolean running;
    private final KeyboardSensor keyboardSensor;
    private final LevelInformation levelInformation;
    /**
     * Contructor for GameLevel class.
     * @param information level information of current level.
     */
    public GameLevel(LevelInformation information, AnimationRunner ar) {
        this.levelInformation = information;
        this.remainingBalls = new Counter(levelInformation.numberOfBalls());
        this.remainingBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        this.sprites = new SpriteCollection();
        this.remainingSprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.currentEnvironment = new GameEnvironment();
        this.blockRemover = new BlockRemover(this, this.remainingBlocks);
        this.ballRemover = new BallRemover(this, this.remainingBalls);
        this.score = new Counter();
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.running = true;
        this.animationRunner = ar;
        this.keyboardSensor = animationRunner.getGui().getKeyboardSensor();
    }

    /**
     * initialize initializes the game.
     */
    public void initialize() {
        setBorders();
        getBalls();
        getPaddle();
        getBlocks();
        this.addSprite(new ScoreIndicator(this.score));
    }

    /**
     * setScore gives the ability to set the score for starting a level.
     * @param score score being set.
     */
    public void setScore(int score) {
        this.score.setValue(score);
    }

    /**
     * get score returns the score.
     * @return return the score of the game.
     */
    public int getScore() {
        return this.score.getValue();
    }
    /**
     * setBorders sets the borders of the game.
     */
    public void setBorders() {
        Point upperLeftCorner = new Point(MINIMUM, MINIMUM);
        Point bottomLeftCorner = new Point(MINIMUM,
                GAME_HEIGHT - VERTICAL_BORDER_WIDTH);
        Point upperRightCorner = new Geometry.Point(
                GAME_WIDTH - VERTICAL_BORDER_WIDTH, MINIMUM);
        Point deathZonePoint = new Point(bottomLeftCorner.getX(),
                bottomLeftCorner.getY() + HORIZONTAL_BORDER_HEIGHT);
        Rectangle[] sides = new Rectangle[NUMBER_OF_BORDERS];
        sides[LEFT] = new Rectangle(upperLeftCorner, VERTICAL_BORDER_WIDTH,
                VERTICAL_BORDER_HEIGHT);
        sides[BOTTOM] = new Rectangle(deathZonePoint,
               HORIZONTAL_BORDER_WIDTH, HORIZONTAL_BORDER_HEIGHT);
        sides[TOP] = new Rectangle(upperLeftCorner, HORIZONTAL_BORDER_WIDTH,
               HORIZONTAL_BORDER_HEIGHT);
        sides[RIGHT] = new Rectangle(upperRightCorner, VERTICAL_BORDER_WIDTH,
               VERTICAL_BORDER_HEIGHT);
        Block[] borders = new Block[NUMBER_OF_BORDERS];
        borders[LEFT] = new Block(sides[LEFT]);
        borders[BOTTOM] = new Block(sides[BOTTOM]);
        borders[BOTTOM].addHitListener(ballRemover);
        borders[TOP] = new Block(sides[TOP]);
        borders[RIGHT] = new Block(sides[RIGHT]);
        for (Block border: borders) {
            border.setColor(Color.gray);
            border.addToGame(this);
        }
    }
    /**
     * getBalls initializes the balls in the game.
     */
    public void getBalls() {
        int numberOfBalls = this.levelInformation.numberOfBalls();
        Geometry.Point[] centers =
                new Geometry.Point[numberOfBalls];
        Ball[] balls = new Ball[numberOfBalls];
        List<Velocity> velocities =
                this.levelInformation.initialBallVelocities();
        for (int i = 0; i < numberOfBalls; i++) {
            centers[i] = new Geometry.Point(STARTING_X,
                    STARTING_Y);
            balls[i] = new Ball(centers[i], BALL_SIZE, Color.black);
            balls[i].setVelocity(velocities.get(i));
            balls[i].setGameEnvironment(this.environment);
            balls[i].addToGame(this);
        }
    }

    /**
     * getPaddle initializes a paddle for the game.
     */
    public void getPaddle() {
        Paddle paddle = new Paddle(levelInformation.paddleSpeed(),
                levelInformation.paddleWidth());
        paddle.setKeyboardSensor(this.keyboardSensor);
        paddle.addToGame(this);
    }
    /**
     * getBlocks initializes the blocks in the game.
     */
    public void getBlocks() {
        List<Block> blocks = this.levelInformation.blocks();
        for (Block block: blocks) {
            block.addToGame(this);
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(blockRemover);
        }
    }
    /**
     * addCollidable adds a sprite to the game.
     * @param collidable sprite being added to game.
     */
    public void addCollidable(Collidable collidable) {
        this.environment.addCollidable(collidable);
    }
    /**
     * addSprite adds a sprite to the game.
     * @param sprite sprite being added to game.
     */
    public void addSprite(Sprite sprite) {
        this.sprites.addSprite(sprite);
    }
    /**
     * removeCollidable removes a sprite from the game.
     * @param collidable sprite being removed.
     */
    public void removeCollidable(Collidable collidable) {
        this.currentEnvironment.removeCollidable(collidable);
    }
    /**
     * removeSprite removes a sprite from the game.
     * @param sprite sprite being removed.
     */
    public void removeSprite(Sprite sprite) {
        this.remainingSprites.removeSprite(sprite);
    }

    /**
     * run manages the running and closing the game.
     */
    public void run() {
        this.animationRunner.run(this);
    }
    public int remainingBalls() {
        return this.remainingBalls.getValue();
    }
    @Override
    public void doOneFrame(DrawSurface surface) {
        if (this.keyboardSensor.isPressed(PAUSE_BUTTON)) {
            this.animationRunner.run(new PauseScreen(this.keyboardSensor));
        }
        Sprite background = levelInformation.getBackground();
        background.drawOn(surface);
        this.currentEnvironment.copy(this.environment);
        this.remainingSprites.copy(this.sprites);
        this.sprites.drawAllOn(surface);
        surface.drawText(X_OF_FIRST_LETTER, Y_OF_FIRST_LETTER,
                "Level Name: " + levelInformation.levelName(), FONT_SIZE);
        this.sprites.notifyAllTimePassed();
        this.environment.copy(currentEnvironment);
        this.sprites.copy(remainingSprites);
        if (this.remainingBlocks.getValue() == NO_MORE_BLOCKS
                || this.remainingBalls.getValue() == NO_MORE_BALLS) {
            this.running = false;
        }
        if (this.remainingBlocks.getValue() == NO_MORE_BLOCKS) {
            this.scoreTrackingListener.increase(ALL_BLOCKS_CLEARED);
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}