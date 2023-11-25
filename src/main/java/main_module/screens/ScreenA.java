package main_module.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import main_module.BaseScreen;
import main_module.model.entities.Entity;
import main_module.model.entities.Player;
import main_module.model.entities.Tile;
import main_module.model.managers.TileManager;
import main_module.model.util.MapGenerator;
import main_module.model.util.Vector;

import java.util.Random;

/**
 * Represents the game screen where the main gameplay occurs.
 * Extends the {@link BaseScreen} class.
 */
public class ScreenA extends BaseScreen {
    private final double INITIAL_PLAYER_POS_X = 65;
    private final double INITIAL_PLAYER_POS_Y = 65;
    private final int INITIAL_PLAYER_HP = 5;

    private final int MAX_ROW = 21;
    private final int MAX_COL = 21;

    private boolean adjacencyList;

    private boolean directed;

    private int portalRow;

    private int portalCol;



    /**
     * Creates a new instance of the ScreenA class.
     *
     * @param canvas          The canvas where the game screen is drawn.
     * @param adjacencyList   A flag indicating whether to use an adjacency list for map generation.
     * @param directed        A flag indicating whether the map is directed or not.
     */
    public ScreenA(Canvas canvas,boolean adjacencyList,boolean directed) {
        super(canvas,adjacencyList,directed);
        Vector pos = new Vector(INITIAL_PLAYER_POS_X, INITIAL_PLAYER_POS_Y);
        this.player = new Player(pos, INITIAL_PLAYER_HP, bombManager);
        tileManager = new TileManager();
        super.worldLimits = new Vector(MAX_COL * Tile.SIZE, MAX_ROW * Tile.SIZE);
        maxCol = MAX_COL;
        maxRow = MAX_ROW;
        isPortalShowing = false;

        /***Initialization of layers***/

        nonDestroyableTilesRepresentation = initNonDestroyableTilesRepresentation();
        nonDestroyableTiles = initNonDestroyableTiles(nonDestroyableTilesRepresentation);

        int[][] enemyMatrix = initEnemyRepresentation();

        enemyList = initEnemies(enemyMatrix);

        destroyableTilesRepresentation = initDestroyableTilesRepresentation(enemyMatrix);
        destroyableTiles = initDestroyableTiles(destroyableTilesRepresentation);

        tilesWithNoCollisionRepresentation = initTilesWithNoCollisionRepresentation();
        tilesWithNoCollision = initTilesWithNoCollision(tilesWithNoCollisionRepresentation);

        bombs = new Entity[MAX_ROW][MAX_COL];
        damage = new Entity[MAX_ROW][MAX_COL];
        fields = new Entity[][][]{tilesWithNoCollision, nonDestroyableTiles, destroyableTiles, bombs, damage};
    }

    /**
     * Initializes the representation of non-destroyable tiles on the screen.
     *
     * @return A 2D array representing the non-destroyable tiles.
     */
    public int[][] initNonDestroyableTilesRepresentation() {
        MapGenerator mapGenerator = new MapGenerator();
        int[][] generatedMap = mapGenerator.generateMap(MAX_ROW - 2, MAX_COL - 2, adjacencyList, directed);
        return generatedMap;
    }

    /**
     * Initializes the representation of destroyable tiles on the screen.
     *
     * @param enemyMatrix The matrix representing the enemy positions.
     * @return A 2D array representing the destroyable tiles.
     */
    public int[][] initDestroyableTilesRepresentation(int[][] enemyMatrix) {
        int[][] destroyableTilesMatrix = new int[MAX_ROW][MAX_COL];
        Random random = new Random();

        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                // No modification for the first row, first column, last row, or last column
                if (i == 0 || j == 0 || i == MAX_ROW - 1 || j == MAX_COL - 1) {
                    destroyableTilesMatrix[i][j] = 0;
                    // Only odd coordinates may or may not have destroyableTiles, except (1,1) && (1, 3) && (3, 1), and with no enemies
                } else if (i % 2 == 1 && j % 2 == 1 && !((i == 1 && j == 1) || (i == 1 && j == 3) || (i == 3 && j == 1)) && enemyMatrix[i][j] == 0) {
                    double probability = 0; // 30% chance of having a destroyableTile
                    destroyableTilesMatrix[i][j] = (random.nextDouble() < probability) ? 1 : 0;
                } else {
                    destroyableTilesMatrix[i][j] = 0;
                }
            }
        }

        return destroyableTilesMatrix;
    }

    /**
     * Initializes the representation of tiles with no collision on the screen.
     *
     * @return A 2D array representing the tiles with no collision.
     */
    public int[][] initTilesWithNoCollisionRepresentation() { // For the bombs
        int[][] noCollisionTilesMatrix = new int[MAX_ROW][MAX_COL];
        Random random = new Random();

        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                // No modification for the first row, first column, last row, or last column
                if (i == 0 || j == 0 || i == MAX_ROW - 1 || j == MAX_COL - 1) {
                    noCollisionTilesMatrix[i][j] = 0;
                } else if (destroyableTilesRepresentation[i][j] == 1) {
                    // Generate noCollisionTiles only in positions where there are destroyableTiles
                    double probability = 0.5; // 50% chance of having a Non-destroyableTile
                    noCollisionTilesMatrix[i][j] = (random.nextDouble() < probability) ? 1 : 0;
                } else {
                    noCollisionTilesMatrix[i][j] = 0;
                }
            }
        }

        // For the portal
        generateRandomPortalPosition();
        noCollisionTilesMatrix[portalRow][portalCol] = 2;

        return noCollisionTilesMatrix;
    }

    /**
     * Initializes the representation of enemies on the screen.
     *
     * @return A matrix representing the enemy positions.
     */
    public int[][] initEnemyRepresentation() {
        Random random = new Random();
        int[][] enemyMatrix = new int[MAX_ROW][MAX_COL];

        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                // Keep the first row, first column, last row, and last column unchanged
                if (i == 0 || j == 0 || i == MAX_ROW - 1 || j == MAX_COL - 1) {
                    enemyMatrix[i][j] = 0;
                } else {
                    // Allow enemies only in odd coordinates (except position (1,1) && (1, 3) && (3, 1))
                    if (i % 2 != 0 && j % 2 != 0 && !((i == 1 && j == 1) || (i == 1 && j == 3) || (i == 3 && j == 1))) {
                        // Random to decide if there is an enemy (1 or 0) according to a probability
                        double probability = 0.05; // 30% chance of having an enemy
                        enemyMatrix[i][j] = (random.nextDouble() < probability) ? 1 : 0;
                    } else {
                        enemyMatrix[i][j] = 0;
                    }
                }
            }
        }
        return enemyMatrix;
    }

    /**
     * Updates the game state.
     *
     * @return True if the game is over, false otherwise.
     */
    @Override
    public boolean update() {
        bombManager.manageBombs();
        collisionManager.manageCollisions(bombManager.getDamageManager().getBombDamageList());
        player.update();
        // show EndGame "Game Over"
        if(player.getHp() <= 0){
            player.setEndGameImage();
            return true;
        }

        updateEnemies();

        int removedEnemies = removeEnemiesWithZeroHp(enemyList);
        if (removedEnemies != 0) {
            player.addKills(removedEnemies);
        }

        // When the player kills an even number of enemies, they gain a bomb
        if (player.getKills() != 0 && removedEnemies != 0 && player.getKills() % 2 == 0) {
            player.addBomb();
            player.setBombsIncreased(true);
        }

        // If the player finishes off all the enemies show the portal
        if (enemyList.isEmpty()) {
            if(!isPortalShowing){
                isPortalShowing = true;
                System.out.println(portalCol);
                System.out.println(portalRow);
            }

            // If the player is close to the portal
            Vector playerPos = player.getPos();

            // Define the maximum distance in terms of tile units
            double maxDistance = Tile.SIZE; // You can adjust this value

            // Calculate the distance between the player and the portal
            double distance = Math.sqrt(Math.pow(playerPos.getX() - portalCol * Tile.SIZE, 2) + Math.pow(playerPos.getY() - portalRow * Tile.SIZE, 2));

            // If the player is close enough to the portal, update the stage
            if (distance <= maxDistance) {
                updateStage();
            }
        }

        // If the player picks up a bomb
        if (pickUpBomb()) {
            player.setBombsIncreased(true);
            bombIncreasedEffect();
        }


        return false;
    }

    /**
     * Generates a random position for the portal ensuring it is placed in an odd position.
     * If the portal is placed in an even position, it regenerates the position.
     */
    private void generateRandomPortalPosition() {
        Random random = new Random();
        do {
            portalCol = random.nextInt(MAX_COL);
            portalRow = random.nextInt(MAX_ROW);
        } while (portalCol % 2 == 0 || portalRow % 2 == 0);
    }



    /**
     * Checks if the player picks up a bomb from the screen.
     *
     * @return True if the player picks up a bomb, false otherwise.
     */
    public boolean pickUpBomb() {
        Vector playerMatrixPos = fromVectorToMatrixCoordinate(player.getPos());
        int playerRow = (int) playerMatrixPos.getY();
        int playerCol = (int) playerMatrixPos.getX();

        if (tilesWithNoCollision[playerRow][playerCol] != null && tilesWithNoCollisionRepresentation[playerRow][playerCol] == 1) {
            removeEntityFromField(playerRow, playerCol, 0);
            player.addBomb();
            return true;
        }

        return false;
    }

    /**
     * Updates the game to the next stage.
     */
    public void updateStage() {
        isPortalShowing = false;
        player.setPos(new Vector(INITIAL_PLAYER_POS_X, INITIAL_PLAYER_POS_Y));
        stage++; // Update the stage

        /***Initialization of layers***/

        nonDestroyableTilesRepresentation = initNonDestroyableTilesRepresentation();
        nonDestroyableTiles = initNonDestroyableTiles(nonDestroyableTilesRepresentation);

        int[][] enemyMatrix = initEnemyRepresentation();

        enemyList = initEnemies(enemyMatrix);

        destroyableTilesRepresentation = initDestroyableTilesRepresentation(enemyMatrix);
        destroyableTiles = initDestroyableTiles(destroyableTilesRepresentation);

        tilesWithNoCollisionRepresentation = initTilesWithNoCollisionRepresentation();
        tilesWithNoCollision = initTilesWithNoCollision(tilesWithNoCollisionRepresentation);

        bombs = new Entity[MAX_ROW][MAX_COL];
        damage = new Entity[MAX_ROW][MAX_COL];
        fields = new Entity[][][]{tilesWithNoCollision, nonDestroyableTiles, destroyableTiles, bombs, damage};
    }
}