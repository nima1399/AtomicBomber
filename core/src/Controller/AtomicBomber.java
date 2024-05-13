package Controller;

import View.LoginMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class AtomicBomber extends Game {
    int fpsPassed = 60;
    Batch batch;
    Texture background;
    Music music;
    private ShapeRenderer green;
    private ShapeRenderer black;

    @Override
    public void create() {
        setScreen(new LoginMenuScreen());
        batch = new SpriteBatch();
        background = new Texture("sky.png");
        GameUtility.setMusic("music\\sb_indreams(chosic.com).mp3");
        green = new ShapeRenderer();
        black = new ShapeRenderer();
    }

    public void render() {
        fpsPassed++;
        GameUtility.fullScreenToggle(fpsPassed);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        green.setColor(Color.GREEN);
        green.begin(ShapeRenderer.ShapeType.Filled);
        green.rect(0, 0, Gdx.graphics.getWidth(), 150);
        green.end();

        black.setColor(Color.BLACK);
        black.begin(ShapeRenderer.ShapeType.Filled);
        black.rect(0, 150, Gdx.graphics.getWidth(), 20);
        black.end();

        super.render();
    }

    public void dispose() {
        batch.dispose();
        background.dispose();
        music.dispose();
    }

}
