package Commands;

import Interfaces.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Open implements Command <List<String>, String>{
public static FileReader fr;
public static String curFile;
public static List<String> fileContents = new ArrayList<>();
public static List<String> newFileContents = new ArrayList<>();
    public List<String> run(String[] args) throws IOException {

        if(args.length >= 1){
            curFile = args[0];
        }
        else{
            curFile = "C:\\Users\\galit\\IdeaProjects\\TicketStation\\src\\TestFiles\\infoFile.txt";
        }
        fr = new FileReader(curFile);
        BufferedReader br = new BufferedReader(fr);
        List<String> lines = new ArrayList<>();
        while (br.ready()) {
            fileContents.add(br.readLine());
        }
        System.out.println(lines);
        return lines;
    }


}
