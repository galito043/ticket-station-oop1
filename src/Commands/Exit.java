package Commands;

import Interfaces.Command;
import Structures.SessionInformation;

import java.io.FileReader;
import java.io.IOException;


/**
 * Implements the "exit" command: exits  the application.
 */
public class Exit implements Command {
    private SessionInformation sessionInformation;

    public Exit(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    public void run() {
        ;
    }

    @Override
    public Void run(Object[] args) throws Exception {
        System.out.println("Exiting......");
        System.exit(0);

        return null;
    }
}
