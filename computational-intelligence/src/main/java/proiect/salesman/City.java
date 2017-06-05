package proiect.salesman;

import proiect.ad.Gene;

import java.util.Random;

/**
 * Created by dragos on 6/4/17.
 */
public class City extends Gene {

    private int x;
    private int y;

    public City() {
    }

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double compareTo(proiect.ad.Gene gene) {
        City city = (City) gene;

        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        return Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
    }

    @Override
    public proiect.ad.Gene randomize() {
        Random random = new Random();
        return new City(random.nextInt(200), random.nextInt(200));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return getX()+"|"+getY();
    }
}
