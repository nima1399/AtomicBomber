package Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class DataBaseCommands {
    public static String login(String username, String password) {
        Preferences userPref = Gdx.app.getPreferences("Users");
        String savedPassword = userPref.getString(username, "userNotFound");
        if (savedPassword.equals(password)) {
            Preferences currentUserPref = Gdx.app.getPreferences("CurrentUser");
            currentUserPref.putString("currentUser", username);
            currentUserPref.flush();
            return "loginSuccess";
        } else if (savedPassword.equals("userNotFound")) {
            return "userNotFound";
        } else {
            return "wrongPassword";
        }
    }

    public static String register(String username, String password) {
        Preferences userPref = Gdx.app.getPreferences("Users");
        String savedPassword = userPref.getString(username, "userNotFound");
        if (savedPassword.equals("userNotFound")) {
            userPref.putString(username, password);
            userPref.flush();
            getRandomAvatar();
            return "registerSuccess";
        } else {
            return "userExists";
        }
    }

    public static void logout() {
        Preferences userPref = Gdx.app.getPreferences("CurrentUser");
        userPref.remove("currentUser");
        userPref.flush();
    }

    public static String changeUserNameAndPassword(String newUsername, String newPassword) {
        Preferences userPref = Gdx.app.getPreferences("Users");
        Preferences currentUserPref = Gdx.app.getPreferences("CurrentUser");
        String currentUsername = currentUserPref.getString("currentUser", "Guest");
        if (userPref.contains(newUsername)) {
            return "userExists";
        } else {
            userPref.remove(currentUsername);
            userPref.putString(newUsername, newPassword);
            userPref.flush();
            currentUserPref.remove("currentUser");
            currentUserPref.putString("currentUser", newUsername);
            currentUserPref.flush();
            return "changeSuccess";
        }
    }

    public static void deleteAccount() {
        Preferences userPref = Gdx.app.getPreferences("Users");
        Preferences currentUserPref = Gdx.app.getPreferences("CurrentUser");
        String currentUsername = currentUserPref.getString("currentUser", "Guest");
        userPref.remove(currentUsername);
        userPref.flush();
        currentUserPref.remove("currentUser");
        currentUserPref.flush();
    }

    public static String getAvatar() {
        Preferences currentUserPref = Gdx.app.getPreferences("CurrentUser");
        Preferences avatarPref = Gdx.app.getPreferences("Avatars");
        String currentUsername = currentUserPref.getString("currentUser", "Guest");
        return avatarPref.getString(currentUsername, "avatar1.png");
    }

    public static void setAvatar(String avatar) {
        Preferences currentUserPref = Gdx.app.getPreferences("CurrentUser");
        String currentUsername = currentUserPref.getString("currentUser", "Guest");
        Preferences avatarPref = Gdx.app.getPreferences("Avatars");
        avatarPref.putString(currentUsername, avatar);
        avatarPref.flush();
    }

    public static void getRandomAvatar() {
        Preferences currentUserPref = Gdx.app.getPreferences("CurrentUser");
        Preferences avatarPref = Gdx.app.getPreferences("Avatars");
        String currentUsername = currentUserPref.getString("currentUser", "Guest");
        int random = (int) (Math.random() * 3) + 1;
        avatarPref.putString(currentUsername, "avatar" + random + ".png");
        avatarPref.flush();
    }

    public static String getUsername() {
        Preferences currentUserPref = Gdx.app.getPreferences("CurrentUser");
        return currentUserPref.getString("currentUser", "Guest");
    }

    public static int getDifficulty() {
        Preferences currentUserPref = Gdx.app.getPreferences("CurrentUser");
        String currentUsername = currentUserPref.getString("currentUser", "Guest");
        Preferences difficultyPref = Gdx.app.getPreferences("Difficulty");
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
        Preferences currentUserPref = Gdx.app.getPreferences("CurrentUser");
        String currentUsername = currentUserPref.getString("currentUser", "Guest");
        Preferences difficultyPref = Gdx.app.getPreferences("Difficulty");
        difficultyPref.putString(currentUsername, difficulty);
        difficultyPref.flush();
    }

    public static int getKills() {
        Preferences currentUserPref = Gdx.app.getPreferences("CurrentUser");
        String currentUsername = currentUserPref.getString("currentUser", "Guest");
        Preferences currentUserKillsPref = Gdx.app.getPreferences("Kills");
        return currentUserKillsPref.getInteger(currentUsername, 0);
    }

    public static void setKills(int kills) {
        Preferences currentUserPref = Gdx.app.getPreferences("CurrentUser");
        String currentUsername = currentUserPref.getString("currentUser", "Guest");
        Preferences currentUserKillsPref = Gdx.app.getPreferences("Kills");
        currentUserKillsPref.putInteger(currentUsername, kills);
    }
}
