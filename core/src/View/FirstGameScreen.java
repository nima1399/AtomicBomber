package View;

import Controller.DataBaseCommands;
import Controller.GameWaves;
import Model.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Controller.UserObjectsController;

import static Controller.KillingChecker.processOverlap;

public class FirstGameScreen implements Screen {

	SpriteBatch batch = new SpriteBatch();
	Airplane airplane = Airplane.getAirplane();
	Texture background;
	Rocket rocket = Rocket.getRocket();
	GameWaves gameWaves = new GameWaves();
	private BitmapFont font;
	Bullet bullet;

	@Override
	public void show() {
		background = new Texture("sky.png");
		if (airplane != null)
			airplane.dispose();
		airplane = new Airplane();
		airplane.loadTexture();
		airplane.setHeight(airplane.getTexture().getHeight() * 5);
		airplane.setWidth(airplane.getTexture().getWidth() * 5);
		gameWaves.spawnEnemies();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(5);
	}

	@Override
	public void render(float delta) {
		batch.begin();
		UserObjectsController.keyPress(delta, airplane);
		batch.draw(airplane.getTexture(), airplane.getX(), airplane.getY(), airplane.getWidth(), airplane.getHeight());
		if (airplane.getIsExploded()) {
			airplane.updateExplosionTimer(delta);
		}
		rocket = Rocket.getRocket();
		if (rocket != null) {
			batch.draw(rocket.getTexture(), rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
		}
		font.draw(batch, "Kills: " + DataBaseCommands.getKills() + "\nWave: " + GameWaves.getGameWaves().getWave(), 50, Gdx.graphics.getHeight() - 150);
		bullet = Bullet.getBullet();
		if (bullet != null) {
			bullet.update(delta);
			bullet.draw(batch);
		}
		boolean enemyObjectsExist = false;
		AntiAirTankOrMig antiAirTankOrMig = null;
		for (EnemyObjects enemyObjects : EnemyObjects.getEnemyObjects()) {
			if (!(enemyObjects instanceof AntiAirTankOrMig)) {
				batch.draw(enemyObjects.getTextureRegion(), enemyObjects.getX(), enemyObjects.getY(), enemyObjects.getWidth(), enemyObjects.getHeight());
				enemyObjects.update(delta);
				enemyObjectsExist = true;
			} else {
				antiAirTankOrMig = (AntiAirTankOrMig) enemyObjects;
			}
		}
		if (antiAirTankOrMig != null) {
			batch.draw(antiAirTankOrMig.getTextureRegion(), antiAirTankOrMig.getX(), antiAirTankOrMig.getY(), antiAirTankOrMig.getWidth(), antiAirTankOrMig.getHeight());
			antiAirTankOrMig.update(delta);
			enemyObjectsExist = true;
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
}
