package tema9.problema3;

/**
 * Created by dragos on 5/21/17.
 */
import tema9.problema3.model.City;

import java.util.ArrayList;

public class TourManager {

    // Holds our cities
    private static ArrayList<City> destinationCities = new ArrayList<>();

    // Adds a destination city
    public static void addCity(City city) {
        destinationCities.add(city);
    }

    // Get a city
    public static City getCity(int index){
        return destinationCities.get(index);
    }

    // Get the number of destination cities
    public static int numberOfCities(){
        return destinationCities.size();
    }
}
