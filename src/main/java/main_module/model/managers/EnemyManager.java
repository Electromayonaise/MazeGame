package main_module.model.managers;

import javafx.scene.image.Image;
import main_module.model.entities.Enemy;
import main_module.model.enums.EnemyType;
import main_module.model.util.Vector;

/**
 * The {@code EnemyManager} class is responsible for generating different types of enemies.
 * It provides methods to create instances of enemy entities based on the specified {@link EnemyType}.
 * Currently, it supports the creation of normal enemies.
 */
public class EnemyManager {
    /**
     * Image representing the default appearance of enemies.
     */
    private Image enemyImage;

    /**
     * Constructs an {@code EnemyManager} and initializes the enemy image.
     */
    public EnemyManager() {
        enemyImage = new Image(((System.getProperty("user.dir") + "/src/main/resources/main_module/animations/character/enemies/enemy.png")), false);
    }

    /**
     * Generates an enemy based on the specified {@link EnemyType} and position.
     *
     * @param enemyType The type of enemy to generate.
     * @param pos       The position of the generated enemy.
     * @return An instance of the generated enemy.
     */
    public Enemy generateEnemy(EnemyType enemyType, Vector pos){
        Enemy enemy = null;
        switch (enemyType) {
            case NORMAL -> {
                enemy = generateNormalEnemy(pos);
            }
        }
        return enemy;
    }

    /**
     * Generates a normal enemy at the specified position.
     *
     * @param pos The position of the generated enemy.
     * @return An instance of a normal enemy.
     */
    public Enemy generateNormalEnemy(Vector pos) {
        return new Enemy(pos, 1);
    }
}
