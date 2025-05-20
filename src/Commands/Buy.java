package Commands;

import Exceptions.EmptyPurchaseParametersException;
import Exceptions.TicketAlreadyBoughtException;
import Interfaces.Command;
import Structures.Booking;
import Structures.Purchase;
import Structures.SessionInformation;
import Structures.Ticket;

import java.time.LocalDate;

public class Buy implements Command<Void,String> {
    private SessionInformation sessionInformation;

    public Buy(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @Override
    public Void run(String[] args) throws Exception {
        try{
            if(args.length != 4 || args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()){
                throw new EmptyPurchaseParametersException("Usage buy <row> <seat> <date> <name>");
            }
            String row = args[0];
            String seat = args[1];
            String date = args[2];
            String name = args[3];
            System.out.println(row);
            System.out.println(seat);
            System.out.println(date);
            System.out.println(name);
            Purchase newPurchase = new Purchase(row, seat, LocalDate.parse(date), name);
            if(!sessionInformation.getPurchases().contains(newPurchase)){
                sessionInformation.getBookings().remove(new Booking(newPurchase.getTicket()));
                sessionInformation.addPurchase(newPurchase);
                System.out.println("Ticket bought");
            }
            else{
                throw new TicketAlreadyBoughtException("The ticket is already bought.");
            }
        }catch ( EmptyPurchaseParametersException |TicketAlreadyBoughtException e){
            System.out.println(e.getMessage());
        }


        return null;
    }
}
