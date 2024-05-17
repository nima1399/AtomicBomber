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

    public static boolean isOverlapping(Bonus bonus, Airplane airplane) {
        Rectangle enemyRect = new Rectangle(bonus.getX(), bonus.getY(), bonus.getWidth(), bonus.getHeight());
        Rectangle airplaneRect = new Rectangle(airplane.getX(), airplane.getY(), airplane.getWidth(), airplane.getHeight());

        return enemyRect.overlaps(airplaneRect);
    }

    public static void processOverlap(float delta) {
        ArrayList<EnemyObjects> enemyObjectsCopy = new ArrayList<EnemyObjects>(EnemyObjects.getEnemyObjects());
        Airplane airplane = Airplane.getAirplane();
        for (EnemyObjects enemy : enemyObjectsCopy) {
            if (isOverlapping(enemy, airplane) && !airplane.getIsExploded()) {
                airplane.setExploded();
            }
            Rocket rocket = Rocket.getRocket();
            if (rocket != null && isOverlapping(enemy, rocket) && !enemy.isExploded()) {
                enemy.setExploded();
                rocket.setExploded();
                rocket.setEnemyHit();
                if (enemy instanceof Bunker) {
                    new Bonus(enemy.getX(), enemy.getY(), "bonuscluster.png");
                } else if (enemy instanceof Building) new Bonus(enemy.getX(), enemy.getY(), "bonusnuke.png");
                Airplane.getAirplane().setKills(Airplane.getAirplane().getKills() + enemy.getKillScore());
            }
            rocket = Rocket.getNuclearRocket();
            if (rocket != null && isOverlapping(enemy, rocket) && !enemy.isExploded()) {
                enemy.setExploded();
                rocket.setExploded();
                rocket.setEnemyHit();
                if (enemy instanceof Bunker) {
                    new Bonus(enemy.getX(), enemy.getY(), "bonuscluster.png");
                } else if (enemy instanceof Building) new Bonus(enemy.getX(), enemy.getY(), "bonusnuke.png");
                Airplane.getAirplane().setKills(Airplane.getAirplane().getKills() + enemy.getKillScore());
            }
            ArrayList<Rocket> cluster = Rocket.getCluster();
            if (cluster != null && cluster.size() > 0) {
                for (Rocket rocket2 : cluster) {
                    if (isOverlapping(enemy, rocket2) && !enemy.isExploded()) {
                        enemy.setExploded();
                        if (enemy instanceof Bunker) {
                            new Bonus(enemy.getX(), enemy.getY(), "bonuscluster.png");
                        } else if (enemy instanceof Building) new Bonus(enemy.getX(), enemy.getY(), "bonusnuke.png");
                        rocket2.setExploded();
                        rocket2.setEnemyHit();
                        Airplane.getAirplane().setKills(Airplane.getAirplane().getKills() + enemy.getKillScore());
                    }
                }
            }
            if (enemy.isExploded()) enemy.updateExplosionTimer(delta);
        }
        Bullet bullet = Bullet.getBullet();
        if (bullet != null && isOverlapping(bullet, Airplane.getAirplane())) {
            Airplane.getAirplane().setExploded();
        }

        ArrayList<Bonus> bonuses = Bonus.getBonusInstance();
        ArrayList<Bonus> deletedBonuses = new ArrayList<>();
        for (Bonus bonus : bonuses) {
            if (isOverlapping(bonus, Airplane.getAirplane())) {
                deletedBonuses.add(bonus);
                if (bonus.isCluster()) airplane.setClusterShots(airplane.getClusterShots() + 1);
                else airplane.setNuclearShots(airplane.getNuclearShots() + 1);
            }
        }
        bonuses.removeAll(deletedBonuses);
    }
}