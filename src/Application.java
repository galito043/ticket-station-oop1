import CLI.CommandLineInterface;
import Commands.Exit;
import Commands.Open;
import Exceptions.EmptyEventParametersException;
import Exceptions.EventAlreadyExistsException;
import Exceptions.InvalidTicketCodeException;
import Exceptions.NoFileOpenException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Application {
    public static void main(String[] args) throws Exception {
        boolean shouldContinue = true;
        while (shouldContinue){
            try{
                CommandLineInterface cli = new CommandLineInterface();
                cli.menu();
            }catch (EmptyEventParametersException | EventAlreadyExistsException | InvalidTicketCodeException
                    | NoFileOpenException e){
                System.out.println(e.getMessage());
            }
            shouldContinue = CommandLineInterface.shouldContinue;
        }


        ArrayList<String> strings = new ArrayList<>();
//        strings.add("Escape");
//        System.out.println(Arrays.stream(strings.toArray()).anyMatch(string -> );
    }
}
