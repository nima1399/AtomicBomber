package Controller;

import Model.Tank;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;

public class GameUtility {
    private static Music music;

    public static void setMusic(String musicPath) {
        if (music != null)
            music.stop();
        music = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
        music.setLooping(true);
        music.play();
    }

    public static void toggleMute() {
        if (music.isPlaying()) {
            music.pause();
        } else {
            music.play();
        }
    }

    public static void muteMusic() {
        music.stop();
    }

    public static void fullScreenToggle(int fpsPassed) {
        if (Gdx.input.isKeyPressed(Input.Keys.F) && fpsPassed > 60) {
            fpsPassed = 0;
            Graphics graphics = Gdx.graphics;
            boolean fullscreen = !Gdx.graphics.isFullscreen();
            Graphics.DisplayMode desktopMode = graphics.getDisplayMode();
            if (fullscreen) {
                Gdx.graphics.setFullscreenMode(desktopMode);
            } else {
                Gdx.graphics.setWindowedMode(2000, 1500);
            }
        }
    }

    public static void toggleTheme() {
    }

    public static void randomTankAdded() {
        int randomX = (int) (Math.random() * Gdx.graphics.getWidth());
        new Tank(randomX, 70f, DataBaseCommands.getDifficulty());
    }
}
