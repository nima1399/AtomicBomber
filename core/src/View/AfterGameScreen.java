package View;

import Controller.DataBaseCommands;
import Controller.GameWaves;
import Model.Airplane;
import Model.Bonus;
import Model.Score;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class AfterGameScreen implements Screen {
    Stage stage;
    String hasWon;

    @Override
    public void show() {
        Bonus.getBonusInstance().clear();
        DataBaseCommands.saveScore();
        DataBaseCommands.deleteSave();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        if (Airplane.getAirplane().getHp() > 0) {
            hasWon = "You Win";
        } else {
            hasWon = "Game Lost";
        }
        UIBlocks.textButtonMaker(hasWon, skin, root);
        UIBlocks.textButtonMaker("Last Wave: " + GameWaves.getGameWaves().getWave(), skin, root);
        UIBlocks.textButtonMaker("Kills: " + Airplane.getAirplane().getKills(), skin, root);
        UIBlocks.textButtonMaker("Accuracy: " + Airplane.getAirplane().getAccuracy() + "%", skin, root);
        TextButton exit = UIBlocks.textButtonMaker("Go Back To Main-menu", skin, root);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Airplane.getAirplane().dispose();
                Score.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuGameScreen());
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
