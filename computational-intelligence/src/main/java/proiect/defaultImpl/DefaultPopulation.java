package proiect.defaultImpl;

import proiect.ad.Individual;
import proiect.ad.Population;
import proiect.util.Constants;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by dragos on 6/4/17.
 */
public abstract class DefaultPopulation extends Population {

    public DefaultPopulation() {
    }

    public DefaultPopulation(List<Individual> tourList) {
        this.setIndividualList(tourList);
    }

    protected abstract DefaultPopulation newPopulation();

    protected abstract DefaultIndividual newIndividual();

    @Override
    public Population evolve() {
        DefaultPopulation population = newPopulation();

        int offset = 0;
        if (Constants.ELITISM) {
            population.addIndividual(this.getFittest());
            offset = 1;
        }

        for (int i = offset; i < this.individualSize(); i++) {
            Individual parent1 = tournamentSelection();
            Individual parent2 = tournamentSelection();
            population.addIndividual(parent1.cossover(parent2).mutate());
        }

        return population;
    }

    private Individual tournamentSelection() {

        DefaultPopulation defaultPopulation = newPopulation();
        Random random = new Random();
        IntStream.range(0, Constants.TOURNAMENT_SIZE).boxed().forEach(i -> defaultPopulation.addIndividual(this.getIndividual(random.nextInt(this.individualSize()))));
        return defaultPopulation.getFittest();
    }

    @Override
    public Individual getFittest() {
        return this.getIndividualList().stream().min(Comparator.comparingDouble(Individual::getFitness)).orElse(null);
    }

    @Override
    public Population randomize(int individualSize) {
        Population population = newPopulation();
        population.setIndividualList(IntStream.range(0, individualSize).boxed().map(i -> newIndividual().randomize()).collect(Collectors.toList()));
        return population;
    }
}
