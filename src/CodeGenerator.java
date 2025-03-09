import java.util.Random;


public class CodeGenerator {

    public static String generateCode(Ticket ticket) {
        StringBuilder curKey = new StringBuilder();
        curKey.append(String.valueOf(ticket.getRow())  + String.valueOf(ticket.getSeat()) );
        return curKey.toString();
    }
}