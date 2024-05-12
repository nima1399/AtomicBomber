package Model;

import Controller.DataBaseCommands;
import com.badlogic.gdx.Gdx;

public class Mig extends EnemyObjects {

    public Mig(float x, float y, float xSpeed, String texturePath) {
        super(texturePath, x, y, 4f, xSpeed, Airplane.getExplodedAnimation(), 5);
    }

    @Override
    public void update(float delta) {
        x += xSpeed * delta;
        if (x < 0) {
            this.dispose();
        }

        AntiAirTank.shootBullet(x, y, width, height);
    }




}
