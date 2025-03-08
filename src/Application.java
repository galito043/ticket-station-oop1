public class Application {
    public static void main(String[] args) {
        Ticket ticket = new Ticket.Builder(2,13, "3/05", "Gali Aljabari").code().build();
        System.out.println(ticket.toString());
    }
}
