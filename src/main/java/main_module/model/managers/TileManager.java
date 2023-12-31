package main_module.model.managers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main_module.model.entities.CollisionTile;
import main_module.model.entities.Entity;
import main_module.model.entities.ICollide;
import main_module.model.entities.Tile;
import main_module.model.enums.SideEffect;
import main_module.model.enums.TileType;
import main_module.model.util.Vector;

import java.util.Objects;

/**
 * The {@code TileManager} class manages aspects related to tiles in the game, including the generation of collision
 * and non-collision tiles, as well as handling different tile images based on the specified {@link TileType}.
 */
public class TileManager {
    /**
     * Graphics context for rendering tiles.
     */
    private GraphicsContext gc;

    /**
     * Image representing a brick tile.
     */
    private Image brickImage;

    /**
     * Image representing a grass tile.
     */
    private Image grassImage;

    /**
     * Image representing a destroyable tile.
     */
    private Image destroyableImage;

    /**
     * Image representing a bomb tile.
     */
    private Image bombImage;

    /**
     * Image representing a bomb damage tile.
     */
    private Image bombDamageImage;

    private Image portalImage;

    /**
     * Constructs a {@code TileManager} and initializes the required resources, including various tile images.
     */
    public TileManager() {
        brickImage = new Image(Objects.requireNonNull(getClass().getResource("/animations/tiles/brick.png")).toString(), false);
        grassImage = new Image(Objects.requireNonNull(getClass().getResource("/animations/tiles/grass.png")).toString(), false);
        destroyableImage = new Image(Objects.requireNonNull(getClass().getResource("/animations/tiles/destroyable.png")).toString(), false);
        bombImage = new Image(Objects.requireNonNull(getClass().getResource("/animations/tiles/bomb.png")).toString(), false);
        bombDamageImage = new Image(Objects.requireNonNull(getClass().getResource("/animations/tiles/bombDamage.png")).toString(), false);
        portalImage = new Image(Objects.requireNonNull(getClass().getResource("/animations/portal.png")).toString(), false);
    }


    /**
     * Generates a collision tile based on the specified position, {@link TileType}, and side effect.
     *
     * @param pos        The position of the generated collision tile.
     * @param type       The type of collision tile to generate.
     * @param sideEffect The side effect associated with the collision tile.
     * @return An instance of the generated collision tile.
     */
    public ICollide generateCollisionTile(Vector pos, TileType type, SideEffect sideEffect) {
        return new CollisionTile(pos, getImage(type), sideEffect);
    }

    /**
     * Generates a non-collision tile based on the specified position and {@link TileType}.
     *
     * @param pos  The position of the generated non-collision tile.
     * @param type The type of non-collision tile to generate.
     * @return An instance of the generated non-collision tile.
     */
    public Entity generateNonCollisionTile(Vector pos, TileType type) {
        return new Tile(pos, getImage(type));
    }

    /**
     * Retrieves the appropriate image for the specified {@link TileType}.
     *
     * @param tileType The type of tile for which to retrieve the image.
     * @return The image associated with the specified tile type.
     */
    public Image getImage(TileType tileType) {
        Image image = null;
        switch (tileType) {
            case BRICK -> image = brickImage;
            case GRASS -> image = grassImage;
            case DESTROYABLE -> image = destroyableImage;
            case BOMB -> image = bombImage;
            case BOMB_DAMAGE -> image = bombDamageImage;
            case PORTAL -> image = portalImage;
            default -> image = grassImage;
        }
        return image;
    }
}

