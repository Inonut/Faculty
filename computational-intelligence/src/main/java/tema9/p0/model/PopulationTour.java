package tema9.p0.model;


import com.sun.org.apache.bcel.internal.generic.POP;
import tema9.ga.Individual;
import tema9.ga.Population;
import tema9.ga.impl.AbstractIndividual;
import tema9.ga.impl.AbstractPopulation;
import tema9.util.Constants;

import java.util.Comparator;
import java.util.List;

/**
 * Created by dragos on 5/21/17.
 */
public class PopulationTour extends AbstractPopulation {

    public PopulationTour(int populationSize) {
        super(populationSize);
    }

    @Override
    public void generate() {
        for (int i = 0; i < populationSize(); i++) {
            Individual newIndivudual = new Tour();
            newIndivudual.generate();
            saveIndividual(i, newIndivudual);
        }
    }

    @Override
    public Individual getFittest() {
        return this.getIndividualList().stream().max(Comparator.comparingDouble(Individual::getFitness)).orElse(null);
    }

    @Override
    public Population evolve() {
        PopulationTour newPopulation = new PopulationTour(this.populationSize());

        int elitismOffset = 0;
        if (Constants.elitism) {
            newPopulation.saveIndividual(0, this.getFittest());
            elitismOffset = 1;
        }

        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            Individual parent1 = tournamentSelection(this);
            Individual parent2 = tournamentSelection(this);

            Individual child = parent1.crossover(parent2);
            child.mutate();
            newPopulation.saveIndividual(i, child);
        }

        return newPopulation;
    }

    //Selecţia turnir
    private Individual tournamentSelection(AbstractPopulation pop) {
        PopulationTour tournament = new PopulationTour(Constants.tournamentSize);

        for (int i = 0; i < Constants.tournamentSize; i++) {
            int randomId = Constants.rand.nextInt(pop.populationSize());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        return tournament.getFittest();
    }

    @Override
    public void mutate() {
        this.getIndividualList().forEach(Individual::mutate);
    }
}
