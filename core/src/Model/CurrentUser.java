package Model;

public class CurrentUser {
    private static String username;

    public static String getUsername() {
        if (username == null) return "Guest";
        return username;
    }

    public static void setUsername(String username) {
        CurrentUser.username = username;
    }
}
