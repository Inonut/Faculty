package tema9.p0.model;

import tema9.ga.Gene;

/**
 * Created by dragos on 5/21/17.
 */
public class City implements Gene{

    int x;
    int y;

    // Constructs a randomly placed city
    public City(){
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
    }

    // Constructs a city at chosen x, y location
    public City(int x, int y){
        this.x = x;
        this.y = y;
    }

    // Gets city's x coordinate
    public int getX(){
        return this.x;
    }

    // Gets city's y coordinate
    public int getY(){
        return this.y;
    }

    @Override
    public String toString(){
        return getX()+"|"+getY();
    }

    @Override
    public double compareTo(Gene gene) {
        City city = (City) gene;
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );

        return distance;
    }
}
