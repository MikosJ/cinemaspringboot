package managment;

import cinema.CinemaHall;
import cinema.Ticket;
import lombok.Data;

@Data
public class Statistics {
    Integer currentIncome = 0;
    Integer numberOfAvailableSeats = 81;
    Integer numberOfPurchasedTickets = 0;

    public Statistics(CinemaHall hall) {
        for (Ticket ticket : hall.getAvailableTickets()) {
            if (ticket.getTicketSeat().isTaken()) {
                this.currentIncome += ticket.getTicketSeat().getPrice();
                this.numberOfAvailableSeats--;
                this.numberOfPurchasedTickets++;
            }
        }

    }

    public Statistics() {
    }
}