package main_module.model.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main_module.model.util.Vector;

/**
 * Represents a basic entity in the game world.
 * This class serves as the base class for other game entities.
 */
public abstract class Entity {

    /**
     * The graphics context for rendering the entity.
     */
    protected GraphicsContext gc;

    /**
     * The position vector of the entity in the game world.
     */
    protected Vector pos;

    /**
     * The width of the entity.
     */
    protected double width;

    /**
     * The height of the entity.
     */
    protected double height;

    /**
     * The current image representing the entity.
     */
    public Image currentImage;

    /**
     * Creates a new instance of the Entity class with the specified position.
     *
     * @param pos The initial position of the entity.
     */
    protected Entity(Vector pos) {
        this.pos = pos;
    }

    /**
     * Gets the position vector of the entity.
     *
     * @return The position vector.
     */
    public Vector getPos() {
        return pos;
    }

    /**
     * Gets the width of the entity.
     *
     * @return The width of the entity.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the height of the entity.
     *
     * @return The height of the entity.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the current image representing the entity.
     *
     * @return The current image.
     */
    public Image getCurrentImage() {
        return currentImage;
    }

    /**
     * Gets the middle point of the entity.
     *
     * @return The middle point vector.
     */
    public Vector getMiddlePoint() {
        double midX = getPos().getX() + width / 2;
        double midY = getPos().getY() + height / 2;
        return new Vector(midX, midY);
    }

    /**
     * Sets the position of the entity.
     *
     * @param pos The new position vector.
     */
    public void setPos(Vector pos) {
        this.pos = pos;
    }
}
