package Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Bonus {
    private static ArrayList<Bonus> bonusInstance = new ArrayList<>();
    private float x;
    private float y;
    private int width;
    private int height;
    private Texture texture;
    private float xSpeed = 0;
    private int xAcceleration = 100;
    private float ySpeed = 100;
    private boolean isCluster = false;

    public Bonus(float x, float y, String texturePath) {
        this.x = x;
        this.y = y;
        if (texturePath.equals("bonuscluster.png")) {
            isCluster = true;
        }
        texture = loadTexture(texturePath);
        this.width = texture.getWidth() * 2;
        this.height = texture.getWidth() * 2;
        bonusInstance.add(this);
    }

    private Texture loadTexture(String texturePath) {
        return new Texture(texturePath);
    }

    public void update(float delta) {
        y += ySpeed * delta;
        x += xSpeed * delta;
        if (xSpeed == 100 || xSpeed == -100) xAcceleration *= -1;
        xSpeed += xAcceleration * delta;
        if (xSpeed > 100) xSpeed = 100;
        else if (xSpeed < -100) xSpeed = -100;
    }

    public void dispose() {
        if (isCluster) Airplane.getAirplane().setClusterShots(Airplane.getAirplane().getClusterShots() + 1);
        else Airplane.getAirplane().setNuclearShots(Airplane.getAirplane().getNuclearShots() + 1);
        texture.dispose();
        bonusInstance.remove(this);
    }

    public static ArrayList<Bonus> getBonusInstance() {
        return bonusInstance;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isCluster() {
        return isCluster;
    }
}
