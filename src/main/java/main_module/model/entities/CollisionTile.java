package main_module.model.entities;

import javafx.scene.image.Image;
import main_module.model.enums.SideEffect;
import main_module.model.util.Vector;

/**
 * Represents a collision tile in the game.
 * Extends the {@link Tile} class and implements the {@link ICollide} interface.
 */
public class CollisionTile extends Tile implements ICollide {

    protected SideEffect sideEffect;

    /**
     * Creates a new instance of the CollisionTile class.
     *
     * @param pos        The position of the collision tile.
     * @param image      The image representing the collision tile.
     * @param sideEffect The side effect associated with the collision tile.
     */
    public CollisionTile(Vector pos, Image image, SideEffect sideEffect) {
        super(pos, image);
        this.sideEffect = sideEffect;
    }

    /**
     * Gets the side effect associated with the collision tile.
     *
     * @return The side effect of the collision tile.
     */
    @Override
    public SideEffect getSideEffect() {
        return sideEffect;
    }
}
