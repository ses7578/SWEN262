import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Shannon Sloan
 */
public class Weather
{
    private String airportCode;
    private ArrayList<String> airportWeather = new ArrayList<>();
    private ArrayList<String> airportTemp = new ArrayList<>();
    private int curIndex;
    private static ArrayList<Weather> weather = new ArrayList<>();

    /**
     * takes the weather and puts it in a list for all of the airports.txt
     * @throws IOException if the URL is not found
     */
    Weather() throws IOException
    {
        createWeather();
    }

    /**
     * creates weather for a specific airport
     * @param aCode airport code
     * @param weather the list of weathers
     * @param temperature the list of temperatures
     */
    private Weather(String aCode, ArrayList<String> weather, ArrayList<String> temperature)
    {
        airportCode = aCode;
        airportWeather = weather;
        airportTemp = temperature;
        curIndex = 0;
    }

    /**
     * Creates the weather
     * @throws IOException if the URL is not found
     */
    private void createWeather() throws IOException
    {
        URL url = new URL("http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/weather.txt");
        Scanner scanner = new Scanner(url.openStream());
        /*File url = new File("C:\\Users\\shann\\IdeaProjects\\SWEN262\\src\\weather.txt");
        Scanner scanner = new Scanner(url);*/
        while(scanner.hasNextLine())
        {
            String s = scanner.nextLine();
            String[] split = s.split(",");

            // Arraylists to store weather and temperature
            ArrayList<String> weatherCondition = new ArrayList();
            ArrayList<String> temperature = new ArrayList();
            for(int i = 1; i<split.length; i+=2)
            {
                // Adds weather and temp to list
                weatherCondition.add(split[i]);
                temperature.add(split[i+1]);
            }
            // Adds weather and temp lists to weather
            weather.add(new Weather(split[0],weatherCondition, temperature));
        }
    }

    /**
     * gets the weather for a specific airport
     * @param a the airport
     * @return the weather
     */
    static Weather getWeather(Airport a)
    {
        for(Weather w : weather)
        {
            if(w.airportCode.equals(a.airportCode))
                return w;
        }
        return null;
    }

    /**
     * Returns the string in the expected format for the airport command
     * @return
     */
    @Override
    public String toString()
    {

        // gets weather and temp at current index
        String s = this.airportWeather.get(curIndex) + "," + this.airportTemp.get(curIndex);

        // Iterates index
        this.curIndex++;

        // If all weather has happened, loops to beginning
        if(curIndex == this.airportWeather.size()){
            this.curIndex = 0;
        }

        return s;
    }
}
