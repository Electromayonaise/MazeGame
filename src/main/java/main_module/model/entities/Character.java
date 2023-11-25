package main_module.model.entities;

import javafx.scene.image.Image;
import main_module.model.enums.Direction;
import main_module.model.enums.SideEffect;
import main_module.model.util.Timer;
import main_module.model.util.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an abstract character in the game.
 * Extends the {@link Entity} class and implements the {@link IUpdate} interface.
 */
public abstract class Character extends Entity implements IUpdate {
    protected int hp;
    protected int frame;
    protected ArrayList<Image> idle;
    protected ArrayList<Image> run;
    protected int state;
    protected Set<Direction> collisionDirectionSet;

    public static final long INVULNERABILITY_TIME = 3000;

    protected Timer invulnerabilityTimer;

    protected Set<SideEffect> sideEffects;

    /**
     * Creates a new instance of the Character class.
     *
     * @param pos The initial position of the character.
     * @param hp  The initial health points of the character.
     */
    public Character(Vector pos, int hp) {
        super(pos);
        this.hp = hp;
        this.invulnerabilityTimer = new Timer(INVULNERABILITY_TIME);
        this.collisionDirectionSet = new HashSet<>(5);
        this.sideEffects = new HashSet<>(5);
        invulnerabilityTimer.reset();
    }

    /**
     * Gets the health points of the character.
     *
     * @return The health points of the character.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets the health points of the character.
     *
     * @param hp The health points to set.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Callback method called when the character moves.
     *
     * @param collidingSet The set of directions in which the character is colliding.
     */
    protected abstract void onMove(Set<Direction> collidingSet);

    /**
     * Updates the state of the character.
     */
    @Override
    public void update() {
        onMove(collisionDirectionSet);
        processSideEffects();
        collisionDirectionSet.clear();
        sideEffects.clear();
    }

    /**
     * Gets the set of collision directions.
     *
     * @return The set of collision directions.
     */
    public Set<Direction> getCollisionDirectionSet() {
        return collisionDirectionSet;
    }

    /**
     * Processes the side effects of the character.
     */
    protected void processSideEffects() {
        if (sideEffects.contains(SideEffect.BOMB_DAMAGE)) {
            if (invulnerabilityTimer.check()) {
                processBombDamageSideEffect();
                invulnerabilityTimer.reset();
            }
        }
        if (sideEffects.contains(SideEffect.ENEMY_DAMAGE)) {
            if (invulnerabilityTimer.check()) {
                processEnemyDamage();
                invulnerabilityTimer.reset();
            }
        }
    }

    /**
     * Processes the bomb damage side effect.
     */
    protected void processBombDamageSideEffect() {
        --hp;
    }

    /**
     * Gets the set of side effects.
     *
     * @return The set of side effects.
     */
    public Set<SideEffect> getSideEffects() {
        return sideEffects;
    }

    /**
     * Processes the enemy damage.
     */
    public void processEnemyDamage() {
        --hp;
    }
}
