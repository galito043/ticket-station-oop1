package Commands;

import Interfaces.Command;
import Structures.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Open implements Command <Void, String>{
public static FileReader generalInfoReader = null;
public static FileReader hallInfoReader = null;
public static String hallsFile = "src/TestFiles/halls.txt";
private SessionInformation sessionInformation;
public  static String curFile;
    /**
     * Implements the "open" command: loads session data from files.
     */
    public Open(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    public Void run(String[] args) throws IOException {
        sessionInformation.reset();
        curFile = args.length >= 1 ? args[0] : "src/TestFiles/InfoFile.txt";

        try{
            generalInfoReader = new FileReader(curFile);
        }catch (IOException e){
            System.out.println("Could not open info file " + curFile);
            return null;
        }
        try{
            hallInfoReader = new FileReader(hallsFile);

        }catch (IOException e){
            System.out.println("Could not open halls file " + hallsFile);
            return null;
        }

        try{
            FileLoader fileLoader = new FileLoader(curFile, hallsFile, sessionInformation, generalInfoReader, hallInfoReader);
            fileLoader.loadGeneralInformation();
            fileLoader.loadHallInformation();

            System.out.println("Opening " + curFile);
        }catch (IOException e){
            System.out.println("Error loading file contents " + e.getMessage());
        }

            return null;
        }


}
