package Controller;

import Model.Airplane;
import Model.Rocket;
import View.PauseGameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Collections;

public class UserObjectsController {


    public static void keyPress(float delta, Airplane airplane, Batch batch) {
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
                Rocket rocket = Rocket.spawnRocket(airplane.getXSpeed(), airplane.getYSpeed());
                Rocket.setRocket(rocket);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.C) && (Rocket.getCluster() == null || Rocket.getCluster().size() == 0)) {
                Rocket.spawnCluster();
            }
        }
        if (processRocket(delta, batch, Rocket.getRocket()))
            Rocket.getRocket().dispose();
        ArrayList<Rocket> toRemove = new ArrayList<>();
        if (Rocket.getCluster() != null) {
            for (Rocket rocket : Rocket.getCluster()) {
                if (processRocket(delta, batch, rocket))
                    toRemove.add(rocket);
            }
        }
        if (Rocket.getCluster() != null && toRemove.size() > 0)
            Rocket.getCluster().removeAll(toRemove);

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
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PauseGameScreen());
        }

    }

    private static Boolean processRocket(float delta, Batch batch, Rocket rocket) {
        if (rocket == null)
            return false;

        if (!rocket.isExploded()) {
            rocket.setX(rocket.getX() + rocket.getXSpeed());
            rocket.setYSpeed(rocket.getYSpeed() - 10 * delta);
            rocket.setY(rocket.getY() + rocket.getYSpeed());

            float angle = (float) Math.atan2(rocket.getYSpeed(), rocket.getXSpeed());
            Sprite sprite = new Sprite(rocket.getTexture().getKeyFrame(0));
            sprite.setPosition(rocket.getX(), rocket.getY());
            sprite.setRotation((float) Math.toDegrees(angle));
            sprite.setScale(2);
            sprite.draw(batch);
        } else {
            Texture currentFrame = rocket.getTexture().getKeyFrame(rocket.getElapsedTime(), true);
            batch.draw(currentFrame, rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
        }

        if (rocket.getY() <= 170 && !rocket.isExploded())
            rocket.setExploded();
        else if (rocket.isExploded()) {
            rocket.setExplosionTimer(rocket.getExplosionTimer() + delta);
            if (rocket.getExplosionTimer() >= 0.5) {
                Airplane airplane = Airplane.getAirplane();
                if (rocket.isEnemyHit()) {
                    airplane.setSuccessfulShots(airplane.getSuccessfulShots() + 1);
                }
                airplane.setTotalShots(airplane.getTotalShots() + 1);
                return true;
            }
        }
        return false;
    }
}
