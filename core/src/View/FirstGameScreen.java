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

import java.util.ArrayList;

import static Controller.KillingChecker.processOverlap;

public class FirstGameScreen implements Screen {

    SpriteBatch batch = new SpriteBatch();
    Airplane airplane = Airplane.getAirplane();
    Texture background;
    GameWaves gameWaves = GameWaves.getGameWaves();
    private static boolean waveFinished;
    private float waveFinishedTimer = 0;
    private BitmapFont font;
    Bullet bullet;
    float timePassed = 0;
    float migTime = (float) 2.5 * (5 - DataBaseCommands.getDifficulty());
    FreezeBar freezeBar;
    boolean isFreeze;
    Texture freezeEffect;
    Boolean wasdControls = false;

    @Override
    public void show() {
        background = new Texture("sky.png");
        if (airplane == null) airplane = new Airplane();
        airplane.loadTexture();
        airplane.setHeight(airplane.getTexture().getHeight() * 5);
        airplane.setWidth(airplane.getTexture().getWidth() * 5);
        DataBaseCommands.loadGame();
        if (gameWaves == null) gameWaves = new GameWaves();
        if (GameWaves.getWave() == 0 || (Score.getLoggedInSaveScore() != null && GameWaves.getWave() == Score.getLoggedInSaveScore().getWave()))
            gameWaves.goToNextWave();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(5);
        waveFinished = false;
        freezeBar = new FreezeBar();
        isFreeze = FreezeBar.getFreezeBar().getFreeze();
        freezeEffect = new Texture("freezeScreen.png");
        if (DataBaseCommands.getControls().equals("wasd")) wasdControls = true;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        UserObjectsController.keyPress(delta, airplane, batch, wasdControls);
        batch.draw(airplane.getTexture(), airplane.getX(), airplane.getY(), airplane.getWidth(), airplane.getHeight());
        if (airplane.getIsExploded()) {
            airplane.updateExplosionTimer(delta);
        }
        font.draw(batch, "Kills: " + Airplane.getAirplane().getKills() + "\nCluster: " + airplane.getClusterShots() + "\nNuke: " + airplane.getNuclearShots() + "\nWave: " + GameWaves.getWave(), 50, Gdx.graphics.getHeight() - 150);
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
                if (!isFreeze) enemyObjects.update(delta);
                enemyObjectsExist = true;
            } else {
                mig = (Mig) enemyObjects;
            }
        }
        if (mig != null) {
            batch.draw(mig.getTextureRegion(), mig.getX(), mig.getY(), mig.getWidth(), mig.getHeight());
            if (!isFreeze) mig.update(delta);
            enemyObjectsExist = true;
        } else if (GameWaves.getWave() == 3 && !isFreeze) {
            timePassed += delta;
            if (timePassed >= migTime) {
                new Mig(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 2, -200, "mig1.png");
                timePassed = 0;
            }
            if (timePassed >= migTime - 3)
                font.draw(batch, "Mig Is Coming", Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 + 200);
        }
        if (!enemyObjectsExist) gameWaves.goToNextWave();

        processOverlap(delta);
        freezeBar.render(batch);
        freezeBar.update(delta);
        if (isFreeze) {
            batch.setColor(1, 1, 1, 0.2f);
            batch.draw(freezeEffect, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.setColor(1, 1, 1, 1);
        }
        ArrayList<Bonus> bonuses = Bonus.getBonusInstance();
        for (Bonus bonus : bonuses) {
            bonus.draw(batch);
            bonus.update(delta);
        }
        batch.end();
        isFreeze = FreezeBar.getFreezeBar().getFreeze();
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
