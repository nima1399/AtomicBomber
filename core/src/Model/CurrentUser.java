package Model;

public class CurrentUser {
    private static String username;

    public static String getUsername() {
        System.out.println("Username: hooooooooy");
        if (username == null) return "Guest";
        System.out.println("Username: " + username);
        return username;
    }

    public static void setUsername(String username) {
        CurrentUser.username = username;
    }
}
