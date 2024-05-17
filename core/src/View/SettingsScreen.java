package View;

import Controller.DataBaseCommands;
import Controller.GameUtility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingsScreen implements Screen {
    Stage stage;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        // add a listener to the buttons
        TextButton easy = UIBlocks.textButtonMaker("Easy", skin, root);
        easy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                DataBaseCommands.setDifficulty("easy");
            }
        });

        TextButton medium = UIBlocks.textButtonMaker("Medium", skin, root);
        medium.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                DataBaseCommands.setDifficulty("medium");
            }
        });

        TextButton hard = UIBlocks.textButtonMaker("Hard", skin, root);
        hard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                DataBaseCommands.setDifficulty("hard");
            }
        });

        TextButton toggleMute = UIBlocks.textButtonMaker("Mute / Unmute", skin, root);
        toggleMute.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameUtility.toggleMute();
            }
        });

        UIBlocks.textButtonMaker("White / Black", skin, root);

        TextButton wasdControls = UIBlocks.textButtonMaker("WASD", skin, root);
        wasdControls.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                DataBaseCommands.setControls("wasd");
            }
        });

        TextButton arrowControls = UIBlocks.textButtonMaker("Arrows", skin, root);
        arrowControls.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                DataBaseCommands.setControls("arrows");
            }
        });

        UIBlocks.navigationTextButtonMaker("Back", skin, root, "MainMenuGameScreen");
    }

    @Override
    public void render(float delta) {

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
        stage.dispose();
    }
}
