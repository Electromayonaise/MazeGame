package main_module.model.managers;

import main_module.BaseScreen;
import main_module.model.entities.Entity;
import main_module.model.entities.TemporalTile;
import main_module.model.entities.Tile;
import main_module.model.enums.SideEffect;
import main_module.model.enums.TileType;
import main_module.model.util.Timer;
import main_module.model.util.Vector;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DamageManager {

    protected TileManager tileManager;
    protected BaseScreen screen;

    protected List<Entity> bombDamageList;

    protected Timer bombTimer;

    protected static final long DAMAGE_TIME = 2000;

    protected TemporalTileManager temporalTileManager;

    private static final double BOMB_SIZE = Tile.SIZE;

    private static final int FIELD_OF_BOMB_DAMAGE = 4;

    public DamageManager(BaseScreen screen) {
        this.screen = screen;
        tileManager = screen.getTileManager();
        bombDamageList = new LinkedList<>();
        temporalTileManager = new TemporalTileManager();
    }

    public void manageBombDamage() {
        updateBombsDamage();
    }

    public List<Entity> getBombDamageList() {
        return bombDamageList;
    }

    public void updateBombsDamage() {
        List<Entity> temporalList = new LinkedList<>();

        for (Entity bombDamage : bombDamageList) {
            boolean flag = ((TemporalTile) bombDamage).check();
            if (flag) {
                temporalList.add(bombDamage);
            }
        }
        for (Entity bombDamage : temporalList) {
            removeBombDamage(bombDamage);
        }
    }

    // Puts bomb damage effect in the center
    public void startExplosion(Vector matrixCor) {
        int col = (int) (matrixCor.getX());
        int row = (int) (matrixCor.getY());
        List<Integer> cellsToCheckX = Arrays.asList(-1, 1); // Row
        List<Integer> cellsToCheckY = List.of(0);
        // The center one
        addBombDamage(col, row);
        // All horizontals -> side
        for (int dx : cellsToCheckX) {
            for (int dy : cellsToCheckY) {
                int newCol = col + dx;
                int newRow = row + dy;
                if(screen.getNonDestroyableTiles()[newRow][newCol]==null){
                    addBombDamage(newCol, newRow);
                }

            }
        }
        // Verticals -> up and down
        for (int dy : cellsToCheckX) {
            for (int dx : cellsToCheckY) {
                int newCol = col + dx;
                int newRow = row + dy;
                if(screen.getNonDestroyableTiles()[newRow][newCol]==null){
                    addBombDamage(newCol, newRow);
                }
            }
        }
    }

    public void addBombDamage(int col, int row) {
        Vector matrixPos = new Vector(col, row);
        Vector posAtCenter = calculateCoordinatesOfBombAtCenter(matrixPos);
        Entity bombDamage = temporalTileManager.generateTemporalTileWithCollision(posAtCenter, TileType.BOMB_DAMAGE, SideEffect.BOMB_DAMAGE, DAMAGE_TIME);
        bombDamageList.add(bombDamage);
        screen.addEntityInField(bombDamage, row, col, FIELD_OF_BOMB_DAMAGE);
    }

    public void removeBombDamage(Entity bombDamage) {
        Vector pos = bombDamage.getPos();
        Vector cor = screen.fromVectorToMatrixCoordinate(pos);
        screen.removeEntityFromField((int) cor.getY(), (int) cor.getX(), FIELD_OF_BOMB_DAMAGE);
        bombDamageList.remove(bombDamage);
    }

    public Vector calculateCoordinatesOfBombAtCenter(Vector matrixCor) {
        // Tile center
        double tileCenterX = (matrixCor.getX()) * Tile.SIZE + (double) Tile.SIZE / 2;
        double tileCenterY = (matrixCor.getY()) * Tile.SIZE + (double) Tile.SIZE / 2;
        //
        double bombX = tileCenterX - (double) BOMB_SIZE / 2;
        double bombY = tileCenterY - (double) BOMB_SIZE / 2;
        return new Vector(bombX, bombY);
    }
}
