package CLI;

import Commands.*;
import Exceptions.*;
import Interfaces.Command;

import java.awt.*;
import java.util.*;
import java.util.List;
public class CommandLineInterface {
    public static boolean shouldContinue = true;
    Scanner inputScanner = new Scanner(System.in);
    Map<String, Command> aliasCommand = new HashMap<>();

    public void menu() throws Exception {
        aliasCommand.put("open", new Open());
        aliasCommand.put("exit", new Exit());
        aliasCommand.put("close", new Close());
        aliasCommand.put("help", new Help());
        aliasCommand.put("save", new Save());
        aliasCommand.put("saveas", new SaveAs());
        aliasCommand.put("addevent", new AddEvent());
        aliasCommand.put("freeseats", new FreeSeats());
        aliasCommand.put("book", new Book());
        aliasCommand.put("unbook", new Unbook());
        aliasCommand.put("buy" , new Buy());
        aliasCommand.put("bookings", new Bookings());
        aliasCommand.put("check", new Check());
        aliasCommand.put("report", new Report());
        aliasCommand.put("addhall", new AddHall());

        while(shouldContinue){
            try{
                System.out.print("> ");
                String command =  inputScanner.nextLine();
                List<String> commandWithOptions = new ArrayList<String>();
                String[] commands = command.split(" ");
                commandWithOptions.addAll(Arrays.asList(commands));



                if(aliasCommand.containsKey(commandWithOptions.getFirst()) ){
                    if(Open.curFile == null){
                        if(!Objects.equals(commandWithOptions.getFirst(), "open") && !Objects.equals(commandWithOptions.getFirst(), "help") && !commandWithOptions.getFirst().equals("exit")){
                            throw new NoFileOpenException("No file is opened. Open a file first");
                        }
                    }
                    Command commandToExecute = aliasCommand.get(commandWithOptions.getFirst());
                    commandWithOptions.removeFirst();
                    String[] arguments = commandWithOptions.toArray(new String[0]);
                    if(commandToExecute.getClass().getSimpleName().equals("exit")){
                        shouldContinue = false;
                    }
                    commandToExecute.run(arguments);
                }
                else{
                    System.out.println("No such command \n");
                }
            }
            catch (EmptyBookingParametersException | EmptyEventParametersException | EmptyFreeSeatsCommandException |
                    EmptyPurchaseParametersException | EmptyReportParametersException | EmptyUnbookParametersException
                    | EventAlreadyExistsException | EventNotFoundException | HallNotFoundException | InvalidDateException
                    | InvalidTicketCodeException | NoEventsException | NoFileOpenException | TicketAlreadyBoughtException
                   | ArrayIndexOutOfBoundsException  | MissingCheckParameterException e){
                System.out.println(e.getMessage());
            }



        }






    }

}
