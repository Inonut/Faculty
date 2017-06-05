package tema9.problema3.model;

import tema9.problema3.TourManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dragos on 5/21/17.
 */
public class Population {

    // Holds population of tours
    private List<Tour> tours;

    // Construct a population
    public Population(int populationSize, boolean initialise) {
        tours = new ArrayList<>();
        // If we need to initialise a population of tours do so
        for (int i = 0; i < populationSize(); i++) {
            if (initialise) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            } else {
                tours.add(null);
            }
        }

    }

    // Saves a tour
    public void saveTour(int index, Tour tour) {
        tours.set(index, tour);
    }

    // Gets a tour from population
    public Tour getTour(int index) {
        return tours.get(index);
    }

    // Gets the best tour in the population
    public Tour getFittest() {
        Tour fittest = tours.get(0);
        // Loop through individuals to find fittest
        for (int i = 0; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    // Gets population size
    public int populationSize() {
        return tours.size();
    }
}
