package Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Rocket {
    private static Rocket rocket;
    private float x;
    private float y;
    private int width;
    private int height;
    private Animation<Texture> texture;
    private float xSpeed;
    private float ySpeed;
    private boolean isExploded;
    private Airplane airplane = Airplane.getAirplane();
    private float explosionTimer;
    private boolean enemyHit = false;

    private Rocket() {
        x = airplane.getX() + airplane.getWidth() / 2;
        y = airplane.getY();
        width = 0;
        height = 0;
        xSpeed = airplane.getXSpeed();
        ySpeed = airplane.getYSpeed();
        isExploded = false;
        explosionTimer = 0;
    }

    public static Rocket getRocket() {
        return rocket;
    }

    public static Rocket spawnRocket() {
        rocket = new Rocket();
        rocket.loadTexture();
        rocket.setHeight(rocket.getTexture().getKeyFrame(0).getHeight() * 2);
        rocket.setWidth(rocket.getTexture().getKeyFrame(0).getWidth() * 2);
        return rocket;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Animation<Texture> getTexture() {
        return texture;
    }

    public void dispose() {
        rocket = null;
    }

    public float getExplosionTimer() {
        return explosionTimer;
    }

    public void setExplosionTimer(float explosionTimer) {
        this.explosionTimer = explosionTimer;
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setExploded() {
        this.texture = getExplosionAnimation();
        this.x += this.width / 2;
        this.y += this.height / 2;
        this.width = texture.getKeyFrame(0).getWidth() * 2;
        this.height = texture.getKeyFrame(0).getHeight() * 2;
        this.x -= this.width / 2;
        this.y -= this.height / 2;
        if (y < 170)
            y = 170;
        this.isExploded = true;
    }

    public boolean isEnemyHit() {
        return enemyHit;
    }

    public void setEnemyHit() {
        enemyHit = true;
    }

    public void loadTexture() {
        texture = new Animation<>(0.1f, new Texture("rocket1.png"));
        texture.setPlayMode(Animation.PlayMode.LOOP);
    }


    public float getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public Animation<Texture> getExplosionAnimation() {
        return new Animation<>(0.1f, new Texture("bigblast1.png"), new Texture("bigblast2.png"), new Texture("bigblast3.png"), new Texture("bigblast4.png"));
    }

    public float getElapsedTime() {
        return explosionTimer;
    }
}
