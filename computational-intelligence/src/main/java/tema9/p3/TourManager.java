package tema9.p3;

/**
 * Created by dragos on 5/21/17.
 */

import tema9.p3.model.City;

import java.util.ArrayList;
import java.util.List;

public class TourManager{

    private static List<City> destinationCities = new ArrayList<>();

    public static void addCity(City city) {
        destinationCities.add(city);
    }

    public static City getCity(int index){
        return destinationCities.get(index);
    }

    public static List<City> getCityList(){
        return destinationCities;
    }

    public static int numberOfCities(){
        return destinationCities.size();
    }
}
