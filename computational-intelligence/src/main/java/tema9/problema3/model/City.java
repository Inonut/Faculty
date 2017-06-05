package tema9.problema3.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragos on 5/21/17.
 */
public class City {
    private int code;
    private List<Road> roads;

    public City(int code) {
        this(code, new ArrayList<>());
    }

    public City(int code, List<Road> roads) {
        this.code = code;
        this.roads = roads;
    }

    public int costTo(City city) {
        return roads.stream().filter(road -> road.getCity().equals(city)).map(Road::getCost).findFirst().orElse(0);
    }

    public void addRoad(Road road) {
        roads.add(road);
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
}
