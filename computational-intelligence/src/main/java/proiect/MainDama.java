package proiect;

import proiect.ad.Population;
import proiect.dame.Dama;
import proiect.dame.DamaGa;
import proiect.salesman.Salesman;
import proiect.util.IndividualManager;

import java.io.FileNotFoundException;

/**
 * Created by dragos on 6/5/17.
 */
public class MainDama {

    public static void main(String[] args) throws FileNotFoundException {

        for(int i = 0;i < 100; i++){
            IndividualManager.addIndividual(new Dama(i));
        }

        // Initialize population
        Population pop = new DamaGa().randomize(50);
        System.out.println("Initial error: " + pop.getFittest().getFitness());

        // Evolve population for 100 generations
        pop = pop.evolve();
        for (int i = 0; i < 2000 && pop.getFittest().getFitness() != 0; i++) {
            pop = pop.evolve();
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final error: " + pop.getFittest().getFitness());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
}
