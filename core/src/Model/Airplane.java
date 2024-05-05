package Model;

import View.AfterGameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Airplane {
    private static Airplane airplane;
    private float x = 500;
    private float y = 500;
    private int width = 0;
    private int height = 0;
    private Texture texture;
    private Texture planeTexture;
    private Texture explodedTexture;
    private float xSpeed = 0;
    private float ySpeed = 0;
    private int hp = 2;
    private boolean isExploded = false;
    private float explosionTimer = 0;

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
        return texture;
    }

    public void dispose() {
        texture.dispose();
        planeTexture.dispose();
        explodedTexture.dispose();
        airplane = null;
    }

    public void loadTexture() {
        planeTexture = new Texture("plane.png");
        texture = planeTexture;
        explodedTexture = new Texture("fire1.png");
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
        width = texture.getWidth() * 5;
        height = texture.getHeight() * 5;
        xSpeed = 0;
        ySpeed = 0;
        isExploded = true;
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
                width = texture.getWidth() * 5;
                height = texture.getHeight() * 5;
                explosionTimer = 0;
            } else {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new AfterGameScreen());
            }
        }
    }
}
