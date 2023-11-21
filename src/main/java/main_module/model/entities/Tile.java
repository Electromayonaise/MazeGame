package main_module.model.entities;

import javafx.scene.image.Image;
import main_module.model.util.Vector;

/**
 * Represents a basic tile in the game.
 */
public class Tile extends Entity {

    /**
     * The original size of the tile.
     */
    public static final double originalTileSize = 16;

    /**
     * The resizing factor for the tile.
     */
    public static final double resize = 4;

    /**
     * The size of the tile after resizing.
     */
    public static final double SIZE = originalTileSize * resize;

    /**
     * Creates a new instance of the Tile class.
     *
     * @param pos   The position of the tile.
     * @param image The image representing the tile.
     */
    public Tile(Vector pos, Image image) {
        super(pos);
        this.currentImage = image;
        super.height = SIZE;
        super.width = SIZE;
    }
}
