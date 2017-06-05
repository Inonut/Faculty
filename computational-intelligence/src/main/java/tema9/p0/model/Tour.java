package tema9.p0.model;

import tema9.ga.Gene;
import tema9.ga.Individual;
import tema9.ga.impl.AbstractIndividual;
import tema9.p0.TourManager;
import tema9.util.Constants;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dragos on 5/21/17.
 */

public class Tour extends AbstractIndividual {

    @Override
    public Individual crossover(Individual individual) {
        Tour parent1 = this;
        Tour parent2 = (Tour) individual;

        Tour child = new Tour();

        int startPos = Constants.rand.nextInt(parent1.geneSize());
        int endPos = Constants.rand.nextInt(parent1.geneSize());

        if (startPos < endPos) {
            for (int i = startPos; i < endPos; i++) {
                child.setGene(i, parent1.getGene(i));
            }
        } else if (startPos > endPos) {
            for (int i = 0; i < endPos; i++) {
                child.setGene(i, parent1.getGene(i));
            }

            for (int i = startPos; i < child.geneSize(); i++) {
                child.setGene(i, parent1.getGene(i));
            }
        }

        List<Gene> availableCites = parent2.getGeneList().stream().filter(city -> !child.containsCity(city)).collect(Collectors.toList());
        Iterator<Gene> cityIterator = availableCites.iterator();
        for (int j = 0; j < child.geneSize(); j++) {
            if (child.getGene(j) == null) {
                child.setGene(j, cityIterator.next());
            }
        }
        return child;
    }


    @Override
    public void mutate() {
        for(int tourPos1=0; tourPos1 < this.geneSize(); tourPos1++){
            if(Math.random() < Constants.mutationRate){
                int tourPos2 = (int) (this.geneSize() * Math.random());

                Gene city1 = this.getGene(tourPos1);
                Gene city2 = this.getGene(tourPos2);

                this.setGene(tourPos2, city1);
                this.setGene(tourPos1, city2);
            }
        }
    }

    @Override
    protected List getAllGene() {
        return TourManager.getCityList();
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < this.geneSize(); i++) {
            geneString += "" + getGene(i)+" ";
        }
        return geneString;
    }
}
