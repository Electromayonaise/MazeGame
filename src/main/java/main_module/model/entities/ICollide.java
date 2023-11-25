package main_module.model.entities;


import main_module.model.enums.SideEffect;

/**
 * The {@code ICollide} interface represents entities that can cause collisions and have associated side effects.
 * Implementing classes should provide an implementation for the {@link #getSideEffect()} method to specify the
 * side effect of the collision.
 */
public interface ICollide {
    /**
     * Gets the side effect associated with the collision.
     *
     * @return The side effect of the collision.
     */
    SideEffect getSideEffect();
}

