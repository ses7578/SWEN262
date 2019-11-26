import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Shannon Sloan
 */
public class Reservation {
    static HashMap<String, Flight> reservations = new HashMap<>();
    static ArrayList<Reservation> reservation = new ArrayList<>();
    String passenger;
    Flight flight;

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
        reservations.put(p, f);
        Itinerary.addFlight(f, p);
    }

    /**
     * Gets the reservation
     * @param p passenger
     * @param origin the starting destination
     * @param destination the ending destination
     * @return the reservation
     */
    public static Reservation getReservation(String p, Airport origin, Airport destination)
    {
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p)&&r.flight.originAirport.equals(origin)&&(r.flight.destinationAirport.equals(destination)))
                return r;
        }
        return null;
    }

    /**
     * Makes a specific reservation
     * @param p the passenger
     * @param origin
     * @param destination
     */
    public static void makeReservation(String p, Airport origin, Airport destination)
    {

    }

    /**
     * deletes a specific reservation
     * @param p the passenger
     * @param origin
     * @param destination
     */
    public static void deleteReservation(String p, Airport origin, Airport destination)
    {
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p)&&r.flight.originAirport.equals(origin)&&r.flight.destinationAirport.equals(destination))
            {
                reservation.remove(r);
                Itinerary.removeFlight(p, origin, destination);
            }
        }
    }

    @Override
    public String toString()
    {
        return passenger+" has a reservation for "+flight;
    }
}
