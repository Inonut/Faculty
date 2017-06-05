package proiect.defaultImpl;

import proiect.ad.Gene;
import proiect.ad.Individual;
import proiect.util.Constants;
import proiect.util.IndividualManager;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by dragos on 6/4/17.
 */
public abstract class DefaultIndividual extends Individual {

    public DefaultIndividual() {
    }

    public DefaultIndividual(List<Gene> geneList) {
        super();
        this.setGeneList(geneList);
    }

    protected abstract DefaultIndividual newIndividual();

    protected abstract Gene newGene();

    public DefaultIndividual randomize() {
        List<Gene> geneList = new ArrayList<>(IndividualManager.numberOfIndividual());
        geneList.addAll(IndividualManager.getIndividualList());
        Collections.shuffle(geneList);
        DefaultIndividual individual = newIndividual();
        individual.setGeneList(geneList);
        return individual;
    }

    @Override
    public Individual randomize(int geneNumber) {
        DefaultIndividual individual = newIndividual();
        individual.setGeneList(IntStream.range(0, geneNumber).boxed().map(i -> newGene().randomize()).collect(Collectors.toList()));
        return individual;
    }

    @Override
    public Individual cossover(Individual individual) {
        Random random = new Random();
        DefaultIndividual parent1 = this;
        DefaultIndividual parent2 = (DefaultIndividual) individual;

        DefaultIndividual child = newIndividual();
        child.setGeneList(IndividualManager.nullIndidual());

        int startPos = random.nextInt(parent1.geneSize());
        int endPos = random.nextInt(parent1.geneSize());

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

        List<Gene> availableCites = parent2.getGeneList().stream().filter(city -> !child.containsGene(city)).collect(Collectors.toList());
        Iterator<Gene> cityIterator = availableCites.iterator();
        for (int j = 0; j < child.geneSize(); j++) {
            if (child.getGene(j) == null) {
                child.setGene(j, cityIterator.next());
            }
        }
        return child;
    }

    @Override
    public Individual mutate() {
        DefaultIndividual tour = newIndividual();
        tour.setGeneList(this.geneList);

        for (int tourPos1 = 0; tourPos1 < tour.geneSize(); tourPos1++) {
            if (Math.random() < Constants.MUTATE_RATE) {
                int tourPos2 = (int) (tour.geneSize() * Math.random());

                Gene gene1 = tour.getGene(tourPos1);
                Gene gene2 = tour.getGene(tourPos2);

                tour.setGene(tourPos1, gene2);
                tour.setGene(tourPos2, gene1);
            }
        }

        return tour;
    }

    @Override
    public double getFitness() {
        double result = 0;

        int size = this.getGeneList().size();
        for (int i = 0; i < size - 1; i++) {
            result += this.getGeneList().get(i).compareTo(this.getGeneList().get(i + 1));
        }
        result += this.getGeneList().get(size - 1).compareTo(this.getGeneList().get(0));

        return result;
    }
}
