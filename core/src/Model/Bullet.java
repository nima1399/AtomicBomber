package Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
    private static Bullet bullet = null;
    private float x, y;
    private float dx, dy;
    private float speed = 1000f;
    private Sprite sprite;

    public Bullet(float x, float y, String texturePath) {
        this.x = x;
        this.y = y;
        this.sprite = new Sprite(new Texture(texturePath));
        this.sprite.setPosition(x, y);
        this.sprite.setScale(2f);
        bullet = this;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public void setDirection(float targetX, float targetY) {
        float distance = (float) Math.sqrt((targetX - x) * (targetX - x) + (targetY - y) * (targetY - y));
        this.dx = (targetX - x) / distance;
        this.dy = (targetY - y) / distance;
        sprite.setRotation((float) Math.toDegrees(Math.atan2(dy, dx)));
    }

    public void update(float delta) {
        x += dx * speed * delta;
        y += dy * speed * delta;
        if (x > Gdx.graphics.getWidth() || x < 0 || y > Gdx.graphics.getHeight() || y <= 170) {
            bullet.dispose();
        }
        sprite.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public static Bullet getBullet() {
        return bullet;
    }

    public void dispose() {
        sprite.getTexture().dispose();
        bullet = null;
    }


}