package Model;

import View.AfterGameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;


public class Airplane {
    private static Airplane airplane;
    private float x = 500;
    private float y = 500;
    private int width = 0;
    private int height = 0;
    private Animation<Texture> texture;
    private Animation<Texture> planeTexture;
    private Animation<Texture> explodedTexture;
    private float xSpeed = 0;
    private float ySpeed = 0;
    private int hp = 2;
    private boolean isExploded = false;
    private float explosionTimer = 0;
    private int totalShots = 0;
    private int successfulShots = 0;

    public Airplane() {
        Airplane.airplane = this;
    }

    public static Airplane getAirplane() {
        return airplane;
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

    public Texture getTexture() {
        return texture.getKeyFrame(explosionTimer);
    }

    public static Animation getExplodedAnimation() {
        return airplane.explodedTexture;
    }

    public void dispose() {
        airplane = null;
    }

    public void loadTexture() {
        planeTexture = new Animation<>(1f, new Texture("plane.png"));
        texture = planeTexture;
        explodedTexture = new Animation<>(0.4f, new Texture("fire1.png"), new Texture("fire2.png"), new Texture("fire3.png"));
    }

    public float getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(float xSpeed) {
        if (xSpeed > 20) {
            this.xSpeed = 20;
        } else this.xSpeed = Math.max(xSpeed, -20);
    }

    public float getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(float ySpeed) {
        if (ySpeed > 20) {
            this.ySpeed = 20;
        } else this.ySpeed = Math.max(ySpeed, -20);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean getIsExploded() {
        return isExploded;
    }

    public void setExploded() {
        texture = explodedTexture;
        width = texture.getKeyFrame(0).getWidth() * 5;
        height = texture.getKeyFrame(0).getHeight() * 5;
        xSpeed = 0;
        ySpeed = 0;
        isExploded = true;
    }

    public void setTotalShots(int totalShots) {
        this.totalShots = totalShots;
    }

    public int getTotalShots() {
        return totalShots;
    }

    public void setSuccessfulShots(int successfulShots) {
        this.successfulShots = successfulShots;
    }

    public int getSuccessfulShots() {
        return successfulShots;
    }

    public int getAccuracy() {
        if (totalShots == 0) {
            return 0;
        }
        return successfulShots * 100 / totalShots;
    }

    public void updateExplosionTimer(float delta) {
        explosionTimer += delta;
        if (explosionTimer >= 1) {
            hp--;
            if (hp > 0) {
                x = 500;
                y = 500;
                isExploded = false;
                texture = planeTexture;
                width = texture.getKeyFrame(0).getWidth() * 5;
                height = texture.getKeyFrame(0).getHeight() * 5;
                explosionTimer = 0;
            } else {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new AfterGameScreen());
            }
        }
    }
}
