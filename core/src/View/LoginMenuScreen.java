package View;

import Controller.DataBaseCommands;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoginMenuScreen implements Screen {
    // this screen is for the login menu and getting the username and password
    Texture background;
    Stage stage;
    Batch batch = new SpriteBatch();

    TextField username;
    TextField password;
    TextButton login;
    TextButton register;
    TextButton guest;

    @Override
    public void show() {
        DataBaseCommands.logout();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        username = UIBlocks.textFieldMaker("Username", skin, root);
        password = UIBlocks.textFieldMaker("Password", skin, root);
        login = UIBlocks.textButtonMaker("Login", skin, root);
        login.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String result = DataBaseCommands.login(username.getText(), password.getText());
                switch (result) {
                    case "loginSuccess":
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuGameScreen());
                        break;
                    case "userNotFound":
                        Dialog dialog = new Dialog("Error", skin);
                        dialog.text("User not found");
                        dialog.button("OK");
                        dialog.show(stage);
                        break;
                    case "wrongPassword":
                        Dialog dialog2 = new Dialog("Error", skin);
                        dialog2.text("Wrong password");
                        dialog2.button("OK");
                        dialog2.show(stage);
                        break;
                }
            }
        });

        register = UIBlocks.textButtonMaker("Register", skin, root);
        register.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String result = DataBaseCommands.register(username.getText(), password.getText());
                switch (result) {
                    case "registerSuccess":
                        Dialog dialog = new Dialog("Success", skin);
                        dialog.text("User registered");
                        dialog.button("OK");
                        dialog.show(stage);
                        break;
                    case "userExists":
                        Dialog dialog2 = new Dialog("Error", skin);
                        dialog2.text("User already exists");
                        dialog2.button("OK");
                        dialog2.show(stage);
                        break;
                }
            }
        });

        guest = UIBlocks.textButtonMaker("Guest", skin, root);
        guest.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                DataBaseCommands.getRandomAvatar();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new FirstGameScreen());
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
        batch.dispose();
        background.dispose();
        stage.dispose();
    }

}