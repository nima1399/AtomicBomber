package View;

import Controller.DataBaseCommands;
import Model.ScoreBoardScore;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

public class ScoreBoardScreen implements Screen {
    SpriteBatch batch;
    Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    Stage stage = new Stage(new ScreenViewport());
    Label title = new Label("Score Board", skin);
    Window scoreBoard = new Window("High Score", skin);
    Window killBoard = new Window("Kill ", skin);
    Window hardnessBoard = new Window("Hardness", skin);
    Window accuracyBoard = new Window("Accuracy", skin);


    @Override
    public void show() {
        title.setFontScale(6);
        title.setSize(6 * title.getWidth(), 6 * title.getHeight());
        title.setColor(1, 1, 1, 1);
        title.setPosition(Gdx.graphics.getWidth() / 2 - title.getWidth() / 2, 950);
        stage.addActor(title);
        scoreBoard = new Window("High Score", skin);
        killBoard = new Window("Kill ", skin);
        hardnessBoard = new Window("Hardness", skin);
        accuracyBoard = new Window("Accuracy", skin);

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        int windowWidth = screenWidth / 4;
        int windowHeight = screenHeight * 3 / 4;

        scoreBoard.setSize(windowWidth, windowHeight);
        scoreBoard.setPosition(0, 100);

        killBoard.setSize(windowWidth, windowHeight);
        killBoard.setPosition(windowWidth, 100);

        hardnessBoard.setSize(windowWidth, windowHeight);
        hardnessBoard.setPosition(windowWidth * 2, 100);

        accuracyBoard.setSize(windowWidth, windowHeight);
        accuracyBoard.setPosition(windowWidth * 3, 100);
        //fill scoreBoard with users sorted by HighScore
        //clone users
        ArrayList<ScoreBoardScore> users = DataBaseCommands.getUsersList();
        //sort users by highScore and if equal base on wave
        users.sort((o1, o2) -> {
            if (o1.getScore() == o2.getScore()) {
                return o1.getWave() - o2.getWave();
            }
            return o2.getScore() - o1.getScore();
        });
        int rank = 1;
        for (ScoreBoardScore user : users) {
            Label label = new Label(user.getUsername() + " : " + user.getScore(), skin);
            labelColoring(label, rank, scoreBoard);
            if (rank == 10) {
                break;
            }
            rank++;
        }
        users.sort((o1, o2) -> {
            if (o1.getKills() == o2.getKills()) {
                return o1.getWave() - o2.getWave();
            }
            return o2.getKills() - o1.getKills();
        });
        rank = 1;
        for (ScoreBoardScore user : users) {
            Label label = new Label(user.getUsername() + " : " + user.getKills(), skin);
            labelColoring(label, rank, killBoard);
            if (rank == 10) {
                break;
            }
            rank++;
        }
        users.sort((o1, o2) -> {
            if (o1.getKills() * o1.getDifficulty() == o2.getKills() * o2.getDifficulty()) {
                return o1.getWave() - o2.getWave();
            }
            return o2.getKills() * o2.getDifficulty() - o1.getKills() * o1.getDifficulty();
        });
        rank = 1;
        for (ScoreBoardScore user : users) {
            Label label = new Label(user.getUsername() + " : " + user.getKills() * user.getDifficulty(), skin);
            labelColoring(label, rank, hardnessBoard);
            if (rank == 10) {
                break;
            }
            rank++;
        }
        float epsilon = 0.0001f; // threshold value
        users.sort((o1, o2) -> {
            if (Math.abs(o1.getAccuracy() - o2.getAccuracy()) < epsilon) {
                return o1.getWave() - o2.getWave();
            }
            return Float.compare(o2.getAccuracy(), o1.getAccuracy());
        });
        rank = 1;
        for (ScoreBoardScore user : users) {
            Label label = new Label(user.getUsername() + " : " + user.getAccuracy(), skin);

            if (rank == 10) {
                break;
            }
            rank++;
        }
        stage.addActor(scoreBoard);
        stage.addActor(killBoard);
        stage.addActor(hardnessBoard);
        stage.addActor(accuracyBoard);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 144f));
        stage.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuGameScreen());
        }
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

    private static void labelColoring(Label label, int rank, Window board) {
        if (rank == 1) {
            //gold
            label.setColor(1, 0.843f, 0, 1);
        } else if (rank == 2) {
            //silver
            label.setColor(0.75f, 0.75f, 0.75f, 1);
        } else if (rank == 3) {
            //bronze
            label.setColor(0.804f, 0.522f, 0.247f, 1);
        } else {
            label.setColor(1, 1, 1, 1);
        }
        label.setSize(300, 50);
        label.setFontScale(2);
        board.add(label).row();
    }
}