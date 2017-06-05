package tema9.old.ga;

import tema9.util.Constants;

/**
 * Created by dragos on 5/21/17.
 */
public abstract class GeneticAlgotithm {

    public abstract Population getNewPopulation(int populationSize);

    public Population evolvePopulation(Population pop) {
        Population newPopulation = this.getNewPopulation(pop.populationSize());

        int elitismOffset = 0;
        if (Constants.elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
            elitismOffset = 1;
        }

        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            Individual parent1 = tournamentSelection(pop);
            Individual parent2 = tournamentSelection(pop);

            Individual child = parent1.crossover(parent2);
            child.mutate();
            newPopulation.saveIndividual(i, child);
        }

        return newPopulation;
    }

    //Selecţia turnir
    private Individual tournamentSelection(Population pop) {
        Population tournament = this.getNewPopulation(Constants.tournamentSize);

        for (int i = 0; i < Constants.tournamentSize; i++) {
            int randomId = Constants.rand.nextInt(pop.populationSize());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        return tournament.getFittest();
    }

}
