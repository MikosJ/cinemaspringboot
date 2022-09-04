package cinema;

import lombok.Data;


@Data
public class ReturnedTicket {
    Seat returnedTicket;

    ReturnedTicket(Ticket ticket) {
        this.returnedTicket = ticket.getTicketSeat();
    }


    ReturnedTicket() {
    }


}
