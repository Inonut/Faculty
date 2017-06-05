package tema9.ga;

import java.util.List;

/**
 * Created by dragos on 5/21/17.
 */
public interface Individual {

    void generate();

    double getFitness();

    Individual crossover(Individual individual);

    void mutate();
}
