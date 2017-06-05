package tema3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dragos on 12.03.2017.
 */
public class Problema1 {

    public static void main(String[] args){

        int n = 6;
        List<List<Integer>> subs = Arrays.asList(
                Arrays.asList(3, 2),
                Arrays.asList(1, 5, 6),
                Arrays.asList(4)
        );

        List<Integer> reuniune = subs.stream().flatMap(Collection::stream).collect(Collectors.toList());
        if(reuniune.stream().min(Comparator.comparingInt(a -> a)).orElse(0) >= 1 &&
                reuniune.stream().max(Comparator.comparingInt(a -> a)).orElse(0) <= n){
            System.out.println("DA");
        } else {
            System.out.println("NU");
        }

    }
}
