package Model;

import Controller.GameUtility;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.nio.channels.MulticastChannel;
import java.util.ArrayList;

public class Rocket {
    private static Rocket rocketInstance;
    private static ArrayList<Rocket> clusterInstance;
    private static Rocket nuclearRocketInstance;
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

    private Rocket(float xSpeed, float ySpeed) {
        x = airplane.getX() + airplane.getWidth() / 2;
        y = airplane.getY();
        width = 0;
        height = 0;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        isExploded = false;
        explosionTimer = 0;
    }

    public static Rocket getRocket() {
        return rocketInstance;
    }

    public static Rocket spawnRocket(float xSpeed, float ySpeed) {
        Rocket rocket = new Rocket(xSpeed, ySpeed);
        rocket.loadTexture();
        rocket.setHeight(rocket.getTexture().getKeyFrame(0).getHeight() * 2);
        rocket.setWidth(rocket.getTexture().getKeyFrame(0).getWidth() * 2);
        return rocket;
    }

    public static ArrayList<Rocket> getCluster() {
        return clusterInstance;
    }

    public static ArrayList<Rocket> spawnCluster() {
        ArrayList<Rocket> cluster = new ArrayList<>();
        Airplane airplane = Airplane.getAirplane();
        for (int i = -4; i < 5; i += 2)
            cluster.add(spawnRocket(airplane.getXSpeed() + i, airplane.getYSpeed()));
        clusterInstance = cluster;
        return cluster;
    }

    public static Rocket spawnNuclearRocket() {
        Rocket rocket = new Rocket(0, 0);
        rocket.texture = new Animation<>(0.1f, new Texture("sam.png"));
        rocket.texture.setPlayMode(Animation.PlayMode.LOOP);
        nuclearRocketInstance = rocket;
        rocket.setHeight(rocket.getTexture().getKeyFrame(0).getHeight() * 2);
        rocket.setWidth(rocket.getTexture().getKeyFrame(0).getWidth() * 2);
        return rocket;
    }

    public static void setRocket(Rocket rocket) {
        Rocket.rocketInstance = rocket;
    }

    public static Rocket getNuclearRocket() {
        return nuclearRocketInstance;
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
        if (this == rocketInstance)
            rocketInstance = null;
        if (this == nuclearRocketInstance)
            nuclearRocketInstance = null;
        if (clusterInstance != null)
            clusterInstance.remove(this);
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
        GameUtility.playExplosionMusic();
        this.x += this.width / 2;
        this.y += this.height / 2;
        if (this == nuclearRocketInstance) {
            this.width = texture.getKeyFrame(0).getWidth() * 5;
            this.height = texture.getKeyFrame(0).getHeight() * 5;
        } else {
            this.width = texture.getKeyFrame(0).getWidth() * 2;
            this.height = texture.getKeyFrame(0).getHeight() * 2;
        }
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
        if (this == nuclearRocketInstance) {
            return new Animation<>(0.1f, new Texture("nuketop1.png"), new Texture("nuketop3.png"), new Texture("nuketop4.png"));
        } else
            return new Animation<>(0.1f, new Texture("bigblast1.png"), new Texture("bigblast2.png"), new Texture("bigblast3.png"), new Texture("bigblast4.png"));
    }

    public float getElapsedTime() {
        return explosionTimer;
    }
}
