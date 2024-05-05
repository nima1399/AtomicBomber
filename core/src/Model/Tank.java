package Model;

import com.badlogic.gdx.graphics.Texture;

public class Tank extends EnemyObjects{

    public Tank(float x, float xSpeed, int difficulty) {
        super("tank1.png", x, 170, 4f, xSpeed * difficulty, "bigblast4.png", 2);
    }


}
