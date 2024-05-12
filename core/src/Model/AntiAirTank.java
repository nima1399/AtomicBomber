package Model;

import Controller.DataBaseCommands;
import com.badlogic.gdx.Gdx;

public class AntiAirTank extends EnemyObjects {
    private boolean xIsLessThanAirplane;

    public AntiAirTank(float x, float y, float xSpeed, String texturePath) {
        super(texturePath, x, y, 4f, xSpeed, Airplane.getExplodedAnimation(), 6);
        if (Airplane.getAirplane().getX() < x) {
            xIsLessThanAirplane = true;
            textureRegion.flip(true, false);
        }
    }

    @Override
    public void update(float delta) {
        x += xSpeed * delta;
        if (x < 0 || x >= Gdx.graphics.getWidth() - width) {
            xSpeed *= -1;
        }
        if (Airplane.getAirplane().getX() < x && !xIsLessThanAirplane) {
            textureRegion.flip(true, false);
            xIsLessThanAirplane = true;
        } else if (Airplane.getAirplane().getX() > x && xIsLessThanAirplane) {
            textureRegion.flip(true, false);
            xIsLessThanAirplane = false;
        }

        shootBullet(x, y , width, height);
    }

    public static void shootBullet(float x, float y, int width, int height) {
        float distanceX = Airplane.getAirplane().getX() - x;
        float distanceY = Airplane.getAirplane().getY() - y;
        float distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        if (distance < 400 * DataBaseCommands.getDifficulty() && Bullet.getBullet() == null) {
            Bullet bullet = new Bullet(x + width / 2, y + height / 2, "bullet1.png");
            bullet.setDirection(Airplane.getAirplane().getX(), Airplane.getAirplane().getY());
        }
    }


}
