package main_module.model.entities;

import main_module.BaseScreen;
import main_module.model.enums.Direction;
import main_module.model.util.MatrixCor;
import main_module.model.util.PathFinder;
import main_module.model.util.Vector;

import java.util.*;

/**
 * Represents the artificial intelligence (AI) logic for controlling an enemy character in the game.
 */
public class Ia {
    public Enemy enemy;
    private Direction currentInertia;
    private PathFinder pathFinder;

    /**
     * Creates a new instance of the Ia class.
     *
     * @param enemy The enemy character controlled by this AI logic.
     */
    public Ia(Enemy enemy) {
        this.enemy = enemy;
        currentInertia = Direction.RIGHT;
        this.pathFinder = new PathFinder();
    }

    /**
     * Updates the AI logic based on the game state.
     *
     * @param nonDestroyableTilesRepresentation The representation of non-destroyable tiles in the game.
     * @param destroyableTilesRepresentation    The representation of destroyable tiles in the game.
     * @param player                            The player character in the game.
     */
    public void update(int[][] nonDestroyableTilesRepresentation, int[][] destroyableTilesRepresentation, Player player, boolean adjacency, boolean directed) {
        // logic(nonDestroyableTilesRepresentation, destroyableTilesRepresentation, player);
        logic2(nonDestroyableTilesRepresentation, destroyableTilesRepresentation, player, adjacency, directed);
    }

    /**
     * Implements the logic for the AI, determining the directions for the enemy character to move.
     *
     * @param nonDestroyableTilesRepresentation The representation of non-destroyable tiles in the game.
     * @param destroyableTilesRepresentation    The representation of destroyable tiles in the game.
     * @param player                            The player character in the game.
     */
    public void logic(int[][] nonDestroyableTilesRepresentation, int[][] destroyableTilesRepresentation, Player player) {
        Set<Direction> directionsToGo = enemy.getDirectionsToGo();
        directionsToGo.clear();

        if (isPlayerVisible(player, nonDestroyableTilesRepresentation, destroyableTilesRepresentation)) {
            // Change the logic to pursue the player
            currentInertia = directionToPlayer(player);
        } else if (enemy.getCollisionDirectionSet().contains(currentInertia) || currentInertia == Direction.NONE) {
            currentInertia = changeInertiaRand(enemy.getCollisionDirectionSet(), currentInertia);
        }

        directionsToGo.add(currentInertia);
    }


    /**
     * Checks if the player is visible to the enemy.
     *
     * @param player                            The player character in the game.
     * @param nonDestroyableTilesRepresentation The representation of non-destroyable tiles in the game.
     * @param destroyableTilesRepresentation    The representation of destroyable tiles in the game.
     * @return True if the player is visible, false otherwise.
     */
    public boolean isPlayerVisible(Player player, int[][] nonDestroyableTilesRepresentation, int[][] destroyableTilesRepresentation) {
        Vector enemyPos = enemy.getPos();
        Vector playerPos = player.getMiddlePoint();

        int enemyCol = (int) (enemyPos.getX() / Tile.SIZE);
        int enemyRow = (int) (enemyPos.getY() / Tile.SIZE);

        int playerCol = (int) (playerPos.getX() / Tile.SIZE);
        int playerRow = (int) (playerPos.getY() / Tile.SIZE);

        if (enemyRow == playerRow) {
            int startCol = Math.min(enemyCol, playerCol);
            int endCol = Math.max(enemyCol, playerCol);

            for (int col = startCol; col <= endCol; col++) {
                if (destroyableTilesRepresentation[enemyRow][col] != 0 || nonDestroyableTilesRepresentation[enemyRow][col] != 0) {
                    return false;
                }
            }
            return true;
        }

        if (enemyCol == playerCol) {
            int startRow = Math.min(enemyRow, playerRow);
            int endRow = Math.max(enemyRow, playerRow);

            for (int row = startRow; row <= endRow; row++) {
                if (destroyableTilesRepresentation[row][enemyCol] != 0 || nonDestroyableTilesRepresentation[row][enemyCol] != 0) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    /**
     * Determines the direction for the enemy to move in order to approach the player.
     *
     * @param player The player character in the game.
     * @return The direction to move towards the player.
     */
    private Direction directionToPlayer(Player player) {
        Vector enemyPos = enemy.getPos();
        Vector playerPos = player.getPos();

        double deltaX = playerPos.getX() - enemyPos.getX();
        double deltaY = playerPos.getY() - enemyPos.getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            return (deltaX > 0) ? Direction.RIGHT : Direction.LEFT;
        } else {
            return (deltaY > 0) ? Direction.DOWN : Direction.UP;
        }
    }

    /**
     * Changes the current inertia based on the available directions and a random selection.
     *
     * @param collidingDirection The set of directions in which the enemy is colliding with other entities.
     * @param inertia            The current inertia direction.
     * @return The new inertia direction.
     */
    public Direction changeInertiaRand(Set<Direction> collidingDirection, Direction inertia) {
        Direction[] directionsInOrder = Direction.values();
        List<Direction> directions = Arrays.asList(directionsInOrder);
        Collections.shuffle(directions);
        Direction newInertia = Direction.NONE;
        for (Direction direction : directions) {
            boolean flag = collidingDirection.contains(direction);
            if (!flag && direction != Direction.NONE) {
                newInertia = direction;
            }
        }

        return newInertia;
    }

    /**
     * Changes the current inertia based on the available directions in order.
     *
     * @param collidingDirection The set of directions in which the enemy is colliding with other entities.
     * @param inertia            The current inertia direction.
     * @return The new inertia direction.
     */
    public Direction changeInertiaInOrder(Set<Direction> collidingDirection, Direction inertia) {
        Direction[] directions = Direction.values();
        Direction newInertia = Direction.NONE;
        for (Direction direction : directions) {
            boolean flag = collidingDirection.contains(direction);
            if (!flag && direction != Direction.NONE) {
                newInertia = direction;
            }
        }

        return newInertia;
    }


    public void logic2(int[][] nonDestroyableTilesRepresentation, int[][] destroyableTilesRepresentation, Player player, boolean adjacency, boolean directed) {

        Set<Direction> directionsToGo = enemy.getDirectionsToGo();
        directionsToGo.clear();

        Vector playerPosInVector = BaseScreen.fromVectorToMatrixCoordinate(player.getMiddlePoint());
        Vector enemyPosInVector = BaseScreen.fromVectorToMatrixCoordinate(enemy.getMiddlePoint());

        MatrixCor playerPosInMatrixCor = new MatrixCor((int) playerPosInVector.getY(), (int) playerPosInVector.getX());
        MatrixCor enemyPosInMatrixCor = new MatrixCor((int) enemyPosInVector.getY(), (int) enemyPosInVector.getX());
        List<MatrixCor> path = new ArrayList<>();
        path = pathFinder.getShortestPath(enemyPosInMatrixCor, playerPosInMatrixCor, nonDestroyableTilesRepresentation, adjacency, directed);

        Direction direction = getNextDirectionToGoAccordingToPath(path, enemyPosInMatrixCor);
        System.out.println("ORIGINAL+"+ direction);
        if(path.size()>0){
            direction=adjust(direction,enemyPosInMatrixCor,path.get(0));
        }
        System.out.println("AJUSTADA+"+ direction);

        directionsToGo.add(direction);

    }

    public Direction adjust(Direction directionToGo, MatrixCor enemyPosInMatrix, MatrixCor nextCell) {
        if (!enemy.getCollisionDirectionSet().contains(directionToGo)) {
            System.out.println("NO TENGO COLISIONES");
            return directionToGo;
        }
        System.out.println("mis colisiones son->"+enemy.getCollisionDirectionSet());
        //si el path me dice arriba o abajo y hay colision
        //entonces me debo mover a izquierda o a derecha
        if (directionToGo == Direction.UP|| directionToGo==Direction.DOWN) {
            System.out.println("TENGO COLISION UP O DOWN");

            //si mi borde izquierdo no esta en la misma columna, entonces debo ir la derecha
            if((int) BaseScreen.fromVectorToMatrixCoordinate(enemy.getPos()).getX()<nextCell.getCol()){
                directionToGo=Direction.RIGHT;
            }else{
                directionToGo=Direction.LEFT;
            }
        }else if(directionToGo== Direction.LEFT || directionToGo==Direction.RIGHT){
            System.out.println("TENGO COLISION RIGHT O LEFT");
            if((int) BaseScreen.fromVectorToMatrixCoordinate(enemy.getPos()).getY()<nextCell.getRow()){
                directionToGo=Direction.DOWN;
            }else{
                directionToGo=Direction.UP;
            }
        }
        return directionToGo;

    }

    public Direction getNextDirectionToGoAccordingToPath(List<MatrixCor> path, MatrixCor enemyPosInMatrix) {
        if (path.isEmpty()) {
            return Direction.NONE;
        }
        MatrixCor nextCell = path.get(0);
        if (enemyPosInMatrix.getRow() == nextCell.getRow()) {
            if (enemyPosInMatrix.getCol() < nextCell.getCol()) {
                return Direction.RIGHT;
            }

            if (enemyPosInMatrix.getCol() > nextCell.getCol()) {
                return Direction.LEFT;
            }
        }


        if (enemyPosInMatrix.getCol() == nextCell.getCol()) {

            if (enemyPosInMatrix.getRow() < nextCell.getRow()) {
                return Direction.DOWN;
            }

            if (enemyPosInMatrix.getRow() > nextCell.getRow()) {
                return Direction.UP;
            }


        }
        return Direction.NONE;

    }
}
