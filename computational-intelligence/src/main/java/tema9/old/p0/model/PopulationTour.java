package tema9.old.p0.model;

import tema9.old.ga.Individual;
import tema9.old.ga.Population;

/**
 * Created by dragos on 5/21/17.
 */
public class PopulationTour extends Population {

    public PopulationTour(int populationSize) {
        super(populationSize);
    }

    @Override
    public Individual getNewIndivudual() {
        return new Tour();
    }

}
