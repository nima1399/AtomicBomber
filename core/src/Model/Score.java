package Model;

import Controller.DataBaseCommands;
import Controller.GameWaves;

public class Score {
    private static Score loggedInSaveScore = null;
    private String username;
    private int kills = 0;
    private int hp = 2;
    private int clusterBombs = 0;
    private int nukes = 0;
    private float freezeBarWidth = 0;
    private int wave = 0;
    private int difficulty = 1;
    private int totalShots = 0;
    private int successfulShots = 0;

    public Score() {
        username = DataBaseCommands.getUsername();
        Airplane airplane = Airplane.getAirplane();
        kills = airplane.getKills();
        hp = airplane.getHp();
        clusterBombs = airplane.getClusterShots();
        nukes = airplane.getNuclearShots();
        freezeBarWidth = FreezeBar.getFreezeBar().getFreezeBarWidth();
        wave = GameWaves.getGameWaves().getWave();
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
