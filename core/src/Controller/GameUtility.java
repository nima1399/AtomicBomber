package Controller;

import Model.Tank;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;

public class GameUtility {
    public static void toggleMute() {
        AtomicBomber atomicBomber = (AtomicBomber) Gdx.app.getApplicationListener();
        if (atomicBomber.music.isPlaying()) {
            atomicBomber.music.pause();
        } else {
            atomicBomber.music.play();
        }
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
