package main_module.model.managers;

import javafx.geometry.Rectangle2D;
import main_module.BaseScreen;
import main_module.model.entities.Character;
import main_module.model.entities.Entity;
import main_module.model.entities.ICollide;
import main_module.model.enums.Direction;
import main_module.model.enums.SideEffect;
import main_module.model.util.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Manages collision detection and resolution in the game.
 */
public class CollisionManager {

    /**
     * The base screen where the collision manager operates.
     */
    private BaseScreen screen;

    /**
     * Creates a new instance of the CollisionManager class.
     *
     * @param screen The base screen where the collision manager operates.
     */
    public CollisionManager(BaseScreen screen) {
        this.screen = screen;
    }

    /**
     * Manages collisions between entities and the game environment.
     *
     * @param bombDamageList The list of entities damaged by bomb explosions.
     */
    public void manageCollisions(List<Entity> bombDamageList) {
        managePlayerCollisionsToEnvironment(screen.getPlayer());
        manageEnemiesCollisionsToEnvironment(screen.getEnemyList());
        manageDestroyableTilesCollisions(bombDamageList);
        manageCollisionsFromEnemiesToPlayer(screen.getEnemyList(), screen.getPlayer());
    }

    /**
     * Manages collisions from enemies to the player.
     *
     * @param enemies The list of enemy entities.
     * @param player  The player entity.
     */
    public void manageCollisionsFromEnemiesToPlayer(List<Entity> enemies, Character player) {
        for (Entity entity : enemies) {
            Direction direction = getCollidingDirection(entity, player);
            if (direction != Direction.NONE) {
                player.getSideEffects().add(((ICollide) entity).getSideEffect());
            }
        }
    }

    /**
     * Manages collisions between enemies and the game environment.
     *
     * @param enemyList The list of enemy entities.
     */
    public void manageEnemiesCollisionsToEnvironment(List<Entity> enemyList) {
        for (Entity ent : enemyList) {
            managePlayerCollisionsToEnvironment((Character) ent);
        }
    }

    /**
     * Manages collisions with destroyable tiles caused by bomb explosions.
     *
     * @param bombDamageList The list of entities damaged by bomb explosions.
     */
    public void manageDestroyableTilesCollisions(List<Entity> bombDamageList) {
        for (Entity bombDamage : bombDamageList) {
            removeDestroyableTiles(bombDamage);
        }
    }

    /**
     * Removes destroyable tiles affected by bomb explosions.
     *
     * @param bomb The bomb entity causing the damage.
     */
    public void removeDestroyableTiles(Entity bomb) {
        Vector matrixCor = screen.fromVectorToMatrixCoordinate(bomb.getMiddlePoint());
        int bombDamageCol = (int) matrixCor.getX();
        int bombDamageRow = (int) matrixCor.getY();
        Entity field[][] = screen.getDestroyableTiles();
        if (field[bombDamageRow][bombDamageCol] != null) {
            field[bombDamageRow][bombDamageCol] = null;
        }
    }

    /**
     * Manages collisions between the player and the game environment.
     *
     * @param player The player entity.
     */
    public void managePlayerCollisionsToEnvironment(Character player) {
        Vector matrixPlayerPos = screen.fromVectorToMatrixCoordinate(player.getMiddlePoint());

        Entity nonDestroyableEntities[][] = screen.getNonDestroyableTiles();
        Entity destroyableEntities[][] = screen.getDestroyableTiles();
        Entity bombs[][] = screen.getBombs();
        Entity damage[][] = screen.getDamage();

        Entity fields[][][] = {nonDestroyableEntities, destroyableEntities, bombs, damage};
        List<Entity> entities = new ArrayList<>();

        int playerCol = (int) matrixPlayerPos.getX();
        int playerRow = (int) matrixPlayerPos.getY();

        List<Integer> cellsToCheck = new ArrayList<>(List.of(1, -1, 0, 2, -2));

        for (Entity field[][] : fields) {
            for (Integer dx : cellsToCheck) {
                for (Integer dy : cellsToCheck) {
                    int col = playerCol + dx;
                    int row = playerRow + dy;
                    if (validateCoordinate(row, col)) {
                        checkEntity(field[row][col], player);
                    }
                }
            }
        }
    }

    /**
     * Checks collisions between two entities.
     *
     * @param entity The entity to check collision with.
     * @param player The player entity.
     */
    public void checkEntity(Entity entity, Character player) {
        if (entity != null) {
            Direction direction = getCollidingDirection(entity, player);
            SideEffect sideEffect = ((ICollide) entity).getSideEffect();
            if (direction != direction.NONE) {
                if (sideEffect == SideEffect.OBSTRUCTION) {
                    (player.getCollisionDirectionSet()).add(direction);
                } else {
                    (player.getSideEffects()).add(sideEffect);
                }
            }
        }
    }

    /**
     * Validates whether the given coordinates are within the game field.
     *
     * @param row The row index.
     * @param col The column index.
     * @return True if the coordinates are within bounds, false otherwise.
     */
    public boolean validateCoordinate(int row, int col) {
        return 0 <= row && row < screen.getMaxRow() && col >= 0 && col < screen.getMaxCol();
    }

    /**
     * Gets the direction of collision between two entities.
     *
     * @param me     The first entity.
     * @param entity The second entity.
     * @return The direction of collision.
     */
    private Direction getCollidingDirection(Entity me, Entity entity) {
        double epsilon = 3;
        double rectDimension = 5;
        SideEffect sideEffect = ((ICollide) me).getSideEffect();
        Vector pos = me.getPos();
        double width = me.getWidth();
        double height = me.getHeight();

        // If the collision is different from obstruction.
        // It doesn't matter much about rectangles here.
        // Just check if there is an intersection between 2 rectangles.
        // Also, we don't need to know the direction, always return UP.
        if (sideEffect != SideEffect.OBSTRUCTION) {
            Vector leftUp = new Vector(pos.getX(), pos.getY());
            Rectangle2D myRectangle = new Rectangle2D(leftUp.getX(), leftUp.getY(), width, height);
            Vector eLeftUp = new Vector(entity.getPos().getX(), entity.getPos().getY());
            Rectangle2D eRectangle = new Rectangle2D(eLeftUp.getX(), eLeftUp.getY(), entity.getWidth(), entity.getHeight());
            if (myRectangle.intersects(eRectangle)) {
                return Direction.UP;
            }
        }

        // NOW, IF THE SIDE EFFECT IS DIFFERENT FROM OBSTRUCTION, IT GETS MORE COMPLEX
        /*my corners*/
        Vector leftUp = new Vector(pos.getX(), pos.getY());
        Vector rightUp = new Vector(pos.getX() + width, pos.getY());
        Vector leftDown = new Vector(pos.getX(), pos.getY() + height);
        Vector rightDown = new Vector(pos.getX() + width, pos.getY() + height);
        /*entity corners*/
        Vector eLeftUp = new Vector(entity.getPos().getX(), entity.getPos().getY());
        Vector eRightUp = new Vector(entity.getPos().getX() + entity.getWidth(), entity.getPos().getY());
        Vector eLeftDown = new Vector(entity.getPos().getX(), entity.getPos().getY() + entity.getHeight());
        Vector eRightDown = new Vector(entity.getPos().getX() + entity.getWidth(), entity.getPos().getY() + entity.getHeight());

        // Left wall is a bit inside the rectangle
        Rectangle2D leftWall = new Rectangle2D(leftUp.getX(), leftUp.getY() + epsilon, rectDimension, height - 2 * epsilon);
        // Now, since it protrudes on the right, add epsilon
        Rectangle2D rightWall = new Rectangle2D(rightUp.getX() - rectDimension, rightUp.getY() + epsilon, rectDimension, height - 2 * epsilon);

        Rectangle2D upWall = new Rectangle2D(leftUp.getX() + epsilon, leftUp.getY(), width - 2 * epsilon, rectDimension);

        Rectangle2D downWall = new Rectangle2D(leftDown.getX() + epsilon, leftDown.getY() - rectDimension, width - 2 * epsilon, rectDimension);

        Rectangle2D eLeftWall = new Rectangle2D(eLeftUp.getX(), eLeftUp.getY() + epsilon, rectDimension, entity.getHeight() - 2 * epsilon);
        // Now, since it protrudes on the right, add epsilon
        Rectangle2D eRightWall = new Rectangle2D(eRightUp.getX() - rectDimension, eRightUp.getY() + epsilon, rectDimension, entity.getHeight() - 2 * epsilon);

        Rectangle2D eUpWall = new Rectangle2D(eLeftUp.getX() + epsilon, eLeftUp.getY(), entity.getWidth() - 2 * epsilon, rectDimension);
        Rectangle2D eDownWall = new Rectangle2D(eLeftDown.getX() + epsilon, eLeftDown.getY() - rectDimension, entity.getWidth() - 2 * epsilon, rectDimension);

        if (eRightWall.intersects(leftWall)) {
            return Direction.RIGHT;
        }
        if (eLeftWall.intersects(rightWall)) {
            return Direction.LEFT;
        }
        if (eUpWall.intersects(downWall)) {
            return Direction.UP;
        }
        if (eDownWall.intersects(upWall)) {
            return Direction.DOWN;
        }

        return Direction.NONE;
    }


}
