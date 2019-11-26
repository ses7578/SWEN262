import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for the Itinerary for each specific person as well as a way to keep everything together
 * @author: Shannon
 */
public class Itinerary {
    private ArrayList<Flight> flights;
    private String passenger;
    private static HashMap<String, ArrayList<Flight>> itinerary = new HashMap<>();

    /**
     * Creates the itinerary
     * @param flights
     * @param passenger
     */
    public Itinerary(ArrayList<Flight> flights, String passenger)
    {
        this.flights = flights;
        this.passenger = passenger;
        itinerary.put(passenger, flights);
    }

    /**
     * adds a flight to the list
     * @param f the flight
     * @param passenger the passenger
     */
    public static void addFlight(Flight f, String passenger)
    {
        if(itinerary.containsKey(passenger))
            itinerary.get(passenger).add(f);
        else {
            ArrayList<Flight> flights1 = new ArrayList<>();
            flights1.add(f);
            itinerary.put(passenger, flights1);
        }
    }

    /**
     * gets the itinerary for a specific person
     * @param p the name of the passenger whose Itinerary
     * @return Returns the string of the itinerary for the specific
     */
    public static String getItinerary(String p)
    {
        ArrayList<Flight> f = itinerary.get(p);
        Itinerary i = new Itinerary(f, p);
        return i.toString();
    }

    /**
     * removes a flight from the itinerary
     * @param p the passenger
     * @param origin
     * @param destination
     */
    public static void removeFlight(String p, Airport origin, Airport destination)
    {
        ArrayList<Flight> flights = itinerary.get(p);
        for(Flight f: flights)
        {
            if(f.originAirport.equals(origin)&&f.destinationAirport.equals(destination))
            {
                flights.remove(f);
            }
        }
        itinerary.remove(p);
        itinerary.put(p, flights);
    }

    @Override
    public String toString()
    {
        String s = passenger+"'s Itinerary:\n";
        for(Flight f: flights) {
            s = s + f + "\n";
        }
        return s;
    }

 }
