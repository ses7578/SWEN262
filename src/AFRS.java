import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main server for the class
 * @author: Shannon
 */
public class AFRS
{
    /**
     * gets the reservation for a specific passenger on a specific flight
     * @param p passenger
     * @return the reservation for that specific person has
     */
    public static String getReservation(String p)
    {
        return Reservation.getReservation(p);
    }

    /**
     * gets the reservation for a specific passenger on a specific flight
     * @param p passenger
     * @param o origin airport
     * @return the reservation for that specific person has
     */
    public static String getReservation(String p, String o)
    {
        return Reservation.getReservation(p, o);
    }

    /**
     * gets the reservation for a specific passenger on a specific flight
     * @param p passenger
     * @param o origin airport
     * @param d destination airport
     * @return the reservation for that specific person has
     */
    public static String getReservation(String p, String o, String d)
    {
        return Reservation.getReservation(p, o, d);
    }

    /**
     *
     * @param o the origin airport
     * @param d the destination airport
     * @return a flight connecting the two airports.txt
     */
    private static Flight getFlight(Airport o, Airport d)
    {
        return Flight.getFlight(o, d);
    }

    private static String getInfo(Airport o, Airport d, int ... connections)
    {
        return Itinerary.getFlights(o, d, connections);
    }
    /**
     *
     * @param o the starting airport
     * @param d the ending airport
     * @return the flights.txt that go between those two
     */
    public static ArrayList<Flight> getAllFlights(Airport o, Airport d)
    {
        return Flight.getAllFlights(o, d);
    }

    /**
     *
     * @param aCode the airport code
     * @return the airport with that code
     */
    private static Airport getAirport(String aCode)
    {
        return Airport.getAirport(aCode);
    }

    /**
     *
     * @param id the id of the itinerary being used
     * @param p passenger
     */
    public static String makeReservation(int id, String p)
    {
        return Reservation.makeReservation(id, p);
    }

    /**
     * deletes a specific reservation for a passenger
     * @param p passenger
     * @param o origin airport
     * @param d destination airport
     */
    private static void deleteReservation(String p, Airport o, Airport d)
    {
        Reservation.deleteReservation(p, o, d);
    }

//    private static String retrieveReservation()
//    {
//        Reservation.getReservation()
//    }

    public static void main(String[] args) throws IOException, ParseException {
        new Airport();
        new Flight();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a command");
        String input;
        while(!(input = scan.nextLine()).equals("quit"))
        {
            input = input.replace(" ", "");
            String[] key = input.split(",", -1);
            switch(key[0])
            {
                case "airport":
                    if(key.length!=2)
                        System.out.println("error,unknown request");
                    else
                        System.out.println(AFRS.getAirport(key[1]));
                    break;
                case "info":
                    if(key.length == 3) {
                        Airport o = AFRS.getAirport(key[1]);
                        Airport d = AFRS.getAirport(key[2]);
                        System.out.println(AFRS.getInfo(o, d));
                    }
                    else if (key.length == 4){
                        Airport o = AFRS.getAirport(key[1]);
                        Airport d = AFRS.getAirport(key[2]);
                        int minConnections = Integer.parseInt(key[3]);
                        System.out.println(AFRS.getInfo(o,d,minConnections));
                    }
                    else if (key.length == 5){
                        Airport o = AFRS.getAirport(key[1]);
                        Airport d = AFRS.getAirport(key[2]);
                        int minConnections = 1;
                        try {
                            minConnections = Integer.parseInt(key[3]);
                        }
                        catch (NumberFormatException e){
                            minConnections = 1;
                        }
                        // TODO - Sort returned itineraries based off of 5th arg
                        System.out.println(AFRS.getInfo(o,d,minConnections));
                    }
                    else{
                        System.out.println("partial-request");
                    }
                    break;
                case "reserve":
                    if(key.length == 3){
                        int id = Integer.parseInt(key[1]);
                        String passenger = key[2];
                        System.out.println(AFRS.makeReservation(id, passenger));
                    }
                    else{
                        System.out.println("partial-request");
                    }
                    break;
                case "help":
                    System.out.println("These are the commands you can run: ");
                    System.out.println("airport,airportCode");
                    System.out.println("info,airportCode1,airportCode2");
                    break;
                case "delete":
                    String p = key[1];
                    Airport oA = AFRS.getAirport(key[2]);
                    Airport dA = AFRS.getAirport(key[3]);
                    AFRS.deleteReservation(p, oA, dA);
                    break;
                case "retrieve":
                    if(key.length == 2){
                        String passenger = key[1];
                        System.out.println(AFRS.getReservation(passenger));
                    }
                    else if(key.length == 3){
                        String passenger = key[1];
                        Airport origin = AFRS.getAirport(key[2]);
                        if (origin == null){
                            System.out.println("error,unknown origin");
                        }
                        else{
                            System.out.println(AFRS.getReservation(passenger, origin.airportCode));
                        }
                    }
                    else if(key.length == 4){
                        String passenger = key[1];
                        String originCode = "";
                        // If the origin is not empty
                        if(!key[2].equals("")){
                            Airport origin = AFRS.getAirport(key[2]);
                            if (origin == null){
                                System.out.println("error,unknown origin");
                                break;
                            }
                            originCode = origin.airportCode;
                        }
                        Airport dest = AFRS.getAirport(key[3]);
                        if (dest == null){
                            System.out.println("error,unknown destination");
                            break;
                        }
                        else{
                            System.out.println(AFRS.getReservation(passenger, originCode, dest.airportCode));
                        }
                    }
                    else{
                        System.out.println("partial-request");
                    }
                    break;
                default:
                    System.out.println("error,unknown request");
                    break;
            }
        }
    }
}
