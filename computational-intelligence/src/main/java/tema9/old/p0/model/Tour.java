package tema9.old.p0.model;

/**
 * Created by dragos on 5/21/17.
 */
import tema9.old.ga.Individual;
import tema9.old.p0.TourManager;
import tema9.util.Constants;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tour implements Individual{

    private List<City> cityList;

    public Tour(){
        this.cityList = new ArrayList<City>();
        IntStream.range(0, TourManager.numberOfCities()).forEach(i->this.addCity(null));
    }

    @Override
    public void generateIndividual() {
        Collections.copy(this.cityList, TourManager.getCityList());
        Collections.shuffle(cityList);
    }

    @Override
    public double getFitness() {
        return 1/(double)getDistance();
    }

    @Override
    public Individual crossover(Individual individual) {

        Tour parent1 = this;
        Tour parent2 = (Tour) individual;

        Tour child = new Tour();

        int startPos = Constants.rand.nextInt(parent1.tourSize());
        int endPos = Constants.rand.nextInt(parent1.tourSize());

        if(startPos < endPos){
            for (int i = startPos; i < endPos; i++){
                child.setCity(i, parent1.getCity(i));
            }
        } else if(startPos > endPos) {
            for (int i = 0; i < endPos; i++){
                child.setCity(i, parent1.getCity(i));
            }

            for (int i = startPos; i < child.tourSize(); i++){
                child.setCity(i, parent1.getCity(i));
            }
        }

        List<City> availableCites = parent2.getCityList().stream().filter(city -> !child.containsCity(city)).collect(Collectors.toList());
        Iterator<City> cityIterator = availableCites.iterator();
        for (int j = 0; j < child.tourSize(); j++) {
            if (child.getCity(j) == null) {
                child.setCity(j, cityIterator.next());
            }
        }
        return child;
    }

    @Override
    public void mutate() {
        for(int tourPos1=0; tourPos1 < this.tourSize(); tourPos1++){
            if(Math.random() < Constants.mutationRate){
                int tourPos2 = (int) (this.tourSize() * Math.random());

                City city1 = this.getCity(tourPos1);
                City city2 = this.getCity(tourPos2);

                this.setCity(tourPos2, city1);
                this.setCity(tourPos1, city2);
            }
        }
    }

    public int getDistance(){
        int tourDistance = 0;
        for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
            City fromCity = getCity(cityIndex);
            City destinationCity;
            if(cityIndex+1 < tourSize()){
                destinationCity = getCity(cityIndex+1);
            }
            else{
                destinationCity = getCity(0);
            }
            tourDistance += fromCity.distanceTo(destinationCity);
        }
        return tourDistance;
    }

    public int tourSize() {
        return cityList.size();
    }

    public boolean containsCity(City city){
        return cityList.contains(city);
    }

    private void addCity(City city) {
        cityList.add(city);
    }

    public City getCity(int tourPosition) {
        return cityList.get(tourPosition);
    }

    public void setCity(int tourPosition, City city) {
        cityList.set(tourPosition, city);
    }

    public List<City> getCityList() {
        return cityList;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < tourSize(); i++) {
            geneString += "(" + getCity(i)+")\n";
        }
        return geneString;
    }
}
