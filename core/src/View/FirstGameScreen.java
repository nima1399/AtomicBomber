package View;

import Controller.DataBaseCommands;
import Controller.GameWaves;
import Controller.UserObjectsController;
import Model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static Controller.KillingChecker.processOverlap;

public class FirstGameScreen implements Screen {

    SpriteBatch batch = new SpriteBatch();
    Airplane airplane = Airplane.getAirplane();
    Texture background;
    Rocket rocket = Rocket.getRocket();
    GameWaves gameWaves = new GameWaves();
    private static boolean waveFinished;
    private float waveFinishedTimer = 0;
    private BitmapFont font;
    Bullet bullet;
    float timePassed = 0;
    float migTime = (float) 2.5 * (5 - DataBaseCommands.getDifficulty());

    @Override
    public void show() {
        background = new Texture("sky.png");
        if (airplane != null)
            airplane.dispose();
        airplane = new Airplane();
        airplane.loadTexture();
        airplane.setHeight(airplane.getTexture().getHeight() * 5);
        airplane.setWidth(airplane.getTexture().getWidth() * 5);
        if (gameWaves.getWave() == 0)
            gameWaves.goToNextWave();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(5);
        waveFinished = false;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        UserObjectsController.keyPress(delta, airplane, batch);
        batch.draw(airplane.getTexture(), airplane.getX(), airplane.getY(), airplane.getWidth(), airplane.getHeight());
        if (airplane.getIsExploded()) {
            airplane.updateExplosionTimer(delta);
        }
        font.draw(batch, "Kills: " + DataBaseCommands.getKills() + "\nWave: " + GameWaves.getGameWaves().getWave(), 50, Gdx.graphics.getHeight() - 150);
        if (waveFinished) {
            font.draw(batch, "Accuracy: " + airplane.getAccuracy() + "%", 50, Gdx.graphics.getHeight() - 50);
            waveFinishedTimer += delta;
        }
        if (waveFinishedTimer > 3) {
            waveFinished = false;
            waveFinishedTimer = 0;
        }
        bullet = Bullet.getBullet();
        if (bullet != null) {
            bullet.update(delta);
            bullet.draw(batch);
        }
        boolean enemyObjectsExist = false;
        Mig mig = null;
        for (EnemyObjects enemyObjects : EnemyObjects.getEnemyObjects()) {
            if (!(enemyObjects instanceof Mig)) {
                batch.draw(enemyObjects.getTextureRegion(), enemyObjects.getX(), enemyObjects.getY(), enemyObjects.getWidth(), enemyObjects.getHeight());
                enemyObjects.update(delta);
                enemyObjectsExist = true;
            } else {
                mig = (Mig) enemyObjects;
            }
        }
        if (mig != null) {
            batch.draw(mig.getTextureRegion(), mig.getX(), mig.getY(), mig.getWidth(), mig.getHeight());
            mig.update(delta);
            enemyObjectsExist = true;
        } else if (GameWaves.getGameWaves().getWave() == 3){
            timePassed += delta;
            if (timePassed >= migTime) {
                new Mig(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 2, -200, "mig1.png");
                timePassed = 0;
            }
            if (timePassed >= migTime - 3)
                font.draw(batch, "Mig Is Coming", Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 + 200);
        }
        if (!enemyObjectsExist)
            gameWaves.goToNextWave();
        processOverlap(delta);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        airplane.dispose();
        background.dispose();
    }

    public static void setWaveFinished() {
        waveFinished = true;
    }
}
