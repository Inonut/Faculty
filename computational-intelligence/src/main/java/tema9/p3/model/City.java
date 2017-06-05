package tema9.p3.model;

import tema9.ga.Gene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragos on 5/21/17.
 */
public class City implements Gene{

    private int code;
    private List<Road> roads;

    public City(int code) {
        this.code = code;
        roads = new ArrayList<>();
    }

    @Override
    public double compareTo(Gene gene) {
//        return roads.stream().filter(road -> road.getCity().equals(gene)).findFirst().orElse(null).getCost();
        Road r = roads.stream().filter(road -> road.getCity().equals(gene)).findFirst().orElse(null);
        if(r == null){
            return 0;
        } else {
            return r.getCost();
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return code == city.code;
    }

    @Override
    public int hashCode() {
        return code;
    }

    @Override
    public String toString() {
        return "" + code;
    }
}
