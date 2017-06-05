package proiect.util;

/**
 * Created by dragos on 5/21/17.
 */



import proiect.ad.Gene;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class IndividualManager {

    private static List<Gene> destinationCities = new ArrayList<>();
    private static Gene first;
    private static Gene last;

    public static void addIndividual(Gene gene) {
        destinationCities.add(gene);
    }

    public static Gene getIndivudual(int index){
        return destinationCities.get(index);
    }

    public static List<Gene> getIndividualList(){
        return destinationCities;
    }

    public static int numberOfIndividual(){
        return destinationCities.size();
    }

    public static List nullIndidual() {
        List<Gene> cities = new ArrayList<>();
        IntStream.range(0, IndividualManager.numberOfIndividual()).boxed().forEach(i-> cities.add(null));
        return cities;
    }

    public static void setFirst(Gene first) {
        IndividualManager.first = first;
    }

    public static void setLast(Gene last) {
        IndividualManager.last = last;
    }

    public static Gene getFirst() {
        return first;
    }

    public static Gene getLast() {
        return last;
    }
}
