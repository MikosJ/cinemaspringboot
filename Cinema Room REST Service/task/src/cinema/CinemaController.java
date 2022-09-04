package cinema;

import managment.CinemaManagment;
import managment.Statistics;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {

    CinemaHall hall = new CinemaHall(9, 9);

    @GetMapping("/seats")
    public CinemaHall getHall() {
        return new CinemaHall(9, 9, hall.getAvailableSeats(), hall.getAvailableTickets());
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat data) {
        if (hall.getAvailableSeats().contains(data) && !hall.getSingleTicket(data).getTicketSeat().isTaken()) {
            hall.getSingleTicket(data).getTicketSeat().setTaken(true);
            data.setTaken(true);
            data.setPrice();
            hall.setTaken(data, true);
            return new ResponseEntity<>(hall.getSingleTicket(data), HttpStatus.OK);
        } else if (data.getRow() > 9 || data.getRow() < 1 || data.getColumn() > 9 || data.getColumn() < 1) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Ticket ticket) {
        for (Ticket tickets : hall.getAvailableTickets()) {
            if (tickets.getToken().equals(ticket.getToken()) && tickets.getTicketSeat().isTaken()) {
                hall.getSingleTicket(ticket.getToken()).getTicketSeat().setTaken(false);
                try {
                    if (ticket.getToken().equals(tickets.getToken()) && ticket.getTicketSeat().equals(tickets.getTicketSeat())) {
                        hall.setTaken(ticket, false);
                    }
                } catch (Exception e) {
                }
                ReturnedTicket ticketResponse = new ReturnedTicket(hall.getSingleTicket(ticket.getToken()));
                return new ResponseEntity<>(ticketResponse, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> statistics(@RequestParam(value = "password", required = false) String password) {
        CinemaManagment mgmt = new CinemaManagment("super_secret");

        try {
            if (password.equals(mgmt.getPassword())) {
                Statistics stats = new Statistics(new CinemaHall(9, 9, hall.getAvailableSeats(), hall.getAvailableTickets()));
                return new ResponseEntity<>(stats, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }


    }
}