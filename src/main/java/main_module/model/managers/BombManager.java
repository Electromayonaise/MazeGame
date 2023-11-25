package main_module.model.managers;

import main_module.BaseScreen;
import main_module.model.entities.Bomb;
import main_module.model.entities.Entity;
import main_module.model.entities.Player;
import main_module.model.entities.Tile;
import main_module.model.util.Timer;
import main_module.model.util.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * Manages the placement and detonation of bombs in the game.
 */
public class BombManager {

    /**
     * The tile manager associated with the screen.
     */
    protected TileManager tileManager;

    /**
     * The base screen where the bomb manager operates.
     */
    protected BaseScreen screen;

    /**
     * The list of bombs currently present in the game.
     */
    protected List<Entity> bombList;

    /**
     * Timer for bomb placement cooldown.
     */
    protected Timer bombTimer;

    /**
     * The damage manager responsible for handling bomb explosions and damage.
     */
    protected DamageManager damageManager;

    /**
     * The size of a bomb tile.
     */
    private static final double BOMB_SIZE = Tile.SIZE;

    /**
     * Creates a new instance of the BombManager class.
     *
     * @param screen The base screen where the bomb manager operates.
     */
    public BombManager(BaseScreen screen) {
        this.screen = screen;
        tileManager = screen.getTileManager();
        bombList = new LinkedList<>();
        bombTimer = new Timer(Bomb.bombTime);
        this.damageManager = new DamageManager(screen);
        bombTimer.reset();
    }

    /**
     * Manages the placement and detonation of bombs in the game.
     */
    public void manageBombs() {
        updateBombs();
        damageManager.manageBombDamage();
    }

    /**
     * Updates the state of bombs in the game.
     */
    public void updateBombs() {
        for (Entity bomb : bombList) {
            if (((Bomb) bomb).checkBomb()) {
                Vector cor = screen.fromVectorToMatrixCoordinate(bomb.getMiddlePoint());
                screen.removeEntityFromField((int) cor.getY(), (int) cor.getX(), 3);
                bombList.remove(bomb);
                damageManager.startExplosion(cor);
            }
        }
    }

    /**
     * Adds a bomb to the game at the player's current position.
     *
     * @param player The player entity.
     */
    public void addBomb(Player player) {
        if (bombTimer.check()) {
            putBombAtCenter(screen.fromVectorToMatrixCoordinate(player.getMiddlePoint()));
            bombTimer.reset();
        }
    }

    /**
     * Places a bomb at the center of the specified matrix coordinates.
     *
     * @param matrixCor The matrix coordinates where the bomb will be placed.
     */
    public void putBombAtCenter(Vector matrixCor) {
        int col = (int) (matrixCor.getX());
        int row = (int) (matrixCor.getY());
        Vector posOfBombAtCenter = calculateCoordinatesOfBombAtCenter(matrixCor);
        Entity bomb = new Bomb(posOfBombAtCenter);
        bombList.add(bomb);
        screen.addEntityInField(bomb, row, col, 3);
    }

    /**
     * Calculates the coordinates of the bomb at the center of the specified matrix coordinates.
     *
     * @param matrixCor The matrix coordinates.
     * @return The vector representing the bomb's position at the center.
     */
    public Vector calculateCoordinatesOfBombAtCenter(Vector matrixCor) {
        double tileCenterX = (matrixCor.getX()) * Tile.SIZE + (double) Tile.SIZE / 2;
        double tileCenterY = (matrixCor.getY()) * Tile.SIZE + (double) Tile.SIZE / 2;

        double bombX = tileCenterX - (double) BOMB_SIZE / 2;
        double bombY = tileCenterY - (double) BOMB_SIZE / 2;

        return new Vector(bombX, bombY);
    }

    /**
     * Gets the damage manager associated with the bomb manager.
     *
     * @return The damage manager.
     */
    public DamageManager getDamageManager() {
        return damageManager;
    }

    /**
     * Checks if the bomb placement cooldown timer has elapsed.
     *
     * @return True if the timer has elapsed, false otherwise.
     */
    public boolean getBombTimerCheck() {
        return bombTimer.check();
    }

    /**
     * Sets the bomb timer for cooldown.
     *
     * @param bombTimer The new bomb timer.
     */
    public void setBombTimer(Timer bombTimer) {
        this.bombTimer = bombTimer;
    }
}
