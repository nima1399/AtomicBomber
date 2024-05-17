package Model;

public class Mig extends EnemyObjects {

    public Mig(float x, float y, float xSpeed, String texturePath) {
        super(texturePath, x, y, 4f, xSpeed, Airplane.getExplodedAnimation(), 5);
    }

    @Override
    public void update(float delta) {
        x += xSpeed * delta;
        if (x < -width) {
            this.dispose();
        }

        if (!isExploded) AntiAirTank.shootBullet(x, y, width, height);
    }
}
