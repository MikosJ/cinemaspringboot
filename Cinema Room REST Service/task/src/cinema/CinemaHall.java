package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CinemaHall {
    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;
    private List<Ticket> availableTickets;

    CinemaHall(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = new ArrayList<>();
        this.availableTickets = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                availableSeats.add(new Seat(i, j));
                availableTickets.add(new Ticket(new Seat(i, j)));
            }
        }
    }


    @JsonIgnore
    public List<Ticket> getAvailableTickets() {
        return availableTickets;
    }

    public Ticket getSingleTicket(Seat seat) {
        return this.availableTickets.get(this.availableSeats.indexOf(seat));
    }

    public void makeTicketTaken(Ticket ticket, boolean taken) {
        for (Ticket tickets : this.availableTickets) {
            if (ticket.equals(tickets)) {
                tickets.getTicketSeat().setTaken(taken);
            }
        }

    }
}
