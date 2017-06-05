package proiect.salesman;

import proiect.ad.Gene;
import proiect.defaultImpl.DefaultIndividual;
import proiect.util.IndividualManager;

/**
 * Created by dragos on 6/4/17.
 */
public class Tour extends DefaultIndividual {

    @Override
    protected DefaultIndividual newIndividual() {
        return new Tour();
    }

    @Override
    protected Gene newGene() {
        return new City();
    }

    @Override
    public double getFitness() {
        double result = 0;

        if(IndividualManager.getFirst() != this.getGene(0)) {
            return 10000;
        }
        if(IndividualManager.getLast() != this.getGene(this.geneSize() - 1)){
            return 10000;
        }

        int size = this.getGeneList().size();
        for (int i = 0; i < size - 1; i++) {
            result += this.getGeneList().get(i).compareTo(this.getGeneList().get(i + 1));
        }
        result += this.getGeneList().get(size - 1).compareTo(this.getGeneList().get(0));

        return result;
    }
}
