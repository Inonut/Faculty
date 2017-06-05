package tema9.old.ga;

/**
 * Created by dragos on 5/21/17.
 */
public interface Individual {

    void generateIndividual();

    double getFitness();

    Individual crossover(Individual individual);

    void mutate();
}
