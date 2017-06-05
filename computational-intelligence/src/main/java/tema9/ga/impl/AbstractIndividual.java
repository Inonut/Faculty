package tema9.ga.impl;

import tema9.ga.Gene;
import tema9.ga.Individual;
import tema9.p0.model.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by dragos on 5/21/17.
 */
public abstract class AbstractIndividual implements Individual {

    private List<Gene> geneList;

    public AbstractIndividual(){
        this.geneList = new ArrayList<>();
        IntStream.range(0, this.getAllGene().size()).forEach(i->this.addGene(null));
    }

    protected abstract List<Gene> getAllGene();

    @Override
    public void generate() {
        Collections.copy(this.geneList, this.getAllGene());
        Collections.shuffle(geneList);
    }

    @Override
    public double getFitness() {
        return 1/(double) processFitness();
    }

    public int processFitness(){
        int tourDistance = 0;
        for (int cityIndex = 0; cityIndex < geneSize(); cityIndex++) {
            Gene fromCity = getGene(cityIndex);
            Gene destinationCity;
            if(cityIndex+1 < geneSize()){
                destinationCity = getGene(cityIndex+1);
            }
            else{
                destinationCity = getGene(0);
            }
            tourDistance += fromCity.compareTo(destinationCity);
        }
        return tourDistance;
    }

    public Gene getGene(int cityIndex) {
        return this.geneList.get(cityIndex);
    }


    public void setGene(int index, Gene gene) {
        this.geneList.set(index, gene);
    }

    public int geneSize() {
        return this.geneList.size();
    }

    public void addGene(Gene gene) {
        this.geneList.add(gene);
    }


    public List<Gene> getGeneList() {
        return geneList;
    }


    public boolean containsCity(Gene city) {
        return this.geneList.contains(city);
    }
}