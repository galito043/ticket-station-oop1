import CLI.CommandLineInterface;
import Commands.Exit;
import Commands.Open;

import java.io.IOException;
import java.util.Date;

public class Application {
    public static void main(String[] args) throws Exception {
        Date date = new Date(2025, 11, 24);

        CommandLineInterface cli = new CommandLineInterface();
        cli.menu();



    }
}
