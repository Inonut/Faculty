package tema9.p3.model;

/**
 * Created by dragos on 5/21/17.
 */
public class Road {

    private City city;
    private int cost;

    public Road(City city, int cost) {
        this.city = city;
        this.cost = cost;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
