package Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Tank extends EnemyObjects {
    public Tank(float x, float xSpeed, int difficulty) {
        super("tank1.png", x, 170, 4f, xSpeed * difficulty, getDeathAnimation(), 2);
    }

    private static Animation getDeathAnimation() {
        return new Animation<Texture>(0.4f, new Texture("deadtank1.png"), new Texture("deadtank2.png"), new Texture("deadtank3.png"));

    }
}
