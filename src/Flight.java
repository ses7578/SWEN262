import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: Shannon
 */
public class Flight
{
    private int flightID;
    private Airport originAirport;
    private Airport destinationAirport;
    private String departureTime;
    private String arrivalTime;
    private int airfare;
    private static ArrayList<Flight> flights = new ArrayList<>();

    /**
     * Creates the list of flights
     * @throws IOException if the URL is not found
     */
    Flight() throws IOException
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
    private Flight(Airport o, Airport d, String dT, String aT, int flightID, int airfare)
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
    private void createFlight() throws IOException
    {
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
    }

    static Flight getFlight(Airport origin, Airport destination)
    {
        for(Flight f: flights)
        {
            if(f.originAirport.equals(origin)&& f.destinationAirport.equals(destination))
            {
                return f;
            }
        }
        return null;
    }

    static ArrayList<Flight> getAllFlights(Airport origin, Airport destination)
    {
        ArrayList<Flight> f = new ArrayList<>();
        for(Flight flight: flights)
        {
            if(flight.originAirport.equals(origin)&&flight.destinationAirport.equals(destination))
            {
                f.add(flight);
            }
        }
        return f;
    }

    Airport getOriginAirport()
    {
        return originAirport;
    }

    Airport getDestinationAirport()
    {
        return destinationAirport;
    }

    @Override
    public String toString()
    {
        return "Flight number "+flightID+": from Airport " + originAirport.getAirportCode()+" to Airport "+destinationAirport.getAirportCode()
                +" Departure: "+departureTime+ " Arrival: " + arrivalTime+" Cost: $"+airfare;
    }

}
