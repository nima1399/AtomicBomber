package View;

import Controller.DataBaseCommands;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class ChangeUsernameOrPasswordScreen implements Screen {

    Stage stage;

    TextField username;
    TextField password;
    TextButton ChangeUsernameAndPassword;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        UIBlocks.textFieldMaker("Username", skin, root);
        UIBlocks.textFieldMaker("Password", skin, root);

        ChangeUsernameAndPassword = UIBlocks.textButtonMaker("Change Username And Password", skin, root);
        ChangeUsernameAndPassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String result = DataBaseCommands.changeUserNameAndPassword(username.getText(), password.getText());
                switch (result) {
                    case "changeSuccess":
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuGameScreen());
                        break;
                    case "YouAreGuest":
                        Dialog dialog = new Dialog("Error", skin);
                        dialog.text("You are a guest, you can't change your username or password");
                        dialog.button("OK");
                        dialog.show(stage);
                        break;
                    case "userExists":
                        Dialog dialog2 = new Dialog("Error", skin);
                        dialog2.text("A user with this username already exists");
                        dialog2.button("OK");
                        dialog2.show(stage);
                        break;
                }
            }
        });
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
