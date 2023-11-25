package main_module.model.entities;

/**
 * The {@code IUpdate} interface represents entities that can be updated, indicating that they may have
 * behavior or state changes over time. Implementing classes should provide an implementation for the
 * {@link #update()} method to define the update logic.
 */
public interface IUpdate {
    /**
     * Updates the entity, applying any changes to its behavior or state.
     */
    void update();
}
