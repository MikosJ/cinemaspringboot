package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Objects;

@Data
public class Seat {
    private Integer row;
    private Integer column;
    private Integer price;
    private boolean isTaken;

    Seat(Integer row, Integer column) {
        this.column = column;
        this.row = row;
        this.price = row >= 4 ? 8 : 10;
    }

    Seat() {
    }

    @JsonIgnore
    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public void setPrice() {
        this.price = row >= 4 ? 8 : 10;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(row, seat.row) && Objects.equals(column, seat.column);
    }
}

