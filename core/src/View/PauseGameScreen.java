package View;

import Controller.DataBaseCommands;
import Controller.GameUtility;
import Model.Bonus;
import Model.Score;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PauseGameScreen implements Screen {
    // TODO: Add Musics
    Stage stage;

    @Override
    public void show() {
        Bonus.getBonusInstance().clear();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        if (!DataBaseCommands.getUsername().equals("Guest")) {
            TextButton saveGame = UIBlocks.textButtonMaker("Save Game", skin, root);
            saveGame.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    DataBaseCommands.saveGame();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuGameScreen());
                }
            });
        }
        TextButton exitGame = UIBlocks.textButtonMaker("Exit Game", skin, root);
        exitGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Score.dispose();
                DataBaseCommands.deleteSave();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuGameScreen());
            }
        });


        TextButton muteMusic = UIBlocks.textButtonMaker("Mute Music", skin, root);
        muteMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameUtility.muteMusic();
            }
        });

        TextButton showControls = UIBlocks.textButtonMaker("Show Controls", skin, root);
        showControls.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Dialog dialog = new Dialog("Controls", skin);
                dialog.text("Move: Arrow Keys or wasd\nShoot: Space\nPause: Esc\nRadioactive: R\nCluster: C\nFreeze: Tab");
                dialog.button("OK");
                dialog.show(stage);
            }
        });

        TextButton music1 = UIBlocks.textButtonMaker("Music 1", skin, root);
        music1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameUtility.setMusic("music\\sb_indreams(chosic.com).mp3");
            }
        });

        TextButton music2 = UIBlocks.textButtonMaker("Music 2", skin, root);
        music2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameUtility.setMusic("music\\Top Gun.mp3");
            }
        });

        TextButton music3 = UIBlocks.textButtonMaker("Music 3", skin, root);
        music3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameUtility.setMusic("music\\lady-of-the-80x27s-128379.mp3");
            }
        });

        Gdx.input.setInputProcessor(stage);
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

    }
}
