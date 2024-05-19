package Controller;

import Model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;

public class DataBaseCommands {
    static Preferences userPref = Gdx.app.getPreferences("Users");
    static Preferences usersListPref = Gdx.app.getPreferences("UsersList");
    static Preferences avatarPref = Gdx.app.getPreferences("Avatars");
    static Preferences difficultyPref = Gdx.app.getPreferences("Difficulty");
    static Preferences controlsPref = Gdx.app.getPreferences("Controls");

    public static String login(String username, String password) {
        String savedPassword = userPref.getString(username, "userNotFound");
        if (savedPassword.equals(password)) {
            CurrentUser.setUsername(username);
            return "loginSuccess";
        } else if (savedPassword.equals("userNotFound")) {
            return "userNotFound";
        } else {
            return "wrongPassword";
        }
    }

    public static String register(String username, String password) {
        String savedPassword = userPref.getString(username, "userNotFound");
        if (savedPassword.equals("userNotFound")) {
            int usersCount = usersListPref.getInteger("usersCount", 0);
            usersListPref.putInteger("usersCount", usersCount + 1);
            usersListPref.putString(String.valueOf(usersCount), username);
            usersListPref.flush();
            userPref.putString(username, password);
            userPref.flush();
            getRandomAvatar();
            return "registerSuccess";
        } else {
            return "userExists";
        }
    }

    public static void logout() {
        System.out.println("hhhjkgj");
        CurrentUser.setUsername("Guest");
    }

    public static String changeUserNameAndPassword(String newUsername, String newPassword) {
        String currentUsername = CurrentUser.getUsername();
        if (userPref.contains(newUsername)) {
            return "userExists";
        } else {
            userPref.remove(currentUsername);
            userPref.putString(newUsername, newPassword);
            userPref.flush();
            CurrentUser.setUsername(newUsername);
            String avatar = avatarPref.getString(currentUsername, "avatar1.png");
            avatarPref.remove(currentUsername);
            avatarPref.putString(newUsername, avatar);
            avatarPref.flush();
            String difficulty = difficultyPref.getString(currentUsername, "easy");
            difficultyPref.remove(currentUsername);
            difficultyPref.putString(newUsername, difficulty);
            difficultyPref.flush();
            String controls = controlsPref.getString(currentUsername, "arrows");
            controlsPref.remove(currentUsername);
            controlsPref.putString(newUsername, controls);
            controlsPref.flush();
            Preferences savedGamePref = Gdx.app.getPreferences("savedGame-" + currentUsername);
            if (savedGamePref.getBoolean("gameSaved", false)) {
                Preferences newSavedGamePref = Gdx.app.getPreferences("savedGame-" + newUsername);
                newSavedGamePref.putInteger("kills", savedGamePref.getInteger("kills", 0));
                newSavedGamePref.putInteger("hp", savedGamePref.getInteger("hp", 2));
                newSavedGamePref.putInteger("clusterBombs", savedGamePref.getInteger("clusterBombs", 0));
                newSavedGamePref.putInteger("nukes", savedGamePref.getInteger("nukes", 0));
                newSavedGamePref.putFloat("freezeBarWidth", savedGamePref.getFloat("freezeBarWidth", 0));
                newSavedGamePref.putInteger("wave", savedGamePref.getInteger("wave", 0));
                newSavedGamePref.putBoolean("gameSaved", true);
                newSavedGamePref.putInteger("difficulty", savedGamePref.getInteger("difficulty", 1));
                newSavedGamePref.putInteger("totalShots", savedGamePref.getInteger("totalShots", 0));
                newSavedGamePref.putInteger("successfulShots", savedGamePref.getInteger("successfulShots", 0));
                newSavedGamePref.flush();
                savedGamePref.putBoolean("gameSaved", false);
                savedGamePref.flush();
            }
            Preferences saveScorePref = Gdx.app.getPreferences("saveScore-" + currentUsername);
            if (saveScorePref.getInteger("score", 0) != 0) {
                Preferences newSaveScorePref = Gdx.app.getPreferences("saveScore-" + newUsername);
                newSaveScorePref.putInteger("score", saveScorePref.getInteger("score", 0));
                newSaveScorePref.putInteger("kills", saveScorePref.getInteger("kills", 0));
                newSaveScorePref.putInteger("wave", saveScorePref.getInteger("wave", 0));
                newSaveScorePref.putInteger("difficulty", saveScorePref.getInteger("difficulty", 1));
                newSaveScorePref.putInteger("accuracy", saveScorePref.getInteger("accuracy", 0));
                newSaveScorePref.flush();
                saveScorePref.putInteger("score", 0);
                saveScorePref.flush();
            }

            return "changeSuccess";
        }
    }

    public static void deleteAccount() {
        String currentUsername = CurrentUser.getUsername();
        userPref.remove(currentUsername);
        userPref.flush();
        System.out.println("sadasdasd");
        CurrentUser.setUsername("Guest");
    }

    public static String getAvatar() {
        String currentUsername = CurrentUser.getUsername();
        return avatarPref.getString(currentUsername, "avatar1.png");
    }

    public static void setAvatar(String avatar) {
        String currentUsername = CurrentUser.getUsername();
        avatarPref.putString(currentUsername, avatar);
        avatarPref.flush();
    }

    public static void getRandomAvatar() {
        String currentUsername = CurrentUser.getUsername();
        int random = (int) (Math.random() * 3) + 1;
        avatarPref.putString(currentUsername, "avatar" + random + ".png");
        avatarPref.flush();
    }

    public static String getUsername() {
        return CurrentUser.getUsername();
    }

    public static int getDifficulty() {
        String currentUsername = CurrentUser.getUsername();
        String difficulty = difficultyPref.getString(currentUsername, "easy");
        switch (difficulty) {
            case "easy":
                return 1;
            case "medium":
                return 2;
            default:
                return 3;
        }
    }

    public static void setDifficulty(String difficulty) {
        String currentUsername = CurrentUser.getUsername();
        difficultyPref.putString(currentUsername, difficulty);
        difficultyPref.flush();
    }

    public static void setControls(String controls) {
        String currentUsername = CurrentUser.getUsername();
        controlsPref.putString(currentUsername, controls);
        controlsPref.flush();
    }

    public static String getControls() {
        String currentUsername = CurrentUser.getUsername();
        return controlsPref.getString(currentUsername, "arrows");
    }

    public static void saveGame() {
        Score score = Score.getLoggedInSaveScore();
        Preferences savedGamePref = Gdx.app.getPreferences("savedGame-" + score.getUsername());
        savedGamePref.putInteger("kills", score.getKills());
        savedGamePref.putInteger("hp", score.getHp());
        savedGamePref.putInteger("clusterBombs", score.getClusterBombs());
        savedGamePref.putInteger("nukes", score.getNukes());
        savedGamePref.putFloat("freezeBarWidth", score.getFreezeBarWidth());
        savedGamePref.putInteger("wave", score.getWave());
        savedGamePref.putBoolean("gameSaved", true);
        savedGamePref.putInteger("difficulty", score.getDifficulty());
        savedGamePref.putInteger("totalShots", score.getTotalShots());
        savedGamePref.putInteger("successfulShots", score.getSuccessfulShots());
        savedGamePref.flush();
    }

    public static void deleteSave() {
        Preferences savedGamePref = Gdx.app.getPreferences("savedGame-" + getUsername());
        savedGamePref.putBoolean("gameSaved", false);
        savedGamePref.flush();
    }

    public static void loadGame() {
        Preferences savedGamePref = Gdx.app.getPreferences("savedGame-" + getUsername());
        if (!savedGamePref.getBoolean("gameSaved", false)) {
            new Score(0, 2, 0, 0, 0, 0, 0, 0, 0);
            return;
        }
        int kills = savedGamePref.getInteger("kills", 0);
        int hp = savedGamePref.getInteger("hp", 2);
        int clusterBombs = savedGamePref.getInteger("clusterBombs", 0);
        int nukes = savedGamePref.getInteger("nukes", 0);
        float freezeBarWidth = savedGamePref.getFloat("freezeBarWidth", 0);
        int wave = savedGamePref.getInteger("wave", 0);
        int difficulty = savedGamePref.getInteger("difficulty", 1);
        if (difficulty == 1) setDifficulty("easy");
        else if (difficulty == 2) setDifficulty("medium");
        else setDifficulty("hard");
        int totalShots = savedGamePref.getInteger("totalShots", 0);
        int successfulShots = savedGamePref.getInteger("successfulShots", 0);
        savedGamePref.putBoolean("gameSaved", false);
        savedGamePref.flush();
        new Score(kills, hp, clusterBombs, nukes, freezeBarWidth, wave, difficulty, totalShots, successfulShots);
        Airplane airplane = Airplane.getAirplane();
        if (airplane == null) airplane = new Airplane();
        GameWaves.getGameWaves().setWave(wave);
        airplane.setKills(kills);
        airplane.setHp(hp);
        airplane.setClusterShots(clusterBombs);
        airplane.setNuclearShots(nukes);
        airplane.setTotalShots(totalShots);
        airplane.setSuccessfulShots(successfulShots);

        FreezeBar.getFreezeBar().setFreezeBarWidth(freezeBarWidth);
    }

    public static void saveScore() {
        Score score = Score.getLoggedInSaveScore();
        Preferences saveScore = Gdx.app.getPreferences("saveScore-" + score.getUsername());
        int playerScore;
        if (score.getTotalShots() != 0)
            playerScore = score.getKills() * score.getSuccessfulShots() * 100 / score.getTotalShots();
        else playerScore = 0;
        if (saveScore.getInteger("score", 0) <= playerScore) {
            saveScore.putInteger("score", playerScore);
            saveScore.putInteger("kills", score.getKills());
            saveScore.putInteger("wave", score.getWave());
            saveScore.putInteger("difficulty", score.getDifficulty());
            saveScore.putInteger("accuracy", Airplane.getAirplane().getAccuracy());
            saveScore.flush();
        }
    }

    public static ArrayList<ScoreBoardScore> getUsersList() {
//        Preferences usersListPref = Gdx.app.getPreferences("UsersList");
        ArrayList<ScoreBoardScore> usersList = new ArrayList<>();
        for (int i = 0; i < usersListPref.getInteger("usersCount", 0); i++) {
            Preferences saveScore = Gdx.app.getPreferences("saveScore-" + usersListPref.getString(String.valueOf(i)));
            String username = usersListPref.getString(String.valueOf(i));
//            Preferences userPref = Gdx.app.getPreferences("Users");
            if (userPref.getString(username, "userNotFound").equals("userNotFound")) {
                continue;
            }
            int score = saveScore.getInteger("score", 0);
            int kills = saveScore.getInteger("kills", 0);
            int wave = saveScore.getInteger("wave", 0);
            int difficulty = saveScore.getInteger("difficulty", 1);
            int accuracy = saveScore.getInteger("accuracy", 0);
            if (score != 0) usersList.add(new ScoreBoardScore(username, score, kills, difficulty, wave, accuracy));
        }
        for (int i = 0; i < usersList.size(); i++) {
            for (int j = i + 1; j < usersList.size(); j++) {
                if (usersList.get(i).getUsername().equals(usersList.get(j).getUsername())) {
                    usersList.remove(j);
                    j--;
                }
            }
        }
        return usersList;
    }
}
