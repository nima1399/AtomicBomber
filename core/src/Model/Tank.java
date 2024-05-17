package Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Tank extends EnemyObjects {
    public Tank(float x, float xSpeed, int difficulty) {
        super("tank1.png", x, 170, 4f, xSpeed * difficulty, Airplane.getExplodedAnimation(), 2);
    }
}
