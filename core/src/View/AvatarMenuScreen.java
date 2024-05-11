package View;

import Controller.DataBaseCommands;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class AvatarMenuScreen implements Screen {
    Stage stage;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        UIBlocks.avatarImageButtonMaker("avatar1.png", root);
        UIBlocks.avatarImageButtonMaker("avatar2.png", root);
        UIBlocks.avatarImageButtonMaker("avatar3.png", root);
        TextField avatarPath = UIBlocks.textFieldMaker("avatarPath", skin, root);
        TextButton save = UIBlocks.textButtonMaker("Save", skin, root);
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                DataBaseCommands.setAvatar(avatarPath.getText());
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
