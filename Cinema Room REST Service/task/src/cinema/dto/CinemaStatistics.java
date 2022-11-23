package cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CinemaStatistics {
    @JsonProperty("current_income")
    private int currentIncome;
    @JsonProperty("number_of_available_seats")
    private int numberOfAvailableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private int numberOfPurchasedTickets;

    public CinemaStatistics() {
    }

    public CinemaStatistics(@JsonProperty("current_income") int currentIncome,
                            @JsonProperty("number_of_available_seats") int numberOfAvailableSeats,
                            @JsonProperty("number_of_purchased_tickets") int numberOfPurchasedTickets) {
        this.currentIncome = currentIncome;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }
}
