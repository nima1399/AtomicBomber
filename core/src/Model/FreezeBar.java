package Model;

import Controller.DataBaseCommands;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class FreezeBar {
    private static FreezeBar freezeBarInstance;
    private boolean isFreeze = false;
    private Texture freezeBar = new Texture("freezeBar.png");
    private Sprite freezeBarSprite = new Sprite(freezeBar);
    private float freezeBarWidth = 0;
    private float freezeBarHeight = 20;
    private int lastKillCount;
    private float freezeBarX = 0;
    private ShapeRenderer red = new ShapeRenderer();

    public FreezeBar() {
        freezeBarSprite.setSize(freezeBarWidth, freezeBarHeight);
        lastKillCount = 0;
        freezeBarInstance = this;
    }

    public void render(SpriteBatch batch) {
        freezeBarSprite.setPosition(20, 200);
        freezeBarSprite.setSize(freezeBarWidth, freezeBarHeight);
        freezeBarSprite.draw(batch);
    }

    public void update(float delta) {
        int diff = DataBaseCommands.getKills() - lastKillCount;
        lastKillCount = DataBaseCommands.getKills();
        if (!isFreeze) {
            if (diff > 0) {
                float oldWidth = freezeBarWidth;
                freezeBarWidth += diff * 10;
                if (freezeBarWidth > 500) {
                    freezeBarWidth = 500;
                }
                float widthChange = freezeBarWidth - oldWidth;
                freezeBarX -= widthChange;
            }
        } else {
            freezeBarWidth -= 20 * delta;
            if (freezeBarWidth <= 0) {
                freezeBarWidth = 0;
                isFreeze = false;
            }
        }
        freezeBarSprite.setPosition(freezeBarX, Gdx.graphics.getHeight() - 100);
        freezeBarSprite.setSize(freezeBarWidth, freezeBarHeight);
    }

    public void toggleFreeze() {
        isFreeze = !isFreeze;
    }

    public boolean getFreeze() {
        return isFreeze;
    }

    public static FreezeBar getFreezeBar() {
        return freezeBarInstance;
    }
}