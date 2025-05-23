package Structures;

import java.time.LocalDate;
import java.util.Objects;
/**
 * Represents a ticket purchase with a unique code
 */
public class Purchase {
    private Ticket ticket;
    private String ticketCode;
    /**
     * Creates a Purchase and generates a code
     *
     * @param row       seat row
     * @param seat      seat number
     * @param date      event date
     * @param name      event name
     */
    public Purchase(int row, int seat, LocalDate date, String name) {
        this.ticket = new Ticket(row,seat,date, name);
        this.ticketCode = CodeGenerator.generateCode(row,seat,date.toString(),name);
    }
    public Purchase(int row, int seat, LocalDate date, String name, String ticketCode) {
        this.ticket = new Ticket(row,seat,date, name);
        this.ticketCode = ticketCode;
    }
    public Purchase(Ticket ticket) {
        this.ticket =  ticket;

    }
    @Override
    public String toString() {
        return "Purchase," + ticketCode + "," + ticket.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(ticket, purchase.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket);
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getTicketCode() {
        return ticketCode;
    }
}
