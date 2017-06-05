package tema2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dragos on 12.03.2017.
 */
public class Problema4 {

    public static void main(String[] args){

        List<Integer> v1 = Stream.of(6,2,6,3,4,7,1).collect(Collectors.toList());
        List<Integer> v2 = Stream.of(5,9,3,88,4,7).collect(Collectors.toList());

        v1.remove(0);
        v2.remove(0);

        Stream.of(v1.stream().filter(v -> !v2.contains(v)), v2.stream().filter(v -> !v1.contains(v)))
                .flatMap(v -> v)
                .collect(Collectors.toSet())
                .forEach(v -> System.out.print(v + " "));
    }

}
