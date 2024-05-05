package Model;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Rocket {
    private static Rocket rocket;
    private float x;
    private float y;
    private int width;
    private int height;
    private Texture texture;
    private float xSpeed;
    private float ySpeed;
    private boolean isExploded;
    private Airplane airplane = Airplane.getAirplane();
    private float explosionTimer;

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
        rocket.setHeight(rocket.getTexture().getHeight());
        rocket.setWidth(rocket.getTexture().getWidth());
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

    public Texture getTexture() {
        return texture;
    }

    public void dispose() {
        texture.dispose();
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
        this.texture = new Texture("bigblast3.png");
        this.x += this.width / 2;
        this.y += this.height / 2;
        this.width = texture.getWidth() * 2;
        this.height = texture.getHeight() * 2;
        this.x -= this.width / 2;
        this.y -= this.height / 2;
        if (y < 170)
            y = 170;
        this.isExploded = true;
    }

    public void loadTexture() {
        int diameter = 30;
        Pixmap pixmap = new Pixmap(diameter, diameter, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fillCircle(diameter / 2, diameter / 2, diameter / 2);
        texture = new Texture(pixmap);
        pixmap.dispose();
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

}
