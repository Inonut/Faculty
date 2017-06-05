package tema3;

import javafx.util.Pair;

import java.util.stream.IntStream;

/**
 * Created by dragos on 12.03.2017.
 */
public class Problema2 {

    public static double singular(int x, double a){
        return a;
    }

    private static double triangular(int x, double a, double b, double c) {
        return Math.max(0, Math.min(1, Math.min((x - a)/(b - a), (c-x)/(c-b))));
    }

    private static double trapezoidala(Integer x, double a, double b, double c, double d) {
        return Math.max(0, Math.min(1, Math.min((x - a)/(b - a), (d-x)/(d-c))));
    }

    private static double fctZ(Integer x, double a, double b, double c, double d) {
        return 1 - trapezoidala(x,a,b,c,d);
    }

    public static void main(String[] args){

        double a = 1;
        double b = 5;
        double c = 3;
        double d = 4;

        System.out.println("Singulara");
        IntStream.range(1, 10)
                .boxed()
                .map(x -> new Pair(x, singular(x, a)))
                .forEach(p -> System.out.println(p.getKey() + " --> " + p.getValue() + " "));

        System.out.println();
        System.out.println("---------------------------------");

        System.out.println("Triangulara");
        IntStream.range(1, 10)
                .boxed()
                .map(x -> new Pair(x, triangular(x, a, b, c)))
                .forEach(p -> System.out.println(p.getKey() + " --> " + p.getValue() + " "));

        System.out.println();
        System.out.println("---------------------------------");

        System.out.println("Trapezoidala");
        IntStream.range(1, 10)
                .boxed()
                .map(x -> new Pair(x, trapezoidala(x, a, b, c, d)))
                .forEach(p -> System.out.println(p.getKey() + " --> " + p.getValue() + " "));

        System.out.println();
        System.out.println("---------------------------------");

        System.out.println("Functia Z");
        IntStream.range(1, 10)
                .boxed()
                .map(x -> new Pair(x, fctZ(x, a, b, c, d)))
                .forEach(p -> System.out.println(p.getKey() + " --> " + p.getValue() + " "));

        System.out.println();
        System.out.println("---------------------------------");
    }


}
