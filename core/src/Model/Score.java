package Model;

import Controller.DataBaseCommands;
import Controller.GameWaves;

public class Score {
    private static Score loggedInSaveScore = null;
    private final String username;
    private final int kills;
    private final int hp;
    private final int clusterBombs;
    private final int nukes;
    private final float freezeBarWidth;
    private final int wave;
    private final int difficulty;
    private final int totalShots;
    private final int successfulShots;

    public Score() {
        username = DataBaseCommands.getUsername();
        Airplane airplane = Airplane.getAirplane();
        kills = airplane.getKills();
        hp = airplane.getHp();
        clusterBombs = airplane.getClusterShots();
        nukes = airplane.getNuclearShots();
        freezeBarWidth = FreezeBar.getFreezeBar().getFreezeBarWidth();
        wave = GameWaves.getWave();
        difficulty = DataBaseCommands.getDifficulty();
        totalShots = airplane.getTotalShots();
        successfulShots = airplane.getSuccessfulShots();
        loggedInSaveScore = this;
    }

    public Score(int kills, int hp, int clusterBombs, int nukes, float freezeBarWidth, int wave, int difficulty, int totalShots, int successfulShots) {
        this.username = getUsername();
        this.kills = kills;
        this.hp = hp;
        this.clusterBombs = clusterBombs;
        this.nukes = nukes;
        this.freezeBarWidth = freezeBarWidth;
        this.wave = wave;
        this.difficulty = difficulty;
        this.successfulShots = successfulShots;
        this.totalShots = totalShots;
        loggedInSaveScore = this;
    }

    public static Score getLoggedInSaveScore() {
        return loggedInSaveScore;
    }

    public String getUsername() {
        return username;
    }

    public int getKills() {
        return kills;
    }

    public int getHp() {
        return hp;
    }

    public int getClusterBombs() {
        return clusterBombs;
    }

    public int getNukes() {
        return nukes;
    }

    public float getFreezeBarWidth() {
        return freezeBarWidth;
    }

    public int getWave() {
        return wave;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getTotalShots() {
        return totalShots;
    }

    public int getSuccessfulShots() {
        return successfulShots;
    }

    public static void dispose() {
        loggedInSaveScore = null;
    }
}
