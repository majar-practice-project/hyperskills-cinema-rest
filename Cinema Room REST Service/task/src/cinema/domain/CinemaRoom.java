package cinema.domain;

import cinema.authentication.TokenGenerator;
import cinema.authentication.TokenNotFoundException;
import cinema.dto.CinemaStatistics;
import cinema.dto.PurchasedSeatResponse;
import cinema.dto.SeatReturnResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class CinemaRoom {
    @JsonIgnore
    private final Map<Seat, Seat> availableSeats = new ConcurrentHashMap<>();
    @JsonIgnore
    private final Map<String, Seat> purchasedSeats = new ConcurrentHashMap<>();
    @JsonProperty("total_rows")
    private int totalRows;
    @JsonProperty("total_columns")
    private int totalColumns;
    @JsonIgnore
    private int income = 0;

    public CinemaRoom() {
    }

    public CinemaRoom(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;

        for (int r = 1; r <= totalRows; r++) {
            for (int c = 1; c <= totalColumns; c++) {
                addSeat(r, c, (row, column) -> row <= 4 ? 10 : 8);
            }
        }
    }

    @JsonCreator
    public CinemaRoom(
            @JsonProperty("total_rows") int totalRows,
            @JsonProperty("total_columns") int totalColumns,
            @JsonProperty("available_seats") Set<Seat> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        availableSeats.forEach(seat -> this.availableSeats.put(seat, seat));
    }

    private void addSeat(int r, int c, BiFunction<Integer, Integer, Integer> priceConfiguration) {
        Seat newSeat = new Seat(r, c, priceConfiguration.apply(r, c));
        availableSeats.put(newSeat, newSeat);
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    @JsonGetter("available_seats")
    public Set<Seat> getAvailableSeatsAsSet() {
        return availableSeats.keySet();
    }

    public Map<Seat, Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void validateSeat(Seat seat) throws SeatNumberOutOfBoundException {
        int row = seat.getRow();
        int column = seat.getColumn();

        if (row < 1 || row > totalRows || column < 1 || column > totalColumns) {
            throw new SeatNumberOutOfBoundException();
        }
    }

    public PurchasedSeatResponse purchaseSeat(Seat seat) throws UnavailableSeatException, SeatNumberOutOfBoundException {
        validateSeat(seat);
        Seat purchasedSeat = availableSeats.remove(seat);
        if (purchasedSeat == null) {
            throw new UnavailableSeatException();
        } else {
            income += purchasedSeat.getPrice();
            String token = TokenGenerator.generateToken();
            purchasedSeats.put(token, purchasedSeat);
            return new PurchasedSeatResponse(token, purchasedSeat);
        }
    }

    public SeatReturnResponse returnSeat(String token) throws TokenNotFoundException {
        Seat returnedSeat = purchasedSeats.remove(token);
        if (returnedSeat == null) {
            throw new TokenNotFoundException();
        }
        income -= returnedSeat.getPrice();
        availableSeats.put(returnedSeat, returnedSeat);
        return new SeatReturnResponse(returnedSeat);
    }

    public CinemaStatistics presentStats() {
        return new CinemaStatistics(income, availableSeats.size(), purchasedSeats.size());
    }

}
