import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Shannon Sloan
 */
public class Reservation
{
    private static HashMap<String, Flight> reservations = new HashMap<>();
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

    @Override
    public String toString()
    {
        return passenger+" has a reservation for "+flight;
    }
}