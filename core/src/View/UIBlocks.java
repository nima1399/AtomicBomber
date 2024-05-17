package View;

import Controller.DataBaseCommands;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class UIBlocks {
    public static TextButton textButtonMaker(String text, Skin skin, Table root) {
        TextButton textButton = new TextButton(text, skin);
        root.row();
        textButton.getLabel().setFontScale(1.5f);
        root.add(textButton).width(240).height(60).align(Align.center).pad(10).row();
        return textButton;
    }

    public static void navigationTextButtonMaker(String text, Skin skin, Table root, String eventListenerMenu) {
        TextButton textButton = textButtonMaker(text, skin, root);
        Screen screen;
        switch (eventListenerMenu) {
            case "FirstGameScreen":
                screen = new FirstGameScreen();
                break;
            case "MainMenuGameScreen":
                screen = new MainMenuGameScreen();
                break;
            case "Exit":
                screen = null;
                break;
            case "ProfileMenuScreen":
                screen = new ProfileMenuScreen();
                break;
            case "ChangeUsernameOrPassword":
                screen = new ChangeUsernameOrPasswordScreen();
                break;
            case "DeleteAccount":
                DataBaseCommands.deleteAccount();
            case "Logout":
                DataBaseCommands.logout();
                screen = new LoginMenuScreen();
                break;
            case "AvatarMenu":
                screen = new AvatarMenuScreen();
                break;
            case "SettingsScreen":
                screen = new SettingsScreen();
                break;
            case "ScoreBoardScreen":
                screen = new ScoreBoardScreen();
                break;
            default:
                return;
        }


        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (screen == null) Gdx.app.exit();
                else ((Game) Gdx.app.getApplicationListener()).setScreen(screen);
            }
        });
    }

    public static TextField textFieldMaker(String text, Skin skin, Table root) {
        TextField textField = new TextField(text, skin);
        root.add(textField).width(240).height(60).align(Align.center).pad(10).row();
        if (text.equals("Password")) {
            textField.setPasswordCharacter('*');
            textField.setPasswordMode(true);
        }
        return textField;
    }

    public static void avatarImageButtonMaker(String avatarPath, Table root) {
        Texture texture = new Texture(Gdx.files.internal(avatarPath));
        Image avatar = new Image(texture);
        avatar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                DataBaseCommands.setAvatar(avatarPath);
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuGameScreen());
            }
        });
        root.add(avatar).width(200).height(200).pad(10).row();
    }
}
