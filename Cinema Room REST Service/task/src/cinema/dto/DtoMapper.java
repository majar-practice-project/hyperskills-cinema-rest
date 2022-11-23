package cinema.dto;

import cinema.domain.Seat;

public class DtoMapper {
    public static Seat SeatRequestToSeat(SeatRequest seatDto) {
        int r = seatDto.getRow();
        int c = seatDto.getColumn();
        return new Seat(r, c, 0);
    }
}
