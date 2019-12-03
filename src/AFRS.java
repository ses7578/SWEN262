import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

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
    private static String getReservation(String p)
    {
        return Reservation.getReservation(p);
    }

    /**
     * gets the reservation for a specific passenger on a specific flight
     * @param p passenger
     * @param o origin airport
     * @return the reservation for that specific person has
     */
    private static String getReservation(String p, String o)
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
    private static String getReservation(String p, String o, String d)
    {
        return Reservation.getReservation(p, o, d);
    }


    /**
     * Gets the info for a trip
     * @param o origin airport
     * @param d destination airport
     * @param connections the amount of connections
     * @return the string of itinerarys
     */
    private static String getInfo(Airport o, Airport d, int ... connections)
    {
        return Itinerary.getFlights(o, d, connections);
    }

    /**
     * Gets the info for a trip
     * @param o origin airport
     * @param d destination airport
     * @param sort the way to sort
     * @param connections the amount of connections
     * @return the string of itinerarys
     */
    private static String getInfo(Airport o, Airport d, String sort, int...connections)
    {
        return Itinerary.getFlights(o, d, sort, connections);
    }


    /**
     * gets the airport with a certain code
     * @param aCode the airport code
     * @return the airport with that code
     */
    private static Airport getAirport(String aCode)
    {
        return Airport.getAirport(aCode);
    }

    /**
     * makes the reservation for a passenger
     * @param id the id of the itinerary being used
     * @param p passenger
     */
    private static String makeReservation(int id, String p)
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


    /**
     * Handles all requests
     * @param input the request
     */
    static void handleRequest(String input)
    {
        input = input.replace(" ", "");
        String[] key = input.split(",", -1);
        switch(key[0])
        {
            case "airport":
                if(key.length!=2)
                    System.err.println("partial-request");
                else
                    System.out.println(AFRS.getAirport(key[1]));
                break;
            case "info":
                if (key.length == 3) {
                    Airport o = AFRS.getAirport(key[1]);
                    Airport d = AFRS.getAirport(key[2]);
                    if(o == null)
                        System.err.println("error,unknown origin");
                    else if(d==null)
                        System.err.println("error,unknown destination");
                    else
                        System.out.println(AFRS.getInfo(o, d));
                }
                else if (key.length == 4){
                    Airport o = AFRS.getAirport(key[1]);
                    Airport d = AFRS.getAirport(key[2]);
                    int minConnections = Integer.parseInt(key[3]);
                    if(o == null)
                        System.err.println("error,unknown origin");
                    else if(d == null)
                        System.err.println("error,unknown destination");
                    else if(minConnections>2)
                        System.err.println("error,invalid connection limit");
                    else
                        System.out.println(AFRS.getInfo(o,d,minConnections));
                }
                else if (key.length == 5){
                    Airport o = AFRS.getAirport(key[1]);
                    Airport d = AFRS.getAirport(key[2]);
                    int minConnections = 0;
                    try {
                        minConnections = Integer.parseInt(key[3]);
                    }
                    catch (NumberFormatException ignored){
                    }
                    String sort = key[4];
                    if(o == null)
                        System.err.println("error,unknown origin");
                    else if(d == null)
                        System.err.println("error,unknown destination");
                    else if(minConnections>2)
                        System.err.println("error,invalid connection limit");
                    else
                        System.out.println(AFRS.getInfo(o,d,sort,minConnections));
                }
                else{
                    System.err.println("partial-request");
                }
                break;
            case "reserve":
                if(key.length == 3){
                    int id = Integer.parseInt(key[1]);
                    String passenger = key[2];
                    System.out.println(AFRS.makeReservation(id, passenger));
                }
                else{
                    System.err.println("partial-request");
                }
                break;
            case "help":
                System.out.println("These are the commands you can run: ");
                System.out.println("airport,airportCode");
                System.out.println("info,origin,destination[,connections[,sort-order]]");
                System.out.println("reserve,id,passenger");
                System.out.println("retrieve,passenger[,origin[,destination]]");
                System.out.println("delete,passenger,origin,destination");
                break;
            case "delete":
                if(key.length == 4) {
                    String p = key[1];
                    Airport oA = AFRS.getAirport(key[2]);
                    Airport dA = AFRS.getAirport(key[3]);
                    AFRS.deleteReservation(p, oA, dA);
                }
                else
                    System.err.println("partial-request");
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
                        System.err.println("error,unknown origin");
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
                        System.err.println("error,unknown destination");
                        break;
                    }
                    else{
                        System.out.println(AFRS.getReservation(passenger, originCode, dest.airportCode));
                    }
                }
                else{
                    System.err.println("partial-request");
                }
                break;
            default:
                System.err.println("error,unknown request");
                break;
        }

    }

    /**
     * Sets up the Airport and Flights
     * @throws IOException if there is an error reading the file
     * @throws ParseException if there is an error making a String a number
     */
    static void setup() throws IOException, ParseException {
        new Airport();
        new Flight();
    }

}
