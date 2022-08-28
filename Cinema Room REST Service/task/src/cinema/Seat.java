package cinema;

import lombok.Data;

@Data
public class Seat {
    private Integer row;
    private Integer column;

    Seat(Integer row, Integer column) {
        this.column = column;
        this.row = row;
    }
}
