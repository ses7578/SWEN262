import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * The main class to run everything
 * @author: Shannon
 */
public class Client {

    public static void main(String[] args) throws IOException, ParseException {
        AFRS.setup();
        Scanner scan = new Scanner(System.in);
        String input;
        while(!(input=scan.nextLine()).equals("quit"))
        {
            AFRS.handleRequest(input);
        }
    }
}
