package Controller;

import Model.*;
import View.AfterGameScreen;
import View.FirstGameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GameWaves {
    private int wave = 0;
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


        switch (wave) {
            case 1:
                spawnEnemies();
                break;
            case 2:
                spawnEnemies();
                FirstGameScreen.setWaveFinished();
                new AntiAirTank(900, 170, 70f, "zsu57.png");
                break;
            case 3:
                spawnEnemies();
                FirstGameScreen.setWaveFinished();
//                new Mig(900, 1000, -200f, "mig1.png");
                break;
            default:
                wave = 3;
                ((Game) Gdx.app.getApplicationListener()).setScreen(new AfterGameScreen());
        }
    }
}
