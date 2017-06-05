package proiect.ad;

import proiect.salesman.Tour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragos on 6/4/17.
 */
public abstract class Individual {

    protected List<Gene> geneList = new ArrayList<>();

    public abstract Individual cossover(Individual individual);

    public abstract Individual mutate();

    public abstract double getFitness();

    public abstract Individual randomize(int geneNumber);

    public List<Gene> getGeneList() {
        return geneList;
    }

    public void setGeneList(List<Gene> geneList) {
        this.geneList = geneList;
    }

    public int geneSize() {
        return getGeneList().size();
    }

    public Gene getGene(int i){
        return getGeneList().get(i);
    }

    public void setGene(int i, Gene gene){
        getGeneList().set(i, gene);
    }

    public boolean containsGene(Gene gene) {
        return getGeneList().contains(gene);
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < geneSize(); i++) {
            geneString += "" + getGene(i)+" ";
        }
        return geneString;
    }
}
