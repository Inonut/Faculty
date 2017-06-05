package proiect;

import proiect.ad.Individual;
import proiect.salesman.City;
import proiect.salesman.Salesman;
import proiect.ad.Population;
import proiect.util.Constants;
import proiect.util.IndividualManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by dragos on 6/4/17.
 */
public class MainSalesman {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(new File(Constants.resource + "/salesmanData.in")));

        while (scanner.hasNextInt()){
            IndividualManager.addIndividual(new City(scanner.nextInt(), scanner.nextInt()));
        }

        IndividualManager.setFirst(IndividualManager.getIndivudual(0));
        IndividualManager.setLast(IndividualManager.getIndivudual(IndividualManager.numberOfIndividual() - 1));

        scanner.close();

        // Initialize population
        Population pop = new Salesman().randomize(50);
        System.out.println("Initial distance: " + pop.getFittest().getFitness());

        // Evolve population for 100 generations
        pop = pop.evolve();
        for (int i = 0; i < 100; i++) {
            pop = pop.evolve();
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getFitness());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
}
