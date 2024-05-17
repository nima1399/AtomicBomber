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
        new Tank((float) (Math.random() * Gdx.graphics.getWidth() - 300), 70f, DataBaseCommands.getDifficulty());
        new Tank((float) (Math.random() * Gdx.graphics.getWidth() - 300), -70f, DataBaseCommands.getDifficulty());
        new Tank((float) (Math.random() * Gdx.graphics.getWidth() - 300), 70f, DataBaseCommands.getDifficulty());
        new Building((float) (Math.random() * Gdx.graphics.getWidth() - 300));
        new Bunker((float) (Math.random() * Gdx.graphics.getWidth() - 300));
        new Truck((float) (Math.random() * Gdx.graphics.getWidth() - 300), 100f);
        new Truck((float) (Math.random() * Gdx.graphics.getWidth() - 300), -100f);
        new Tree((float) (Math.random() * Gdx.graphics.getWidth() - 300));
    }

    public void goToNextWave() {
        if (wave != 0) new Score();
        wave++;
        EnemyObjects.getEnemyObjects().clear();


        switch (wave) {
            case 1:
                spawnEnemies();
                break;
            case 2:
                spawnEnemies();
                FirstGameScreen.setWaveFinished();
                new AntiAirTank((float) (Math.random() * Gdx.graphics.getWidth() - 300), 170, 70f, "zsu57.png");
                break;
            case 3:
                spawnEnemies();
                FirstGameScreen.setWaveFinished();
                break;
            default:
                wave = 3;
                ((Game) Gdx.app.getApplicationListener()).setScreen(new AfterGameScreen());
        }
    }
}
