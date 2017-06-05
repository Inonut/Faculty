package tema9.old.ga;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by dragos on 5/21/17.
 */
public abstract class Population {

    private List<Individual> individualList;

    public Population(int populationSize) {
        individualList = new ArrayList<>();
        IntStream.range(0, populationSize).forEach(i->this.addIndividual(null));
    }

    public abstract Individual getNewIndivudual();

    public void generatePopulation() {
        for (int i = 0; i < populationSize(); i++) {
            Individual newIndivudual = this.getNewIndivudual();
            newIndivudual.generateIndividual();
            saveIndividual(i, newIndivudual);
        }
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

    public Individual getFittest() {
        return individualList.stream().max(Comparator.comparingDouble(Individual::getFitness)).orElse(null);
    }

    public int populationSize() {
        return this.individualList.size();
    }
}
