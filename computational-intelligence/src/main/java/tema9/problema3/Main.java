package tema9.problema3;

import tema9.problema3.model.City;
import tema9.problema3.model.Road;
import tema9.util.Constants;

/**
 * Created by dragos on 5/21/17.
 */
public class Main {

    public static void main(String[] args){

        City city1 = new City(1);
        City city2 = new City(2);
        city1.addRoad(new Road(city2, 20));


        for(int i = 0;i<100;i++){

            System.out.println(Constants.rand.nextInt(200));
        }

    }
}
