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

    public Open(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    public Void run(String[] args) throws IOException {
        sessionInformation.reset();
            if(args.length >= 1){
                curFile = args[0];
            }
            else{
                curFile = "src/TestFiles/InfoFile.txt";
            }
        generalInfoReader = new FileReader(curFile);
        hallInfoReader = new FileReader(hallsFile);
        FileLoader fileLoader = new FileLoader(curFile, hallsFile, sessionInformation, generalInfoReader, hallInfoReader);
            fileLoader.loadGeneralInformation();
            fileLoader.loadHallInformation();

        System.out.println("Opening " + curFile);
            return null;
        }


}
