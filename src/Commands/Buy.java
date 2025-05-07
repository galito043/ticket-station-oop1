package Commands;

import Exceptions.EmptyPurchaseParametersException;
import Exceptions.TicketAlreadyBoughtException;
import Interfaces.Command;
import Structures.Purchase;
import Structures.Ticket;

import java.time.LocalDate;

public class Buy implements Command<Void,String> {
    @Override
    public Void run(String[] args) throws Exception {

        if(args.length < 4 || args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()){
            throw new EmptyPurchaseParametersException("Usage buy <row> <seat> <date> <name>");
        }
        String row = args[0];
        String seat = args[1];
        String date = args[2];
        String name = args[3];
        Purchase newPurchase = new Purchase(row, seat, LocalDate.parse(date), name);
        if(!Open.purchases.contains(newPurchase)){
            Open.purchases.add(newPurchase);
            System.out.println("Ticket bought");
        }
        else{
            throw new TicketAlreadyBoughtException("The ticket is already bought.");
        }

        return null;
    }
}
