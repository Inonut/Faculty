package tema9.p3;

import tema9.ga.Population;
import tema9.p3.model.City;
import tema9.p3.model.PopulationTour;
import tema9.p3.model.Road;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by dragos on 5/21/17.
 */
public class TSP_GA {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileInputStream(new File("computational-intelligence/src/main/java/tema9/genetic.in")));

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        for(int i=1; i<=n; i++){
            TourManager.addCity(new City(i));
        }
        for(int i=1; i<=m; i++){
            Integer from = scanner.nextInt()-1;
            Integer to = scanner.nextInt()-1;
            Integer cost = scanner.nextInt();

            City cityFrom = TourManager.getCity(from);
            City cityTo = TourManager.getCity(to);
            cityFrom.getRoads().add(new Road(cityTo, cost));
            cityTo.getRoads().add(new Road(cityFrom, cost));
        }

        Population pop = new PopulationTour(50);
        pop.generate();
        System.out.println("Initial distance: " + (1./pop.getFittest().getFitness()));

        pop = pop.evolve();
        for (int i = 0; i < 100; i++) {
            pop = pop.evolve();
        }

        System.out.println("Finished");
        System.out.println("Final distance: " + (1./pop.getFittest().getFitness()));
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
}