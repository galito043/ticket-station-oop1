package Structures;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {

    private String note;
    private Ticket ticket;

    public Booking(String row, String seat, LocalDate localDate, String name, String note) {
        this.ticket = new Ticket(row, seat, localDate, name);
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(note, booking.note) && Objects.equals(ticket, booking.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(note, ticket);
    }

    @Override
    public String toString() {
        return "Booking "  + ticket.toString() + " " + note;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getNote() {
        return note;
    }
}
