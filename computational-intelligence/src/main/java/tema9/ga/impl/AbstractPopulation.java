package tema9.ga.impl;

import tema9.ga.Individual;
import tema9.ga.Population;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by dragos on 5/21/17.
 */
public abstract class AbstractPopulation implements Population{

    private List<Individual> individualList;

    public AbstractPopulation(int populationSize) {
        individualList = new ArrayList<>();
        IntStream.range(0, populationSize).forEach(i->this.addIndividual(null));
    }

    public int populationSize() {
        return this.individualList.size();
    }

    private void addIndividual(Individual individual) {
        individualList.add(individual);
    }

    public void saveIndividual(int index, Individual individual) {
        individualList.set(index, individual);
    }

    public Individual getIndividual(int index) {
        return individualList.get(index);
    }

    public List<Individual> getIndividualList() {
        return individualList;
    }
}
