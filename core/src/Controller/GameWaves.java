package Controller;

import Model.*;
import View.AfterGameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import java.util.Iterator;

public class GameWaves {
    private int wave = 1;
    private static GameWaves gameWaves;

    public GameWaves() {
        gameWaves = this;
    }

    public static GameWaves getGameWaves() {
        return gameWaves;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public void spawnEnemies() {
        new Tank(1800, 70f, DataBaseCommands.getDifficulty());
        new Tank(1200, -70f, DataBaseCommands.getDifficulty());
        new Tank(300, 70f, DataBaseCommands.getDifficulty());
        new Building(2000);
        new Bunker(600);
        new Truck(1000, 100f);
        new Truck(1500, -100f);
        new Tree(500);
    }

    public void goToNextWave() {
        wave++;
        EnemyObjects.getEnemyObjects().clear();
        spawnEnemies();
        if (wave == 2) {
            new AntiAirTankOrMig(900, 170, 70f, "zsu57.png", 5);
        } else if (wave == 3) {
            new AntiAirTankOrMig(900, 1000, -200f, "mig1.png", 6);
        } else {
            wave = 3;
            ((Game) Gdx.app.getApplicationListener()).setScreen(new AfterGameScreen());
        }
    }
}
