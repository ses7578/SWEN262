import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private Date delayCurrent;
    private Date connectionsMin;
    private static ArrayList<Airport> airports = new ArrayList<>();

    /**
     * Creates a list of all airports.txt
     * @throws IOException if there is not an ability to access the URL
     */
    Airport() throws IOException, ParseException {
        createAirports();
    }

    /**
     * Helper class to make the individual airports.txt
     * @param airportCode 3 character code unique to each airport
     * @param airportName the name of the airport
     * @param delay the current delay time for the airport
     * @param connections the amount of time required between connections
     */
    private Airport(String airportCode, String airportName, String delay, String connections) throws ParseException {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.delay = delay;
        DateFormat dateFormat = new SimpleDateFormat("mm");
        connectionsMin = dateFormat.parse(connections);
        delayCurrent = dateFormat.parse(delay);
    }

    /**
     * Takes all text files and puts them into the airports.txt and then into the lists
     * @throws IOException if there is not an ability to access the URL
     */
    private void createAirports() throws IOException, ParseException {
        URL f = new URL("http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/airports.txt");
        URL c = new URL("http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/delays.txt");
        URL file = new URL("http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/connections.txt");
        Scanner scan = new Scanner(c.openStream());
        Scanner scanner1 = new Scanner(file.openStream());
        Scanner scanner = new Scanner(f.openStream());
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
     * @return the minimum length of time for connections
     */
    long getConnectionsMin()
    {
        return connectionsMin.getTime();
    }

    /**
     * @param obj the airport being compared to
     * @return if the airports.txt are the same
     */
    boolean equals(Airport obj) {
        return this.airportCode == obj.airportCode;
    }

    @Override
    public String toString() {
        // Returns airport info in string formatted for airport command
        return "airport,"+airportName+","+weather+","+delay;
    }

}
