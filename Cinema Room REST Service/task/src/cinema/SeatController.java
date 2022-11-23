package cinema;

import cinema.authentication.PasswordVerifier;
import cinema.authentication.TokenNotFoundException;
import cinema.domain.CinemaRoom;
import cinema.domain.Seat;
import cinema.domain.SeatNumberOutOfBoundException;
import cinema.domain.UnavailableSeatException;
import cinema.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeatController {
    private final CinemaRoom room = new CinemaRoom(9, 9);

    @GetMapping("/seats")
    public CinemaRoom getSeats() {
        return room;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseSeat(@RequestBody SeatRequest seatRequest) {
        try {
            Seat requestSeat = DtoMapper.SeatRequestToSeat(seatRequest);
            return new ResponseEntity<>(room.purchaseSeat(requestSeat), HttpStatus.OK);
        } catch (UnavailableSeatException | SeatNumberOutOfBoundException e) {
            return new ResponseEntity<>(new ErrorRequestBody(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnTicket(@RequestBody TokenBody token) {
        try {
            return new ResponseEntity<>(room.returnSeat(token.getToken()), HttpStatus.OK);
        } catch (TokenNotFoundException e) {
            return new ResponseEntity<>(new ErrorRequestBody(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/stats")
    public ResponseEntity<Object> getStatistics(@RequestParam(value = "password", required = false) String password){
        if(PasswordVerifier.verifyManagerPassword(password)){
            return new ResponseEntity<>(room.presentStats(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(new ErrorRequestBody("The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
    }
}
