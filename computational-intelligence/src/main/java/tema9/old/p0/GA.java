package tema9.old.p0;

import tema9.old.ga.GeneticAlgotithm;
import tema9.old.ga.Population;
import tema9.old.p0.model.PopulationTour;

/**
 * Created by dragos on 5/21/17.
 */
public class GA extends GeneticAlgotithm{

    @Override
    public Population getNewPopulation(int populationSize) {
        return new PopulationTour(populationSize);
    }

}