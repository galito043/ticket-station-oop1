package Commands;

import Interfaces.Command;
import Structures.SessionInformation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * Implements the "close" command: closes the current session and file.
 */
public class Close  implements Command <Void, String> {
private SessionInformation sessionInformation;

    public Close(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    public static boolean isClosed;

    public Void run(String[] args) throws IOException {
        sessionInformation.reset();
        Open.generalInfoReader.close();
        Open.hallInfoReader.close();
        Open.curFile = null;
        System.out.println("Closing");
        return null;
    }

}
