package cinema.dto;

import cinema.domain.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchasedSeatResponse {
    @JsonProperty
    private String token;
    @JsonProperty
    private Seat ticket;

    public PurchasedSeatResponse() {
    }

    public PurchasedSeatResponse(String token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
