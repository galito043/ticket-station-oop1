package Commands;

import Exceptions.InvalidTicketCodeException;
import Exceptions.MissingCheckParameterException;
import Interfaces.Command;
import Structures.Purchase;
import Structures.SessionInformation;

public class Check implements Command<Void, String> {

private SessionInformation sessionInformation;

    public Check(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @Override
    public Void run(String[] args) throws Exception {
        if(!(args.length > 0)){
            throw new MissingCheckParameterException("Usage: check <code>");
        }
        String code = args[0];
        String seatNumber = "";
        String rowNumber = "";
//        boolean isValid = Open.purchases.stream().anyMatch(purchase -> checkValidity(purchase, code));
        boolean isValid = false;
        for(Purchase p : sessionInformation.getPurchases()){
            if(checkValidity(p, code)){
                isValid = true;
            }
        }
        if(isValid){
            seatNumber = code.substring(0,1);
            rowNumber = code.substring(2,3);
            System.out.println("Ticket with code " + code +" is valid. \n Seat number " + seatNumber + " row number " + rowNumber );
        }
        else{
            throw new InvalidTicketCodeException("Invalid ticket code");
        }
        return null;
    }
    public boolean checkValidity(Purchase purchase, String codeToCheck){
        String purchaseCode = purchase.getTicketCode();
       if(purchaseCode.equals(codeToCheck)){
           return true;
       }
       return false;
    }
}
