package main_module.model.entities;

import javafx.scene.image.Image;
import main_module.model.enums.SideEffect;
import main_module.model.util.Timer;
import main_module.model.util.Vector;

/**
 * Represents a bomb entity in the game.
 * Extends the {@link CollisionTile} class.
 */
public class Bomb extends CollisionTile {
    private Timer timer;
    public static final long bombTime = 3000;

    public int row, col;
    Entity[][] field;

    /**
     * Creates a new instance of the Bomb class.
     *
     * @param pos The position of the bomb.
     */
    public Bomb(Vector pos) {
        super(pos, loadImage(), SideEffect.OBSTRUCTION);
        timer = new Timer(bombTime);
        timer.reset();
    }

    /**
     * Loads the image for the bomb from the resources.
     *
     * @return The image of the bomb.
     */
    public static Image loadImage() {
        return new Image(((System.getProperty("user.dir") + "/src/main/resources/animations/tiles/bomb.png")), false);
    }

    /**
     * Checks if the bomb's timer has expired.
     *
     * @return True if the bomb's timer has expired, false otherwise.
     */
    public boolean checkBomb() {
        return timer.check();
    }
}
