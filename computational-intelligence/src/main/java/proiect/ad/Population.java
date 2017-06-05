package proiect.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragos on 6/4/17.
 */
public abstract class Population {
    protected List<Individual> individualList = new ArrayList<>();

    public abstract Population evolve();

    public abstract Individual getFittest();

    public abstract Population randomize(int individualSize);

    public List<Individual> getIndividualList() {
        return individualList;
    }

    public void setIndividualList(List<Individual> individualList) {
        this.individualList = individualList;
    }

    public Individual getIndividual(int i){
        return individualList.get(i);
    }

    public void setIndividual(int i, Individual individual){
        this.getIndividualList().set(i, individual);
    }

    public void addIndividual(Individual individual){
        this.getIndividualList().add(individual);
    }

    public int individualSize(){
        return this.individualList.size();
    }
}
