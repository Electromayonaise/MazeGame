package main_module;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main_module.model.entities.*;
import main_module.model.entities.Character;
import main_module.model.enums.EnemyType;
import main_module.model.enums.SideEffect;
import main_module.model.enums.TileType;
import main_module.model.managers.*;
import main_module.model.util.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class representing the base screen of the game.
 */
public abstract class BaseScreen {

    public static final int SCREEN_WIDTH = 1152;
    public static final int SCREEN_HEIGHT = 768;
    protected Vector worldLimits;

    protected int maxCol;
    protected int maxRow;

    protected Canvas canvas;
    protected GraphicsContext gc;

    protected Player player;

    protected int stage = 1;

    /*-------------------representation layers---------------------------*/
    protected int[][] tilesWithNoCollisionRepresentation;

    protected int[][] destroyableTilesRepresentation;

    protected int[][] nonDestroyableTilesRepresentation;

    /*-----------------------------------------------------------*/

    /*-------------entity layers---------------------*/

    protected Entity[][] tilesWithNoCollision;

    protected Entity[][] nonDestroyableTiles;

    protected Entity[][] destroyableTiles;
    protected Entity[][] bombs;
    protected Entity[][] damage;

    //simple grouping of all layers

    protected Entity[][][] fields;

    protected List<Entity> enemyList;

    /********MANAGERS-------------------------------*/

    protected TileManager tileManager;

    protected CollisionManager collisionManager;

    protected BombManager bombManager;

    protected DamageManager damageManager;

    protected EnemyManager enemyManager;

    /**--------------------methods for map initialization---------------------------*/
    /**** All maps must implement these methods *****/
    public abstract int[][] initTilesWithNoCollisionRepresentation();

    public abstract int[][] initNonDestroyableTilesRepresentation();

    protected boolean adjacency;
    protected boolean directed;
    protected BaseScreen(Canvas canvas,boolean adjacency,boolean directed) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        tileManager = new TileManager();
        collisionManager = new CollisionManager(this);
        bombManager = new BombManager(this);
        damageManager = new DamageManager(this);
        enemyManager = new EnemyManager();
        this.adjacency=adjacency;
        this.directed=directed;
    }

    //------------updates-----*/
    public boolean update() {
        bombManager.manageBombs();
        collisionManager.manageCollisions(bombManager.getDamageManager().getBombDamageList());
        player.update();
        updateEnemies();
        if (player.getHp() <= 0) {
            paint();
            return true;
        }
        return false;
    }

    public void updateEnemies() {
        for (Entity ent : enemyList) {
            Enemy enemy = (Enemy) ent;
            enemy.update(nonDestroyableTilesRepresentation, destroyableTilesRepresentation, player,adjacency,directed);
        }
    }

    public void paintEnemies() {
        for (Entity ent : enemyList) {
            paintEntity(ent);
        }
    }

    /******-----paint----*/

    public void paint() {
        gc.setFill(Color.BLACK);
        gc.fillRect(-1152, -768, canvas.getWidth() * 2, canvas.getHeight() * 2);
        paintEntitiesInMatrix(tilesWithNoCollision);
        paintEntitiesInMatrix(destroyableTiles);
        paintEntitiesInMatrix(bombs);
        paintEntitiesInMatrix(nonDestroyableTiles);
        paintEntitiesInMatrix(damage);
        paintEntity(player);
        paintEnemies();

        // Hearts
        double heartSpacing = 50;
        double heartX = 10;
        double heartY = 10;

        for (int i = 0; i < player.getHp(); i++) {
            gc.drawImage(player.getHearthImage(), heartX, heartY, 40, 40);
            heartX += heartSpacing;
        }

        // Display the current stage of the player
        gc.setFill(Color.GHOSTWHITE);
        gc.setFont(new Font(30));
        gc.fillText("Stage: " + stage, SCREEN_WIDTH / 2 - 50, 40);

        // Display the bomb image followed by the number of bombs
        Image bombImage = new Image(((System.getProperty("user.dir") + "/src/main/resources/main_module/tiles/bomb.png")), false);
        gc.drawImage(bombImage, SCREEN_WIDTH - 300, 10, 40, 40);
        gc.setFill(Color.GHOSTWHITE);
        gc.setFont(new Font(30));
        gc.fillText("x" + player.getBombs(), SCREEN_WIDTH - 250, 50);

        // Check if the player has increased the number of bombs and execute a "+++" effect
        if (player.isBombsIncreased()) {
            bombIncreasedEffect();
        }
    }

    public void bombIncreasedEffect() {
        // Start a thread for the "+++" effect
        new Thread(() -> {
            Platform.runLater(() -> {
                gc.setFill(Color.YELLOW);
                gc.setFont(new Font(30));
                gc.fillText("+++", SCREEN_WIDTH - 200, 50);
            });

            try {
                Thread.sleep(1000); // Wait for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {
                player.setBombsIncreased(false);
            });
        }).start();
    }

    public void paintEntitiesInMatrix(Entity entities[][]) {
        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[0].length; j++) {
                if (entities[i][j] != null) {
                    paintEntity(entities[i][j]);
                }
            }
        }
    }

    /**** Add, remove, and update entities in layers ****/
    public boolean removeEntityFromField(int row, int col, int field) {
        // 0 -> tiles with no collision
        // 1 -> non-destroyable tiles
        // 2 -> destroyable tiles
        // 3 -> bombs
        boolean flag = false;
        if (0 <= row && row < getMaxRow() && col >= 0 && col < getMaxCol() && 0 <= field && field < fields.length) {
            Entity[][] correspondingField = fields[field];
            if (correspondingField[row][col] != null) {
                correspondingField[row][col] = null;
                flag = true;
            }
        }
        return flag;
    }

    public boolean addEntityInField(Entity entity, int row, int col, int field) {
        // 0 -> tiles with no collision
        // 1 -> non-destroyable tiles
        // 2 -> destroyable tiles
        // 3 -> bombs
        boolean flag = false;
        if (0 <= row && row < getMaxRow() && col >= 0 && col < getMaxCol() && 0 <= field && field < fields.length) {
            Entity[][] correspondingField = fields[field];
            correspondingField[row][col] = entity;
            flag = true;
        }
        return flag;
    }

    public boolean addEntityInField(Entity entity, Vector matrixCor, int field) {
        return addEntityInField(entity, (int) matrixCor.getX(), (int) matrixCor.getY(), field);
    }

    public boolean removeEntityFromField(Vector matrixCor, int field) {
        return removeEntityFromField((int) matrixCor.getX(), (int) matrixCor.getY(), field);
    }

    /*** Interpret the representation matrix ****/
    /**************tiles with no collision*******************/
    protected Entity[][] initTilesWithNoCollision(int[][] tilesWithNoCollisionRepresentation) {
        int row = tilesWithNoCollisionRepresentation.length;
        int col = tilesWithNoCollisionRepresentation[0].length;
        Entity[][] tilesWithNoCollision = new Entity[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Vector posInMatrix = new Vector(i, j);
                int num = tilesWithNoCollisionRepresentation[i][j];
                tilesWithNoCollision[i][j] = interpretateTilesWithNoCollision(num, posInMatrix);
            }
        }
        return tilesWithNoCollision;
    }

    protected Entity interpretateTilesWithNoCollision(int num, Vector posInMatrix) {
        Vector pos = fromMatrixCordinateToVector((int) posInMatrix.getX(), (int) posInMatrix.getY());
        Entity entity;
        switch (num) {
            case 1 -> entity = tileManager.generateNonCollisionTile(pos, TileType.BOMB);
            default -> entity = null;
        }
        return entity;
    }

    public int removeEnemiesWithZeroHp(List<Entity> enemies) {
        List<Entity> temp = new LinkedList<>();
        for (Entity character : enemies) {
            int hp = ((Character) character).getHp();
            if (hp <= 0) {
                temp.add(character);
            }
        }
        int entitiesRemoved = 0;
        for (Entity character : temp) {
            entitiesRemoved++;
            enemies.remove(character);
        }
        return entitiesRemoved;
    }

    /********************non-destroyable tiles**********************/
    protected Entity[][] initNonDestroyableTiles(int[][] tilesWithNoCollisionRepresentation) {
        int row = tilesWithNoCollisionRepresentation.length;
        int col = tilesWithNoCollisionRepresentation[0].length;
        Entity[][] tilesWithNoCollision = new Entity[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Vector posInMatrix = new Vector(i, j);
                int num = tilesWithNoCollisionRepresentation[i][j];
                tilesWithNoCollision[i][j] = interpretateNonDestroyableTiles(num, posInMatrix);
            }
        }
        return tilesWithNoCollision;
    }

    protected Entity interpretateNonDestroyableTiles(int num, Vector posInMatrix) {
        Vector pos = fromMatrixCordinateToVector((int) posInMatrix.getX(), (int) posInMatrix.getY());
        Entity entity;
        switch (num) {
            case 1 -> entity = (Entity) tileManager.generateCollisionTile(pos, TileType.BRICK, SideEffect.OBSTRUCTION);
            default -> entity = null;
        }
        return entity;
    }

    /******************destroyable tiles*******/
    protected Entity[][] initDestroyableTiles(int[][] destroyableTilesRepresentation) {
        int row = destroyableTilesRepresentation.length;
        int col = destroyableTilesRepresentation[0].length;
        Entity[][] destroyableTiles = new Entity[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Vector posInMatrix = new Vector(i, j);
                int num = destroyableTilesRepresentation[i][j];
                destroyableTiles[i][j] = interpretateDestroyableTiles(num, posInMatrix);
            }
        }
        return destroyableTiles;
    }

    protected Entity interpretateDestroyableTiles(int num, Vector posInMatrix) {
        Vector pos = fromMatrixCordinateToVector((int) posInMatrix.getX(), (int) posInMatrix.getY());
        Entity entity;
        switch (num) {
            case 1 ->
                    entity = (Entity) tileManager.generateCollisionTile(pos, TileType.DESTROYABLE, SideEffect.OBSTRUCTION);
            default -> entity = null;
        }
        return entity;
    }

    protected Entity interpretateEnemy(int num, Vector posInMatrix) {
        Vector pos = fromMatrixCordinateToVector((int) posInMatrix.getX(), (int) posInMatrix.getY());
        Entity entity;
        switch (num) {
            case 1 -> entity = enemyManager.generateEnemy(EnemyType.NORMAL, pos);
            default -> entity = null;
        }
        return entity;
    }

    protected List<Entity> initEnemies(int[][] matrix) {
        List<Entity> enemyList = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int num = matrix[i][j];
                if (num != 0) {
                    Vector posInMatrix = new Vector(i, j);
                    enemyList.add(interpretateEnemy(num, posInMatrix));
                }
            }
        }
        return enemyList;
    }

    /***odd methods*****/
    protected Vector fromMatrixCordinateToVector(int row, int col) {
        // Each Tile measures 16x16
        int y = (int) Tile.SIZE * row;
        int x = (int) Tile.SIZE * col;
        return new Vector(x, y);
    }

    public static Vector fromVectorToMatrixCoordinate(Vector vector) {
        double x = vector.getX();
        double y = vector.getY();
        double matrixX = Math.floor(x / Tile.SIZE);
        double matrixY = Math.floor(y / Tile.SIZE);
        return new Vector(matrixX, matrixY);
    }

    private void paintEntity(Entity entity) {

        Vector playerPos = player.getPos();
        double playerX = playerPos.getX() + player.getWidth() / 2;
        double playerY = playerPos.getY() + player.getHeight() / 2;

        double screenMidPointX = SCREEN_WIDTH / 2;
        double screenMidPointY = SCREEN_HEIGHT / 2;

        double xTranslation = screenMidPointX - playerX;
        double yTranslation = screenMidPointY - playerY;

        double maxX = getWorldLimits().getX();
        double maxY = getWorldLimits().getY();

        if (playerX - screenMidPointX < 0) {
            xTranslation = 0;
        } else if (playerX + screenMidPointX > maxX) {
            double xr = maxX - screenMidPointX;
            double xl = screenMidPointX;
            xTranslation = xl - xr;
        }

        if (playerY - screenMidPointY < 0) {
            yTranslation = 0;
        } else if (playerY + screenMidPointY > maxY) {
            double xd = maxY - screenMidPointY;
            double xu = screenMidPointY;
            yTranslation = xu - xd;
        }

        Vector currentPos = entity.getPos();
        Image currentImage = entity.getCurrentImage();
        double posInXScreen = currentPos.getX() + xTranslation;
        double posInYScreen = currentPos.getY() + yTranslation;

        gc.drawImage(currentImage, posInXScreen, posInYScreen, entity.getWidth(), entity.getHeight());
    }

    /*-----------------gets----------*/
    public Player getPlayer() {
        return player;
    }

    public Vector getWorldLimits() {
        return worldLimits;
    }

    public int getMaxCol() {
        return maxCol;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public Entity[][] getNonDestroyableTiles() {
        return nonDestroyableTiles;
    }

    public Entity[][] getDestroyableTiles() {
        return destroyableTiles;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public Entity[][] getBombs() {
        return bombs;
    }

    public Entity[][] getDamage() {
        return damage;
    }

    public void setOnKeyPressed(KeyEvent event) {
        player.setOnKeyPressed(event);
    }

    public void setOnKeyReleased(KeyEvent event) {
        player.setOnKeyReleased(event);
    }

    public List<Entity> getEnemyList() {
        return enemyList;
    }
}
