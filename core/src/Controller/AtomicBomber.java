package Controller;

import Model.Airplane;
import View.FirstGameScreen;
import View.LoginMenuScreen;
import View.MainMenuGameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import java.awt.*;
import java.time.temporal.Temporal;



public class AtomicBomber extends Game{
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
        music = Gdx.audio.newMusic(Gdx.files.internal("music\\sb_indreams(chosic.com).mp3"));
        music.setLooping(true);
        music.play();
        green = new ShapeRenderer();
        black = new ShapeRenderer();
    }

    public void render() {
        fpsPassed++;
        GameUtility.fullScreenToggle(fpsPassed);
        batch.begin();
        batch.draw(background, 0, 0, 2736, 1824);
        batch.end();

        green.setColor(Color.GREEN);
        green.begin(ShapeRenderer.ShapeType.Filled);
        green.rect(0, 0, 2736, 150);
        green.end();

        black.setColor(Color.BLACK);
        black.begin(ShapeRenderer.ShapeType.Filled);
        black.rect(0, 150, 2736, 20);
        black.end();

        super.render();
    }

    public void dispose() {
        batch.dispose();
        background.dispose();
        music.dispose();
    }

}
