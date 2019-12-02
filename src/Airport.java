import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileLockInterruptionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class to hold all airports.txt
 * @author: Shannon
 */
public class Airport
{
    String airportCode;
    private String airportName;
    private Weather weather;
    private String delay;
    private String connections;
    private static ArrayList<Airport> airports = new ArrayList<>();


    /**
     * Creates a list of all airports.txt
     * @throws IOException if there is not an ability to access the URL
     */
    Airport() throws IOException
    {
        createAirports();
    }

    /**
     * Helper class to make the individual airports.txt
     * @param airportCode 3 character code unique to each airport
     * @param airportName the name of the airport
     * @param delay the current delay time for the airport
     * @param connections the amount of time required between connections
     */
    private Airport(String airportCode, String airportName, String delay, String connections)
    {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.delay = delay;
        this.connections = connections;
    }

    /**
     * Takes all text files and puts them into the airports.txt and then into the lists
     * @throws IOException if there is not an ability to access the URL
     */
    private void createAirports() throws IOException
    {
        URL f = new URL("http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/airports.txt");
        URL c = new URL("http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/delays.txt");
        URL file = new URL("http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/connections.txt");
        Scanner scan = new Scanner(c.openStream());
        Scanner scanner1 = new Scanner(file.openStream());
        Scanner scanner = new Scanner(f.openStream());
        /*File f = new File("C:\\Users\\shann\\IdeaProjects\\SWEN262\\src\\airports.txt");
        File c = new File("C:\\Users\\shann\\IdeaProjects\\SWEN262\\src\\delays.txt");
        File file = new File("C:\\Users\\shann\\IdeaProjects\\SWEN262\\src\\connections.txt");
        Scanner scanner = new Scanner(f);
        Scanner scan = new Scanner(c);
        Scanner scanner1 = new Scanner(file);*/
        while(scanner.hasNextLine())
        {
            String s = scanner.nextLine();
            String[] airport = s.split(",");
            String x = scan.nextLine();
            String[] delay = x.split(",");
            String t = scanner1.nextLine();
            String[] connection = t.split(",");
            airports.add(new Airport(airport[0], airport[1], delay[1], connection[1]));
        }
        new Weather();
        for(Airport a: airports)
        {
            a.addWeather(Weather.getWeather(a));
        }

    }

    /**
     * Puts the weather for the flight
     * @param weather the weather for that airport
     */
    private void addWeather(Weather weather)
    {
        this.weather = weather;
    }

    /**
     * Gets an airport with a specific code
     * @param aCode the airport code that is being looked for
     * @return the airport of the specific type
     */
    static Airport getAirport(String aCode)
    {
        aCode = aCode.toUpperCase();
        for(Airport a: airports)
        {
            if(a.getAirportCode().equals(aCode))
                return a;
        }
        return null;
    }


    /**
     * @return the airport code for that specific airport
     */

    String getAirportCode()
    {
        return this.airportCode;
    }

    /**
     * @param obj the airport being comapred to
     * @return if the airports.txt are the same
     */
    boolean equals(Airport obj) {
        return this.airportCode == obj.airportCode;
    }

    @Override
    public String toString() {
        // Returns airport info in string formatted for airport command
        return "airport," + airportName + "," + weather + "," + delay;
    }

}
