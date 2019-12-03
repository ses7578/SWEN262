import java.util.ArrayList;

/**
 * @author Shannon Sloan
 */
public class Reservation
{
    private static ArrayList<Reservation> reservation = new ArrayList<>();
    private String passenger;
    private Itinerary itinerary;

    /**
     * Creates a reservation
     * @param p passenger
     */
    private Reservation(String p, Itinerary i)
    {
        passenger = p;
        itinerary = i;
        reservation.add(this);
    }

    /**
     * Gets the reservation for a specific person
     * @param p passenger
     * @return the reservation
     */
    static String getReservation(String p)
    {
        ArrayList<Reservation> passengerReservations = new ArrayList<>();
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p))
                passengerReservations.add(r);
        }
        StringBuilder s = new StringBuilder("retrieve," + passengerReservations.size());
        for(Reservation r: passengerReservations){
            s.append(r.toString());
        }
        return s.toString();

    }

    /**
     * Gets the reservation at a specific airport
     * @param p the passenger
     * @param origin the origin airport
     * @return the reservation
     */
    static String getReservation(String p, String origin)
    {
        ArrayList<Reservation> passengerReservations = new ArrayList<>();
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p)&&r.itinerary.origin.equals(origin))
                passengerReservations.add(r);
        }
        StringBuilder s = new StringBuilder("retrieve," + passengerReservations.size());

        for(Reservation r: passengerReservations){
            s.append(r.toString());
        }
        return s.toString();
    }

    /**
     * Gets the reservation for the specific person to a specific place
     * @param p the passenger
     * @param origin the origin airport
     * @param destination the destination airport
     * @return the reservation
     */
    static String getReservation(String p, String origin, String destination)
    {
        ArrayList<Reservation> passengerReservations = new ArrayList<>();
        for(Reservation r: reservation)
        {
            if(origin.equals("")){
                if (r.passenger.equals(p) && (r.itinerary.dest.equals(destination)))
                    passengerReservations.add(r);
            }
            else {
                if (r.passenger.equals(p) && r.itinerary.origin.equals(origin) && (r.itinerary.dest.equals(destination)))
                    passengerReservations.add(r);
            }
        }
        StringBuilder s = new StringBuilder("retrieve," + passengerReservations.size());
        for(Reservation r: passengerReservations){
            s.append(r.toString());
        }
        return s.toString();
    }

    /**
     * Makes the reservation
     * @param iD the itinerary with that id
     * @param passenger the passenger that is being reserved for
     * @return if the reservation was successful
     */
    static String makeReservation(int iD, String passenger)
    {
        Itinerary i = Itinerary.getItinerary(iD);
        if(i == null)
            return "reserve,failure";
        new Reservation(passenger, i);
        return "reserve,successful";
    }

    /**
     * deletes a specific reservation
     * @param p the passenger
     * @param origin the origin airport
     * @param destination the destination airport
     */
    static void deleteReservation(String p, Airport origin, Airport destination)
    {
        Reservation toRemove = null;
        for(Reservation r: reservation)
        {
            if(r.passenger.equals(p)&&r.itinerary.origin.equals(origin.getAirportCode())&&r.itinerary.dest.equals(destination.getAirportCode()))
            {
                toRemove = r;
            }
        }

        if(toRemove == null){
            System.out.println("error,reservation not found");
        }
        else{
            reservation.remove(toRemove);
            System.out.println("delete,successful");
        }
    }

    @Override
    public String toString()
    {
        return itinerary.toString();
    }

}
