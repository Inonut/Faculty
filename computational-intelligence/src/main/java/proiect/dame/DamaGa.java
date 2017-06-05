package proiect.dame;

import proiect.defaultImpl.DefaultIndividual;
import proiect.defaultImpl.DefaultPopulation;

/**
 * Created by dragos on 6/5/17.
 */
public class DamaGa extends DefaultPopulation {
    @Override
    protected DefaultPopulation newPopulation() {
        return new DamaGa();
    }

    @Override
    protected DefaultIndividual newIndividual() {
        return new Permutare();
    }
}
