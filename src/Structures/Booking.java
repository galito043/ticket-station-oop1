package Structures;

import java.time.LocalDate;
import java.util.Objects;
/**
 * Represents a booking for a ticket, with a  note.
 */
public class Booking {

    private String note;
    private Ticket ticket;

    /**
     * Constructs a Booking with full details.
     *
     * @param row       row
     * @param seat      seat
     * @param localDate date of event
     * @param name      the event name
     * @param note      note
     */
    public Booking(int row, int seat, LocalDate localDate, String name, String note) {
        this.ticket = new Ticket(row, seat, localDate, name);
        this.note = note;
    }
    public Booking(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(ticket, booking.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket);
    }

    @Override
    public String toString() {
        return "Booking," + ticket.toString() + "," + note;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getNote() {
        return note;
    }
}
