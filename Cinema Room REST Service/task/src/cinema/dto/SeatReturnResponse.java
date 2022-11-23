package cinema.dto;

import cinema.domain.Seat;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SeatReturnResponse {
    @JsonProperty("returned_ticket")
    private Seat returnTicket;

    public SeatReturnResponse() {
    }

    @JsonCreator
    public SeatReturnResponse(@JsonProperty("returned_ticket") Seat returnTicket) {
        this.returnTicket = returnTicket;
    }

    public Seat getReturnTicket() {
        return returnTicket;
    }
}
