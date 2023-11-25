package main_module.model.entities;

import javafx.scene.image.Image;
import main_module.model.enums.Direction;
import main_module.model.enums.SideEffect;
import main_module.model.util.Vector;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an enemy character in the game.
 * Extends the {@link Character} class and implements the {@link ICollide} interface.
 */
public class Enemy extends Character implements ICollide {

    public static int ENEMY_HP = 1;
    public Set<Direction> directionsToGo;

    public static double ENEMY_SPEED_X = 2.5;
    public static double ENEMY_SPEED_Y = 2.5;

    public Ia ia;

    /**
     * Creates a new instance of the Enemy class.
     *
     * @param pos The initial position of the enemy.
     * @param hp  The initial health points of the enemy.
     */
    public Enemy(Vector pos, int hp) {
        super(pos, hp);
        hp = ENEMY_HP;
        super.width = Tile.SIZE;
        super.height = Tile.SIZE;
        super.currentImage = new Image(((System.getProperty("user.dir") + "/src/main/resources/main_module/animations/character/enemies/enemy.png")), false);
        directionsToGo = new HashSet<>(5);
        ia = new Ia(this);
    }

    /**
     * Updates the state of the enemy.
     *
     * @param nonDestroyableTilesRepresentation The representation of non-destroyable tiles in the game.
     * @param destroyableTilesRepresentation    The representation of destroyable tiles in the game.
     * @param player                           The player character in the game.
     */
    public void update(int[][] nonDestroyableTilesRepresentation, int[][] destroyableTilesRepresentation, Player player,boolean adjacency,boolean directed) {
        ia.update(nonDestroyableTilesRepresentation, destroyableTilesRepresentation, player,adjacency,directed);
        onMove(this.collisionDirectionSet);
        collisionDirectionSet.clear();
        processSideEffects();
        sideEffects.clear();
    }

    /**
     * Handles the movement of the enemy based on the available directions.
     *
     * @param collidingSet The set of directions in which the enemy is colliding with other entities.
     */
    public void onMove(Set<Direction> collidingSet) {
        if (directionsToGo.contains(Direction.UP)) {
            if (!collidingSet.contains(Direction.UP)) {
                pos.setY(pos.getY() - ENEMY_SPEED_Y);
            }
        }
        if (directionsToGo.contains(Direction.DOWN)) {
            if (!collidingSet.contains(Direction.DOWN)) {
                pos.setY(pos.getY() + ENEMY_SPEED_Y);
            }
        }
        if (directionsToGo.contains(Direction.LEFT)) {
            if (!collidingSet.contains(Direction.LEFT)) {
                pos.setX(pos.getX() - ENEMY_SPEED_X);
            }
        }
        if (directionsToGo.contains(Direction.RIGHT)) {
            if (!collidingSet.contains(Direction.RIGHT)) {
                pos.setX(pos.getX() + ENEMY_SPEED_X);
            }
        }
    }

    /**
     * Gets the set of directions the enemy intends to go.
     *
     * @return The set of directions to go.
     */
    public Set<Direction> getDirectionsToGo() {
        return directionsToGo;
    }

    /**
     * Gets the side effect associated with the enemy.
     *
     * @return The side effect of the enemy.
     */
    @Override
    public SideEffect getSideEffect() {
        return SideEffect.ENEMY_DAMAGE;
    }
}
