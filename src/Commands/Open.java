package Commands;

import Interfaces.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Open implements Command <Void, String>{
public static FileReader fr;
public static String curFile;
public static List<String> fileContents = new ArrayList<>();

public static List<String> halls = new ArrayList<>();
private String hallsFile = "src/TestFiles/halls.txt";
    public Void run(String[] args) throws IOException {

            fileContents.clear();
            halls.clear();
            if(args.length >= 1){
                curFile = args[0];
            }
            else{
//                curFile = "C:\\Users\\galit\\IdeaProjects\\TicketStation\\src\\TestFiles\\infoFile.txt";
                curFile = "C:\\Users\\galit\\IdeaProjects\\TicketStation\\src\\TestFiles\\alternateInfoFile.txt";
            }
        System.out.println("Opening " + curFile);
            fr = new FileReader(curFile);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                fileContents.add(br.readLine());
            }
            FileReader hallReader = new FileReader(hallsFile);
            BufferedReader bufferedHallReader = new BufferedReader(hallReader);

            while(bufferedHallReader.ready()){
                halls.add(bufferedHallReader.readLine());
            }

            return null;
        }
}
