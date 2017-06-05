package tema9.problema3.model;

/**
 * Created by dragos on 5/21/17.
 */

import tema9.problema3.TourManager;

import java.util.ArrayList;
import java.util.Collections;

public class Tour{

    // Holds our tour of cities
    private ArrayList<City> tour = new ArrayList<>();

    // Constructs a blank tour
    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }

    public Tour(ArrayList tour){
        this.tour = tour;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination cities and add them to our tour
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            setCity(i, TourManager.getCity(i));
        }
        // Randomly reorder the tour
        Collections.shuffle(tour);
    }

    // Gets a city from the tour
    public City getCity(int tourPosition) {
        return tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
    }

    // Gets the tours fitness
    public double getFitness() {
        return 1./getCost();
    }

    public int getCost(){
        int tourCost = 0;
        // Loop through our tour's cities
        for (int i=0; i < tourSize(); i++) {
            // Get city we're travelling from
            City fromCity = getCity(i);
            // City we're travelling to
            City destinationCity;
            // Check we're not on our tour's last city, if we are set our
            // tour's final destination city to our starting city
            if(i+1 < tourSize()){
                destinationCity = getCity(i+1);
            }
            else{
                destinationCity = getCity(0);
            }
            // Get the distance between the two cities
            tourCost += fromCity.costTo(destinationCity);
        }
        return tourCost;
    }

    // Get number of cities on our tour
    public int tourSize() {
        return tour.size();
    }

    // Check if the tour contains a city
    public boolean containsCity(City city){
        return tour.contains(city);
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i)+"|";
        }
        return geneString;
    }
}
