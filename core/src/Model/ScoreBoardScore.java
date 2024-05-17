package Model;

public class ScoreBoardScore {
    String username;
    int score;
    int kills;
    int difficulty;
    int wave;
    int accuracy;

    public ScoreBoardScore(String username, int score, int kills, int difficulty, int wave, int accuracy) {
        this.username = username;
        this.score = score;
        this.kills = kills;
        this.difficulty = difficulty;
        this.wave = wave;
        this.accuracy = accuracy;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public int getKills() {
        return kills;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getWave() {
        return wave;
    }

    public int getAccuracy() {
        return accuracy;
    }
}
