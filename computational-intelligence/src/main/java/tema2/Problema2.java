package tema2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dragos on 12.03.2017.
 */
public class Problema2 {

    public static void main(String[] args){

        List<Integer> v1 = Arrays.asList(6,2,6,3,4,7,1);
        List<Integer> v2 = Arrays.asList(5,9,3,88,4,7);

        Stream.of(v1.stream(), v2.stream())
                .map(v -> v.skip(1))
                .flatMap(v -> v)
                .collect( Collectors.groupingBy( c -> c, Collectors.counting() ) )
                .entrySet()
                .stream()
                .filter( p -> p.getValue() > 1 )
                .map(Map.Entry::getKey)
                .forEach(v -> System.out.print(v + " "));
    }
}
