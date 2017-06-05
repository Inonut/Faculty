package proiect.dame;

import proiect.ad.Gene;
import proiect.defaultImpl.DefaultIndividual;

/**
 * Created by dragos on 6/4/17.
 */
public class Permutare extends DefaultIndividual {

    @Override
    protected DefaultIndividual newIndividual() {
        return new Permutare();
    }

    @Override
    protected Gene newGene() {
        return new Dama();
    }

    @Override
    public double getFitness() {
        double result = 0;

        for(int i = 0; i < this.geneSize() - 1; i++){
            for (int j = i +1 ;j < this.geneSize(); j++) {
                if( j - i == Math.abs(this.getGene(i).compareTo(this.getGene(j)))){
                    result++;
                }
            }
        }

        return result;
    }
}
