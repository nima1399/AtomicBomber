package Controller;

import Model.Airplane;
import Model.Rocket;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class UserObjectsController {


    public static void keyPress(float delta, Airplane airplane) {
        if (!airplane.getIsExploded()) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                airplane.setXSpeed(airplane.getXSpeed() - 10 * delta);
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                airplane.setXSpeed(airplane.getXSpeed() + 10 * delta);
            } else {
                if (airplane.getXSpeed() > 0) {
                    airplane.setXSpeed(airplane.getXSpeed() - 8 * delta);
                } else if (airplane.getXSpeed() < 0) {
                    airplane.setXSpeed(airplane.getXSpeed() + 8 * delta);
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                airplane.setYSpeed(airplane.getYSpeed() + 10 * delta);
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                airplane.setYSpeed(airplane.getYSpeed() - 10 * delta);
            } else {
                if (airplane.getYSpeed() > 0) {
                    airplane.setYSpeed(airplane.getYSpeed() - 8 * delta);
                } else if (airplane.getYSpeed() < 0) {
                    airplane.setYSpeed(airplane.getYSpeed() + 8 * delta);
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && Rocket.getRocket() == null) {
                Rocket.spawnRocket();
            }
        }
        processRocket(delta);

        airplane.setX(airplane.getX() + airplane.getXSpeed());
        airplane.setY(airplane.getY() + airplane.getYSpeed());

        if (airplane.getX() < -airplane.getWidth()) {
            airplane.setX(Gdx.graphics.getWidth());
        } else if (airplane.getX() > Gdx.graphics.getWidth()) {
            airplane.setX(-airplane.getWidth());
        } else if (airplane.getY() + airplane.getHeight() >= Gdx.graphics.getHeight()) {
            airplane.setY(Gdx.graphics.getHeight() - airplane.getHeight());
            airplane.setYSpeed(0);
        } else if (airplane.getY() < 170) {
            airplane.setY(170);
            airplane.setExploded();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            GameUtility.randomTankAdded();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            GameWaves gameWaves = GameWaves.getGameWaves();
            gameWaves.goToNextWave();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            if (airplane.getHp() == 1) {
                airplane.setHp(2);
            }
        }

    }

    private static void processRocket(float delta) {
        Rocket rocket = Rocket.getRocket();
        if (rocket == null)
            return;

        if (!rocket.isExploded()) {
            rocket.setX(rocket.getX() + rocket.getXSpeed());
            rocket.setYSpeed(rocket.getYSpeed() - 10 * delta);
            rocket.setY(rocket.getY() + rocket.getYSpeed());
        }

        if (rocket.getY() <= 170 && !rocket.isExploded())
            rocket.setExploded();
        else if (rocket.isExploded()) {
            rocket.setExplosionTimer(rocket.getExplosionTimer() + delta);
            if (rocket.getExplosionTimer() >= 0.5)
                rocket.dispose();
        }
    }
}
