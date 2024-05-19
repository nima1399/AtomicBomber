package Controller;

import Model.*;
import View.AfterGameScreen;
import View.FirstGameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GameWaves {
    private static int wave = 0;
    private static GameWaves gameWaves;

    public GameWaves() {
        gameWaves = this;
    }

    public static GameWaves getGameWaves() {
        return gameWaves;
    }

    public static int getWave() {
        return wave;
    }

    public static void setWave(int number) {
        wave = number;
    }

    public void spawnEnemies() {
        new Tank((float) getX(), 70f, DataBaseCommands.getDifficulty());
        new Tank((float) getX(), -70f, DataBaseCommands.getDifficulty());
        new Tank((float) getX(), 70f, DataBaseCommands.getDifficulty());
        new Building((float) getX());
        new Bunker((float) getX());
        new Truck((float) getX(), 100f);
        new Truck((float) getX(), -100f);
        new Tree((float) getX());
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
                new AntiAirTank((float) getX(), 170, 70f, "zsu57.png");
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

    private double getX() {
        double result = Math.random() * Gdx.graphics.getWidth();
        if (result < 200) result += 700;
        if (result > Gdx.graphics.getWidth() - 200) result -= 800;
        return result;
    }
}
