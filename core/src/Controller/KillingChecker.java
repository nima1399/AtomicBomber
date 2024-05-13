package Controller;

import Model.*;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class KillingChecker {
    public static boolean isOverlapping(EnemyObjects enemy, Rocket airplane) {
        Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        Rectangle rocketRect = new Rectangle(airplane.getX(), airplane.getY(), airplane.getWidth(), airplane.getHeight());

        return enemyRect.overlaps(rocketRect);
    }

    public static boolean isOverlapping(EnemyObjects enemy, Airplane airplane) {
        Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        Rectangle airplaneRect = new Rectangle(airplane.getX(), airplane.getY(), airplane.getWidth(), airplane.getHeight());

        return enemyRect.overlaps(airplaneRect);
    }

    public static boolean isOverlapping(Bullet bullet, Airplane airplane) {
        Rectangle enemyRect = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        Rectangle airplaneRect = new Rectangle(airplane.getX(), airplane.getY(), airplane.getWidth(), airplane.getHeight());

        return enemyRect.overlaps(airplaneRect);
    }

//    public static boolean isOverlapping(Bonus bonus, Airplane airplane) {
//        Rectangle enemyRect = new Rectangle(bonus.getX(), bonus.getY(), bonus.getWidth(), bonus.getHeight());
//        Rectangle airplaneRect = new Rectangle(airplane.getX(), airplane.getY(), airplane.getWidth(), airplane.getHeight());
//
//        return enemyRect.overlaps(airplaneRect);
//    }

    public static void processOverlap(float delta) {
        ArrayList<EnemyObjects> enemyObjectsCopy = new ArrayList<EnemyObjects>(EnemyObjects.getEnemyObjects());
        for (EnemyObjects enemy : enemyObjectsCopy) {
            if (isOverlapping(enemy, Airplane.getAirplane())) {
                Airplane airplane = Airplane.getAirplane();
                airplane.setExploded();
            }
            Rocket rocket = Rocket.getRocket();
            if (rocket != null && isOverlapping(enemy, rocket) && !enemy.isExploded()) {
                enemy.setExploded();
                rocket.setExploded();
                rocket.setEnemyHit();
                DataBaseCommands.setKills(DataBaseCommands.getKills() + enemy.getKillScore());
            }
            ArrayList<Rocket> cluster = Rocket.getCluster();
            if (cluster != null && cluster.size() > 0) {
                for (Rocket rocket2 : cluster) {
                    if (isOverlapping(enemy, rocket2) && !enemy.isExploded()) {
                        enemy.setExploded();
                        rocket2.setExploded();
                        rocket2.setEnemyHit();
                        DataBaseCommands.setKills(DataBaseCommands.getKills() + enemy.getKillScore());
                    }
                }
            }
            if (enemy.isExploded())
                enemy.updateExplosionTimer(delta);
        }
        Bullet bullet = Bullet.getBullet();
        if (bullet != null && isOverlapping(bullet, Airplane.getAirplane())) {
            Airplane.getAirplane().setExploded();
        }

    }
}