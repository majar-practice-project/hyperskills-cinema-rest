package cinema.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {
    private int row;
    private int column;

    private int price;

    public Seat() {
    }

    @JsonCreator
    public Seat(@JsonProperty int row,
                @JsonProperty int column,
                @JsonProperty int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        return 10000 * row + column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Seat)) return false;

        Seat anotherSeat = (Seat) obj;
        return (row == anotherSeat.row) && (column == anotherSeat.column);
    }
}
