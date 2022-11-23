package cinema.domain;

public class SeatNumberOutOfBoundException extends Exception{
    public SeatNumberOutOfBoundException() {
        super("The number of a row or a column is out of bounds!");
    }

    public SeatNumberOutOfBoundException(String message) {
        super(message);
    }
}