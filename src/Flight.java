import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: Shannon
 */
public class Flight
{
    int flightID;
    Airport originAirport;
    Airport destinationAirport;
    String departureTime;
    String arrivalTime;
    int airfare;
    static ArrayList<Flight> flights = new ArrayList<>();

    /**
     * Creates the list of flights
     * @throws IOException if the URL is not found
     */
    public Flight() throws IOException
    {
        createFlight();
    }

    /**
     * Creates an individual flight
     * @param o origin airport
     * @param d destination airport
     * @param dT departure time
     * @param aT arrival time
     * @param flightID the flight ID
     * @param airfare the cost of the flight
     */
    public Flight(Airport o, Airport d, String dT, String aT, int flightID, int airfare)
    {
        originAirport = o;
        destinationAirport = d;
        departureTime = dT;
        arrivalTime = aT;
        this.flightID = flightID;
        this.airfare = airfare;
    }

    /**
     * Helper to take the file and create a list of flights
     * @throws IOException if the URL is not accessible
     */
    public void createFlight() throws IOException {
        URL url = new URL("http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/flights.txt");
        Scanner scan = new Scanner(url.openStream());
        while(scan.hasNextLine())
        {
            String s = scan.nextLine();
            String[] split = s.split(",");
            Airport o = Airport.getAirport(split[0]);
            Airport d = Airport.getAirport(split[1]);
            flights.add(new Flight(o, d, split[2], split[3],Integer.valueOf(split[4]),
                    Integer.valueOf(split[5])));

        }
        for(Flight f: flights)
        {
            System.out.println(f);
        }
    }

    @Override
    public String toString()
    {
        return "Flight: from Airport " + originAirport.getAirportCode()+" to Airport "+destinationAirport.getAirportCode()
                +" Departure: "+departureTime+ " Arrival: " + arrivalTime+" Cost: $"+airfare;
    }

    public static void main(String[] args) throws IOException {
        new Airport();
        new Flight();
    }
}
