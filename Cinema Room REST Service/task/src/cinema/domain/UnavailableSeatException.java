package cinema.domain;

public class UnavailableSeatException extends Exception{
    public UnavailableSeatException() {
        super("The ticket has been already purchased!");
    }

    public UnavailableSeatException(String message) {
        super(message);
    }
}
