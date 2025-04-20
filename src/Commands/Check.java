package Commands;

import Exceptions.InvalidTicketCodeException;
import Interfaces.Command;

public class Check implements Command<Void, String> {


    @Override
    public Void run(String[] args) throws Exception {
        String code = args[0];
        String seatNumber = "";
        String rowNumber = "";
        for(String line : Open.fileContents){
            if(!checkValidity(line, code)){
               continue;
            }
            else{
                rowNumber = code.substring(0,1);
                seatNumber = line.substring(1,2);
                break;
            }
        }
        if(rowNumber.isEmpty() && seatNumber.isEmpty()){
            throw new InvalidTicketCodeException("Invalid ticket code");
        }

    }
    public boolean checkValidity(String line, String code){
       if(line.contains("Ticket") && line.contains(code)){
           return true;
       }
       return false;
    }
}
