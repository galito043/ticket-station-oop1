package Commands;

import Interfaces.Command;
import Structures.Ticket;

import java.time.LocalDate;

public class Buy implements Command<Void,String> {
    @Override
    public Void run(String[] args) throws Exception {
        String row = args[0];
        String seat = args[1];
        String date = args[2];
        String name = args[3];
        Ticket newTicket = new Ticket.Builder(row, seat, LocalDate.parse(date), name).build();

        Open.fileContents.add(newTicket.toString());
        return null;
    }
}
