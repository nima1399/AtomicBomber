package Model;

public class Building extends EnemyObjects {
    public Building(float x) {
        super("building.png", x, 170, 4f, 0, Airplane.getExplodedAnimation(), 3);
    }
}
