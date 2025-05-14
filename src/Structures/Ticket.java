package Structures;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Ticket {
    private String row;
    private String seat;
    private LocalDate date;
    private String name;

    public Ticket(String row, String seat, LocalDate date, String name) {
        this.row = row;
        this.seat = seat;
        this.date = date;
        this.name = name;
    }

    @Override
    public String toString() {
        return row + "," + seat + "," + date.toString() + "," + name;
    }


    public String getRow() {
        return row;
    }

    public String getSeat() {
        return seat;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(row, ticket.row) && Objects.equals(seat, ticket.seat) && Objects.equals(date, ticket.date) && Objects.equals(name, ticket.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, seat, date, name);
    }
}
