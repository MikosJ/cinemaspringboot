package cinema;

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

    CinemaHall(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = new ArrayList<>();
        for (int i = 1; i < totalRows+1; i++) {
            for (int j = 1; j < totalColumns+1; j++) {
                availableSeats.add(new Seat(i, j));
            }
        }
    }
}
