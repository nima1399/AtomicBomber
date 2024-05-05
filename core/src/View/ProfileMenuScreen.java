package View;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


public class ProfileMenuScreen implements Screen {

    Stage stage;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        UIBlocks.navigationTextButtonMaker("Change Username Or Password", skin, root, "ChangeUsernameOrPassword");
        UIBlocks.navigationTextButtonMaker("Delete Account", skin, root, "DeleteAccount");
        UIBlocks.navigationTextButtonMaker("Logout", skin, root, "Logout");
        UIBlocks.navigationTextButtonMaker("Avatar Menu", skin, root, "AvatarMenu");
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
        stage.dispose();
    }


}
