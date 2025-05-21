import CLI.CommandLineInterface;
/**
 * Application main class which creates an instance of the command line interface and uses it's menu command
 */
public class Application {
    public static void main(String[] args) throws Exception {
                CommandLineInterface cli = new CommandLineInterface();
                cli.menu();
        }
    }

