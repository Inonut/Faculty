package proiect.salesman;

import proiect.defaultImpl.DefaultIndividual;
import proiect.defaultImpl.DefaultPopulation;

import java.util.List;

/**
 * Created by dragos on 6/4/17.
 */
public class Salesman extends DefaultPopulation {

    @Override
    protected DefaultPopulation newPopulation() {
        return new Salesman();
    }

    @Override
    protected DefaultIndividual newIndividual() {
        return new Tour();
    }
}
