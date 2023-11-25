package main_module.model.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import main_module.model.enums.Direction;
import main_module.model.util.Vector;
import main_module.model.managers.BombManager;

import java.util.Objects;
import java.util.Set;

/**
 * Represents the player character in the game.
 */
public class Player extends Character {

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean cPressed;
    public static final double speedX = 5;
    public static final double speedY = 5;

    private BombManager bombManager;

    private int bombs = 20;

    private Image hearthImage;

    private int kills = 0;

    private boolean bombsIncreased = false;

    private Image[][] playerMovement;

    private int frame;

    private int frameCounter = 0;

    private static final int FRAME_DELAY = 4;

    /**
     * Creates a new instance of the Player class.
     *
     * @param pos         The initial position of the player.
     * @param hp          The initial health points of the player.
     * @param bombManager The bomb manager associated with the player.
     */
    public Player(Vector pos, int hp, BombManager bombManager) {
        super(pos, hp);
        super.width = Tile.SIZE;
        super.height = Tile.SIZE;
        super.currentImage = new Image(Objects.requireNonNull(getClass().getResource("/animations/player/standardPos.png")).toString(), false);
        this.bombManager = bombManager;
        this.hearthImage = new Image(Objects.requireNonNull(getClass().getResource("/animations/tiles/Hearth.png")).toString(), false);
        initPlayerMovement();
    }

    /**
     * Initializes the player movement animations.
     */
    public void initPlayerMovement() {
        int numDirections = 4; // UP, DOWN, LEFT, RIGHT
        int numFrames = 4;

        playerMovement = new Image[numDirections][numFrames];

        for (int direction = 0; direction < numDirections; direction++) {
            for (int frame = 0; frame < numFrames; frame++) {
                String directionName = getDirectionName(direction);
                String imageName = directionName + "_" + (frame + 1) + ".png";
                playerMovement[direction][frame] = new Image(getClass().getResource("/animations/player/BombermanMovement/" + directionName.toUpperCase() + "/" + imageName).toString(), false);
            }
        }
    }

    /**
     * Gets the name of the direction based on the index.
     *
     * @param direction The index representing the direction.
     * @return The name of the direction.
     */
    private String getDirectionName(int direction) {
        switch (direction) {
            case 0:
                return "up";
            case 1:
                return "down";
            case 2:
                return "left";
            case 3:
                return "right";
        }
        return "";
    }

    /**
     * Handles player movement based on the pressed keys.
     *
     * @param collidingSet The set of directions in which the player is colliding with other entities.
     */
    public void onMove(Set<Direction> collidingSet) {
        int directionIndex = -1;

        if (upPressed) {
            if (!collidingSet.contains(Direction.UP)) {
                pos.setY(pos.getY() - speedY);
                directionIndex = 0; // UP
            }
        } else if (downPressed) {
            if (!collidingSet.contains(Direction.DOWN)) {
                pos.setY(pos.getY() + speedY);
                directionIndex = 1; // DOWN
            }
        }
        if (leftPressed) {
            if (!collidingSet.contains(Direction.LEFT)) {
                pos.setX(pos.getX() - speedX);
                directionIndex = 2; // LEFT
            }
        } else if (rightPressed) {
            if (!collidingSet.contains(Direction.RIGHT)) {
                pos.setX(pos.getX() + speedX);
                directionIndex = 3; // RIGHT
            }
        }


        if (directionIndex != -1) {
            if (frameCounter % FRAME_DELAY == 0) {
                frame = (frame + 1) % 4; // Change frame (0-3)
                currentImage = playerMovement[directionIndex][frame];
            }
            frameCounter++;
        } else {
            frameCounter = 0; // Reset the counter if there is no movement
            currentImage = new Image(getClass().getResource("/animations/player/standardPos.png").toString(), false);
        }
    }

    /**
     * Sets the pressed state of keys based on key events.
     *
     * @param event The key event.
     */
    public void setOnKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> {
                state = 1;
                upPressed = true;
            }
            case DOWN -> {
                state = 1;
                downPressed = true;
            }
            case LEFT -> {
                state = 1;
                leftPressed = true;
            }
            case RIGHT -> {
                state = 1;
                rightPressed = true;
            }
            case C -> {
                cPressed = true;
                if (bombs != 0 && bombManager.getBombTimerCheck()) {
                    bombManager.addBomb(this);
                    bombs--;
                }
            }
        }
    }

    /**
     * Sets the released state of keys based on key events.
     *
     * @param event The key event.
     */
    public void setOnKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> {
                state = 0;
                upPressed = false;
            }
            case DOWN -> {
                state = 0;
                downPressed = false;
            }
            case LEFT -> {
                state = 0;
                leftPressed = false;
            }
            case RIGHT -> {
                state = 0;
                rightPressed = false;
            }
            case C -> {
                cPressed = false;
            }
        }
    }

    /**
     * Gets the image representing the player's health.
     *
     * @return The image representing the player's health.
     */
    public Image getHearthImage() {
        return hearthImage;
    }

    /**
     * Gets the number of bombs the player currently has.
     *
     * @return The number of bombs the player currently has.
     */
    public int getBombs() {
        return bombs;
    }

    /**
     * Increases the number of bombs the player has.
     */
    public void addBomb() {
        bombs++;
    }

    /**
     * Gets the number of kills the player has.
     *
     * @return The number of kills the player has.
     */
    public int getKills() {
        return kills;
    }

    /**
     * Adds the specified number of kills to the player.
     *
     * @param kills The number of kills to add.
     */
    public void addKills(int kills) {
        this.kills += kills;
    }

    /**
     * Checks if the player's bomb count has been increased.
     *
     * @return True if the bomb count has been increased, false otherwise.
     */
    public boolean isBombsIncreased() {
        return bombsIncreased;
    }

    /**
     * Sets whether the player's bomb count has been increased.
     *
     * @param bombsIncreased True to indicate that the bomb count has been increased, false otherwise.
     */
    public void setBombsIncreased(boolean bombsIncreased) {
        this.bombsIncreased = bombsIncreased;
    }

    public void setEndGameImage() {
        this.currentImage = new Image(getClass().getResource("/main_module/animations/player/EndGame.png").toString(), false);
    }
}
