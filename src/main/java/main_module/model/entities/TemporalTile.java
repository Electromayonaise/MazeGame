package main_module.model.entities;

import javafx.scene.image.Image;
import main_module.model.enums.SideEffect;
import main_module.model.util.Timer;
import main_module.model.util.Vector;

/**
 * Represents a temporal tile in the game, which has a limited duration.
 */
public class TemporalTile extends CollisionTile {

    private long time;

    private Timer timer;

    /**
     * Creates a new instance of the TemporalTile class.
     *
     * @param pos        The position of the temporal tile.
     * @param image      The image representing the temporal tile.
     * @param sideEffect The side effect associated with the temporal tile.
     * @param time       The duration of the temporal tile.
     */
    public TemporalTile(Vector pos, Image image, SideEffect sideEffect, long time) {
        super(pos, image, sideEffect);
        this.time = time;
        timer = new Timer(time);
        timer.reset();
    }

    /**
     * Checks if the temporal tile's time has elapsed.
     *
     * @return True if the time has elapsed, false otherwise.
     */
    public boolean check() {
        return timer.check();
    }
}
