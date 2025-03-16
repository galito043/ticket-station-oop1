package Commands;

import Interfaces.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Open implements Command <List<String>, String>{
public static FileReader fr;
public static String curFile;
public static List<String> fileContents = new ArrayList<>();
public static List<String> newFileContents = new ArrayList<>();
    public List<String> run(String[] args) throws IOException {
        curFile = args[0];
        fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);
        List<String> lines = new ArrayList<>();
        while (br.ready()) {
            fileContents.add(br.readLine());
        }
        System.out.println(lines);
        return lines;
    }


}
