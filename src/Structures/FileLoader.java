package Structures;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads event, hall, booking and purchase date into sessionInformation
 */
public class FileLoader {
    private String generalInfoFile;
    private String hallInfoFile;

    SessionInformation sessionInformation;
    BufferedReader bufferedGeneralInfoReader;
    BufferedReader bufferedHallInfoReader;

    public FileLoader(String generalInfoFile, String hallInfoFile, SessionInformation sessionInformation, FileReader generalInfoReader, FileReader hallInfoReader) throws FileNotFoundException {
        this.hallInfoFile = hallInfoFile;
        this.generalInfoFile = generalInfoFile;
        bufferedGeneralInfoReader = new BufferedReader(generalInfoReader);
        bufferedHallInfoReader = new BufferedReader(hallInfoReader);
        this.sessionInformation = sessionInformation;
    }

    public void loadGeneralInformation() throws IOException {

        while (bufferedGeneralInfoReader.ready()){

            String line = bufferedGeneralInfoReader.readLine();
            String[] parameters = line.split(",");
            sessionInformation.putObject(line, parameters);
        }
    }
    public void loadHallInformation() throws IOException {
        while(bufferedHallInfoReader.ready()){
            String line = bufferedHallInfoReader.readLine();
            String[] parameters = line.split(",");
            sessionInformation.putHall(parameters);
        }

    }



}
