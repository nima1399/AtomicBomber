//package view;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.*;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.mygdx.game.AtomicBomber;
//
//public class LoginScreen implements Screen {
//    AtomicBomber atomicBomber;
//    SpriteBatch batch;
//    Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
//    Stage stage = new Stage(new ScreenViewport());
//    Texture background = new Texture("background.png");
//    Animation<Texture> animation = new Animation<Texture>(1 / 30f, );
//    Label title = new Label("Atomic Bomber", skin);
//    Label usernameLabel = new Label("Username", skin);
//    Label passwordLabel = new Label("Password", skin);
//    TextField username = new TextField("Username", skin);
//    TextField password = new TextField("Password", skin);
//    TextButton login = new TextButton("Login", skin);
//
//    Table root = new Table();
//
//    public LoginScreen(AtomicBomber atomicBomber) {
//        this.atomicBomber = atomicBomber;
//        batch = atomicBomber.batch;
//    }
//
//    @Override
//    public void show() {
//        Gdx.input.setInputProcessor(stage);
//        root.setFillParent(true);
//        stage.addActor(title);
//        stage.addActor(root);
//        title.setFontScale(6);
//        title.setColor(1, 1, 1, 1);
//        usernameLabel.setFontScale(4);
//        usernameLabel.setColor(1, 1, 1, 1);
//        passwordLabel.setFontScale(4);
//        passwordLabel.setColor(1, 1, 1, 1);
//        title.setPosition(650, Gdx.graphics.getHeight() - title.getHeight() - 200);
//        root.add(usernameLabel).height(50).width(300).padTop(600).row();
//        root.add(username).height(50).width(300).padTop(10).row();
//        stage.setKeyboardFocus(username);
//        username.selectAll();
//        password.setPasswordCharacter('*');
//        password.setPasswordMode(true);
//        root.add(passwordLabel).height(50).width(300).padTop(20).row();
//        root.add(password).height(50).width(300).padTop(10).row();
//        root.add(login).width(150).height(40).padBottom(500);
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        batch.end();
//        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//        stage.draw();
//        if (login.isPressed()) {
//            System.out.println(username.getText());
//            System.out.println(password.getText());
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
//            Gdx.app.exit();
//        }
//    }
//
//    @Override
//    public void resize(int width, int height) {
//    }
//
//    @Override
//    public void pause() {
//    }
//
//    @Override
//    public void resume() {
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//    }
//}