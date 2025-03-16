import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class Hall {
    private int id;
    private int numberOfRows;
    private int numberOfSeatsPerRow;

    public void addEvent(Date date, int hallId, String name) throws IOException {
        FileReader fr = new FileReader("src/TestFiles/infoFile.txt");
        BufferedReader br = new BufferedReader(fr);
        while(br.ready()){
            String curLine = br.readLine();
            if(curLine.startsWith("Play") && curLine.contains(Integer.toString(hallId)) && curLine.contains(date.toString())){
                System.out.println();
            }
        }
    }

    public Hall(int id, int numberOfRows, int numberOfSeatsPerRow) {
        this.id = id;
        this.numberOfRows = numberOfRows;
        this.numberOfSeatsPerRow = numberOfSeatsPerRow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfSeatsPerRow() {
        return numberOfSeatsPerRow;
    }

    public void setNumberOfSeatsPerRow(int numberOfSeatsPerRow) {
        this.numberOfSeatsPerRow = numberOfSeatsPerRow;
    }
}
