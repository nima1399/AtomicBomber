package Model;

public class Bunker extends EnemyObjects {
    public Bunker(float x) {
        super("bunker1.png", x, 170, 4f, 0, Airplane.getExplodedAnimation(), 4);
    }

    @Override
    public void setExploded() {
        super.setExploded();

    }
}
