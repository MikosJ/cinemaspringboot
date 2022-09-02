package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class Ticket {
    UUID token;
    Seat ticket;

    Ticket(Seat seat) {
        this.token = UUID.randomUUID();
        this.ticket = seat;
    }

    Ticket() {
    }

    @JsonIgnore
    public Seat getTicketSeat() {
        return ticket;
    }

    //Return seat data and token when given a seat
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticketPlace = (Ticket) o;
        return Objects.equals(token, ticketPlace.token) && Objects.equals(ticket, ticketPlace.ticket);
    }

}
