import java.util.Comparator;
import java.util.List;

public class SortByArrival implements SortAlgo{
    @Override
    public void sortOrder(List<Flight> flights){
        flights.sort(Comparator.comparing(Flight::getaTime));
    }
}
