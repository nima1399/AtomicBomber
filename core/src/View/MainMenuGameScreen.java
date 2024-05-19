package View;

import Controller.DataBaseCommands;
import Controller.GameWaves;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuGameScreen implements Screen {
    Stage stage;
    Batch batch = new SpriteBatch();
    Texture avatar;

    @Override
    public void show() {
        stage = new Stage();
        if (GameWaves.getGameWaves() != null) GameWaves.getGameWaves().setWave(0);
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        UIBlocks.navigationTextButtonMaker("New Game", skin, root, "FirstGameScreen");
        if (!DataBaseCommands.getUsername().equals("Guest")) {
            TextButton continueGame = UIBlocks.textButtonMaker("Continue Game", skin, root);
            continueGame.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    DataBaseCommands.loadGame();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new FirstGameScreen());
                }
            });
        }
        UIBlocks.navigationTextButtonMaker("Profile Menu", skin, root, "ProfileMenuScreen");
        UIBlocks.navigationTextButtonMaker("ScoreBoard", skin, root, "ScoreBoardScreen");
        UIBlocks.navigationTextButtonMaker("Settings", skin, root, "SettingsScreen");
        UIBlocks.navigationTextButtonMaker("Exit", skin, root, "Exit");

        avatar = new Texture(Gdx.files.internal(DataBaseCommands.getAvatar()));
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(avatar, 100, Gdx.graphics.getHeight() - 300, 200, 200);
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        font.draw(batch, DataBaseCommands.getUsername(), 400, Gdx.graphics.getHeight() + font.getXHeight() / 2 - 200);
        batch.end();

        stage.act();
        stage.draw();
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
        stage.dispose();
    }

}
