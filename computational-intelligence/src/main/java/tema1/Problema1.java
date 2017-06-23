package tema1;

import old.util.Point;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by dragos on 12.03.2017.
 */
public class Problema1 {

    public static void main(String[] args){

        List<Point> points = Arrays.asList(
                new Point(-3),
                new Point(5),
                new Point(2),
                new Point(-1),
                new Point(3)
        );

        Function<Point, Boolean> fct = (p) -> p.getX() > 0;

        System.out.println("C1: " + points.stream().filter(p -> fct.apply(p)).map(Point::toString).collect(Collectors.joining(", ")));
        System.out.println("C2: " + points.stream().filter(p -> !fct.apply(p)).map(Point::toString).collect(Collectors.joining(", ")));

    }
}
