package cinema.authentication;

public class PasswordVerifier {
    private static final String MANAGER_PASSWORD = "super_secret";

    public static boolean verifyManagerPassword(String password){
        return MANAGER_PASSWORD.equals(password);
    }
}
