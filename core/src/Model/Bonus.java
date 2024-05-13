package Model;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Bonus {
    private static ArrayList<Bonus> bonusInstance = new ArrayList<>();
    private float x;
    private float y;
    private int width;
    private int height;
    private Texture texture;
    private float ySpeed = 10;
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
    }

    public void dispose() {
        if (isCluster)
            Airplane.getAirplane().setClusterShots(Airplane.getAirplane().getClusterShots() + 1);
        texture.dispose();
        bonusInstance.remove(this);
    }
}
