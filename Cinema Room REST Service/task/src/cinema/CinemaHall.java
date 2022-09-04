package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    CinemaHall(int totalRows, int totalColumns, List<Seat> availableSeats, List<Ticket> availableTickets) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = new ArrayList<>();
        this.availableTickets = new ArrayList<>();
        for (Seat seat :
                availableSeats) {
            if (!seat.isTaken()) {
                this.availableSeats.add(seat);
            }
        }
        this.availableTickets = availableTickets;
    }


    @JsonIgnore
    public List<Ticket> getAvailableTickets() {
        return availableTickets;
    }


    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public Ticket getSingleTicket(Seat seat) {
        return this.availableTickets.get(this.availableSeats.indexOf(seat));
    }

    public void setTaken(Seat seat, boolean taken) {
        this.availableSeats.get(this.availableSeats.indexOf(seat)).setTaken(taken);
    }

    public void setTaken(Ticket ticket, boolean taken) {
        this.availableSeats.get(this.availableTickets.indexOf(ticket)).setTaken(taken);
    }

    public Ticket getSingleTicket(UUID token) {
        Ticket result = null;
        for (Ticket ticket : this.availableTickets) {
            if (ticket.getToken().equals(token)) {
                result = this.availableTickets.get(this.availableTickets.indexOf(ticket));
            }
        }
        return result;

    }

}
