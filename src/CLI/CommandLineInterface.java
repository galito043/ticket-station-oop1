package CLI;

import Commands.*;
import Enums.CommandType;
import Exceptions.*;
import Interfaces.Command;
import Structures.SessionInformation;

import java.awt.*;
import java.util.*;
import java.util.List;
public class CommandLineInterface {
    public static boolean shouldContinue = true;
    private SessionInformation sessionInformation = SessionInformation.getInstance();
    Scanner inputScanner = new Scanner(System.in);
    Map<CommandType, Command> aliasCommand = new HashMap<>();

    public void menu() throws Exception {
        aliasCommand.put(CommandType.OPEN, new Open(sessionInformation));
        aliasCommand.put(CommandType.EXIT, new Exit(sessionInformation));
        aliasCommand.put(CommandType.CLOSE, new Close(sessionInformation));
        aliasCommand.put(CommandType.HELP, new Help());
        aliasCommand.put(CommandType.SAVE, new Save(sessionInformation));
        aliasCommand.put(CommandType.SAVE_AS, new SaveAs(sessionInformation));
        aliasCommand.put(CommandType.ADD_EVENT, new AddEvent(sessionInformation));
        aliasCommand.put(CommandType.FREE_SEATS, new FreeSeats(sessionInformation));
        aliasCommand.put(CommandType.BOOK, new Book(sessionInformation));
        aliasCommand.put(CommandType.UNBOOK, new Unbook(sessionInformation));
        aliasCommand.put(CommandType.BUY , new Buy(sessionInformation));
        aliasCommand.put(CommandType.BOOKINGS, new Bookings(sessionInformation));
        aliasCommand.put(CommandType.CHECK, new Check(sessionInformation));
        aliasCommand.put(CommandType.REPORT, new Report(sessionInformation));
        aliasCommand.put(CommandType.LIST_ALL_EVENTS, new ListAllEvents(sessionInformation));
        aliasCommand.put(CommandType.MOST_WATCHED, new MostWatchedStatistics(sessionInformation));
        aliasCommand.put(CommandType.LEAST_WATCHED, new LeastWatchedStatistics(sessionInformation));
        aliasCommand.put(CommandType.PURCHASES, new Purchases(sessionInformation));
        while(shouldContinue){
            try{
                System.out.print("> ");
                String command =  inputScanner.nextLine();
                String[] commandWithOptions = command.trim().split("\\s+",2);
                CommandType currentCommand = CommandType.fromString(commandWithOptions[0]);
                if(aliasCommand.containsKey(currentCommand) ){
                    if(Open.curFile == null){
                        if(!currentCommand.equals(CommandType.EXIT) && !currentCommand.equals(CommandType.HELP) && !currentCommand.equals(CommandType.OPEN)){
                            throw new NoFileOpenException("No file is opened. Open a file first");
                        }
                    }
                    if(currentCommand.equals(CommandType.EXIT)){
                        shouldContinue = false;
                    }
                    String[] argumentsArray = new String[0];
                    Command commandToExecute = aliasCommand.get(currentCommand);
                    if(commandWithOptions.length > 1){
                        String arguments = commandWithOptions[1];
                        argumentsArray = arguments.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                        for (int i = 0; i < argumentsArray.length; i++) {
                            argumentsArray[i] = argumentsArray[i].replaceAll("^\"|\"$", "");
                        }
                    }
                    commandToExecute.run(argumentsArray);
                    }
            }
            catch (NoFileOpenException e){
                System.out.println(e.getMessage());
            }
        }
    }

}
