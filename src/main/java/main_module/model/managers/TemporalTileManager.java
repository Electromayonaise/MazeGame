package main_module.model.managers;

import javafx.scene.image.Image;
import main_module.model.entities.Entity;
import main_module.model.entities.TemporalTile;
import main_module.model.enums.SideEffect;
import main_module.model.enums.TileType;
import main_module.model.util.Vector;

/**
 * The {@code TemporalTileManager} class is responsible for generating temporal tiles with or without collision.
 * It provides methods to create instances of {@link TemporalTile} entities based on the specified {@link TileType},
 * side effects, and a specified time duration.
 */
public class TemporalTileManager {
    /**
     * Image representing the bomb damage appearance for temporal tiles.
     */
    protected Image bombDamageImage;

    /**
     * Constructs a {@code TemporalTileManager} and initializes the resources, including the bomb damage image.
     */
    public TemporalTileManager() {
        loadResources();
    }

    /**
     * Generates a temporal tile with collision based on the specified position, {@link TileType}, side effect, and time duration.
     *
     * @param pos        The position of the generated temporal tile.
     * @param tileType   The type of temporal tile to generate.
     * @param sideEffect The side effect associated with the temporal tile.
     * @param time       The time duration for which the temporal tile remains active.
     * @return An instance of the generated temporal tile with collision.
     */
    public Entity generateTemporalTileWithCollision(Vector pos, TileType tileType, SideEffect sideEffect, long time) {
        Image image = selectImage(tileType);
        return new TemporalTile(pos, image, sideEffect, time);
    }

    /**
     * Generates a temporal tile without collision based on the specified position, {@link TileType}, and time duration.
     *
     * @param pos      The position of the generated temporal tile.
     * @param tileType The type of temporal tile to generate.
     * @param time     The time duration for which the temporal tile remains active.
     * @return An instance of the generated temporal tile without collision.
     */
    public Entity generateTemporalTileWithNoCollision(Vector pos, TileType tileType, long time) {
        Image image = selectImage(tileType);
        return new TemporalTile(pos, image, SideEffect.NONE, time);
    }

    /**
     * Selects the image for the specified {@link TileType}.
     *
     * @param tileType The type of tile for which to select the image.
     * @return The image associated with the specified tile type.
     */
    public Image selectImage(TileType tileType) {
        Image image = bombDamageImage;
        switch (tileType) {
            case BOMB_DAMAGE -> {
                image = bombDamageImage;
            }
        }
        return image;
    }

    /**
     * Loads the required resources, including the bomb damage image.
     */
    private void loadResources() {
        bombDamageImage = new Image(((System.getProperty("user.dir") + "/src/main/resources/animations/tiles/explotionGif.gif")), false);
    }
}

