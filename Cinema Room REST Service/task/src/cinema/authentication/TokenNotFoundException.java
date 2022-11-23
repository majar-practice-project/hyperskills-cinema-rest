package cinema.authentication;

public class TokenNotFoundException extends Exception{
    public TokenNotFoundException() {
        super("Wrong token!");
    }

    public TokenNotFoundException(String message) {
        super(message);
    }
}
