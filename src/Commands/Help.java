package Commands;

import Interfaces.Command;

public class Help implements Command <Void, String>{
    public Void run(String[] args){
        System.out.println(
                "  open <файл>          – зарежда или създава нов файл с данни\n" +
                "  close                – затваря текущия файл\n" +
                "  save                 – запазва промените в текущия файл\n" +
                "  saveas <ново_име>    – запазва текущите данни в нов файл\n" +
                "  help                 – показва тази справка\n" +
                "  exit                 – излиза от програмата\n" +
                "  addevent <date> <hall> <name>\n" +
                "  freeseats <date> <name>\n" +
                "  book <row> <seat> <date> <name> [<note>]\n" +
                "  unbook <row> <seat> <date> <name>\n" +
                "  buy <row> <seat> <date> <name>\n" +
                "  bookings [<date>] [<name>]\n" +
                "  check <code>\n" +
                "  report <from> <to> [<hall>]\n" +
                "  statisticsTop\n" +
                "  statisticsLow_occupancy <from> <to>");
        return null;
    }
}
