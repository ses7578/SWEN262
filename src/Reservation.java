import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Shannon Sloan
 */
public class Reservation
{
    //private static HashMap<String, Flight> reservations = new HashMap<>();
    private static ArrayList<Reservation> reservation = new ArrayList<>();
    private String passenger;
    private Flight flight;

    /**
     * Creates a reservation
     * @param p passenger
     * @param f flight
     */
    public Reservation(String p, Flight f)
    {
        passenger = p;
        flight = f;
        reservation.add(this);
        //reservations.put(p, f);
        Itinerary.addFlight(f, p);
    }

    /**
     * Gets the reservation
     * @param p passenger
     * @param origin the starting destination
     * @param destination the ending destination
     * @return the reservation
     */
    static Reservation getReservation(String p, Airport origin, Airport destination)
    {
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p)&&r.flight.getOriginAirport().equals(origin)&&(r.flight.getDestinationAirport().equals(destination)))
                return r;
        }
        return null;
    }

    /**
     * Makes a specific reservation
     * @param p the passenger
     * @param origin the origin airport
     * @param destination the destination airport
     */
    public static void makeReservation(String p, Airport origin, Airport destination)
    {
        ArrayList<Flight> flight= getFlight(origin, destination);
        for(Flight f: flight) {
            new Reservation(p, f);
        }
    }

    /**
     * deletes a specific reservation
     * @param p the passenger
     * @param origin the origin airport
     * @param destination the destination airport
     */
    static void deleteReservation(String p, Airport origin, Airport destination)
    {
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p)&&r.flight.getOriginAirport().equals(origin)&&r.flight.getDestinationAirport().equals(destination))
            {
                reservation.remove(r);
                Itinerary.removeFlight(p, origin, destination);
            }
        }
    }

    /**
     * gets flights between airports
     * @param origin the origin airport
     * @param destination the final airport
     * @return a list of flights that will get you to the final destination
     */
    private static ArrayList<Flight> getFlight(Airport origin, Airport destination)
    {
        ArrayList<Flight> flights = new ArrayList<>();
        ArrayList<Flight> one = Flight.getFlight(origin);
        boolean finished = false;
        for(Flight f: one)
        {
            if(f.getDestinationAirport().equals(destination))
            {
                flights.add(f);
                finished = true;
                break;
            }
        }
        int count = -1;
        while(!finished&&count<one.size()-1)
        {
            count++;
            ArrayList<Flight> two = Flight.getFlight(one.get(count).getDestinationAirport());
            for(Flight x: two)
            {
                if(x.getDestinationAirport().equals(destination))
                {
                    flights.add(one.get(count));
                    flights.add(x);
                    finished = true;
                    break;
                }
            }
        }
        return flights;
    }

    @Override
    public String toString()
    {
        return passenger+" has a reservation for "+flight;
    }

    public static void main(String[] args) throws IOException {
        new Airport();
        new Flight();
        Airport o = Airport.getAirport("LAX");
        Airport d = Airport.getAirport("SEA");
        Reservation.makeReservation("Bob", o, d);
        System.out.println(Itinerary.getPrice("Bob"));
    }
}
