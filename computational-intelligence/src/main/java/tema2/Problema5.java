package tema2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by dragos on 12.03.2017.
 */
public class Problema5 {

    public static void main(String[] args){

        Random r = new Random();

        List<Integer> v1 = IntStream.range(0, Math.abs(r.nextInt()) % 1000).map(v -> r.nextInt()).boxed().collect(Collectors.toList());
        List<Integer> v2 = IntStream.range(0, Math.abs(r.nextInt()) % 1000).map(v -> r.nextInt()).boxed().collect(Collectors.toList());

        System.out.println("A (" + v1.size() + ")");
        v1.forEach(v -> System.out.print(v + " "));

        System.out.println();
        System.out.println("-----------------------------------------");

        System.out.println("B (" + v2.size() + ")");
        v2.forEach(v -> System.out.print(v + " "));

        Set<Integer> reuniune = Stream.of(v1, v2)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());


        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------------");


        if(reuniune.size() == v2.size()){
            System.out.println("A este inclus in B");
        } else {
            System.out.println("A nu este inclus in B");
        }
    }

}
