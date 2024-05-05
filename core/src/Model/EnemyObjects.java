package Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

// this class is superclass for all enemy objects
// including tank truck tree bunker and structure
public abstract class EnemyObjects {
    protected static ArrayList<EnemyObjects> enemyObjects = new ArrayList<>();
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected float multiplier;
    protected Texture texture;
    protected Texture fireTexture;
    protected TextureRegion textureRegion;
    protected float xSpeed;
    protected boolean isExploded = false;
    protected float explosionTimer = 0;
    protected int killScore;

    public EnemyObjects(String texturePath, float x, float y, float multiplier, float xSpeed, String fireTexturePath, int killScore) {
        enemyObjects.add(this);
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        texture = new Texture(texturePath);
        textureRegion = new TextureRegion(texture);
        if (xSpeed < 0)
            textureRegion.flip(true, false);
        this.width = (int) (texture.getWidth() * multiplier);
        this.height = (int) (texture.getHeight() * multiplier);
        if (fireTexturePath != null) {
            fireTexture = new Texture(fireTexturePath);
        }
        this.killScore = killScore;
    }

    public static ArrayList<EnemyObjects> getEnemyObjects() {
        return enemyObjects;
    }

    public float getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
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

    public int getHeight() {
        return height;
    }

    public void dispose() {
        texture.dispose();
        if (fireTexture != null)
            fireTexture.dispose();
        textureRegion.getTexture().dispose();
        enemyObjects.remove(this);
    }

    public void update(float delta) {
        x += xSpeed * delta;
        if (x <= 0 || x >= Gdx.graphics.getWidth() - width) {
            xSpeed *= -1;
            textureRegion.flip(true, false);
        }
        if (x <= 0)
            x = 0;
        if (x >= Gdx.graphics.getWidth() - width)
            x = Gdx.graphics.getWidth() - width;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setExploded() {
        if (fireTexture == null) {
            dispose();
            return;
        }
        textureRegion = new TextureRegion(fireTexture);
        xSpeed = 0;
        isExploded = true;
    }

    public void updateExplosionTimer(float delta) {
        explosionTimer += delta;
        if (explosionTimer >= 1)
            dispose();
    }

    public int getKillScore() {
        return killScore;
    }
}
