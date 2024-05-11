package Model;

import Controller.DataBaseCommands;
import com.badlogic.gdx.Gdx;

public class AntiAirTankOrMig extends EnemyObjects {
    private boolean xIsLessThanAirplane;
    boolean isMig = false;
    public AntiAirTankOrMig(float x, float y, float xSpeed, String texturePath, int killScore) {
        super(texturePath, x, y, 4f, xSpeed, Airplane.getExplodedAnimation(), killScore);
        if (killScore == 5) {
            if (Airplane.getAirplane().getX() < x) {
                xIsLessThanAirplane = true;
                textureRegion.flip(true, false);
            }
        } else {
            isMig = true;
        }
    }

    @Override
    public void update(float delta) {
        x += xSpeed * delta;
        if (!isMig) {
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
        } else {
            if (x < 0) {
                this.dispose();
            }
        }

        float distanceX = Airplane.getAirplane().getX() - x;
        float distanceY = Airplane.getAirplane().getY() - y;
        float distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (distance < 400 * DataBaseCommands.getDifficulty() && Bullet.getBullet() == null) {
            shootBullet();
        }
    }

    private void shootBullet() {
        Bullet bullet = new Bullet(x + width / 2, y + height / 2, "bullet1.png");
        bullet.setDirection(Airplane.getAirplane().getX(), Airplane.getAirplane().getY());
    }


}
