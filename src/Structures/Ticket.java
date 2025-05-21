package Structures;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
/**
 * Ticket base class from which is used in purchases and bookings, contains row seat, date of event and the name of the event
 *
 */
public class Ticket {
    private int row;
    private int seat;
    private LocalDate date;
    private String nameOfPlay;

    public Ticket(int row, int seat, LocalDate date, String name) {
        this.row = row;
        this.seat = seat;
        this.date = date;
        this.nameOfPlay = name;
    }

    @Override
    public String toString() {
        return row + "," + seat + "," + date.toString() + "," + nameOfPlay;
    }


    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return nameOfPlay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(row, ticket.row) && Objects.equals(seat, ticket.seat) && Objects.equals(date, ticket.date) && Objects.equals(nameOfPlay, ticket.nameOfPlay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, seat, date, nameOfPlay);
    }
}
