package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class CinemaController {

    CinemaHall hall = new CinemaHall(9, 9);

    @GetMapping("/seats")
    public CinemaHall getHall() {
        return hall;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat data) {
        if (!hall.getSingleTicket(data).getTicketSeat().isTaken()) {
            hall.getSingleTicket(data).getTicketSeat().setTaken(true);
            data.setTaken(true);
            return new ResponseEntity<>(hall.getSingleTicket(data), HttpStatus.OK);
        } else if (data.getRow() > 9 || data.getRow() < 1 || data.getColumn() > 9 || data.getColumn() < 1) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestParam UUID token) {
        for (Ticket ticket : hall.getAvailableTickets()) {
            if (ticket.token.equals(token)) {
                hall.makeTicketTaken(ticket, false);
                ReturnedTicket ticketResponse = new ReturnedTicket(ticket);
                return new ResponseEntity<>(ticketResponse, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }


}
