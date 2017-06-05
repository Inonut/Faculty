package tema1;

import util.FunctionCoef;
import util.Point;

import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by dragos on 12.03.2017.
 */
public class Problema2 {

    public static void main(String[] args) throws ScriptException {

        List<Point> points = Arrays.asList(
                new Point(-2,2),
                new Point(3,0),
                new Point(0,2),
                new Point(5,0),
                new Point(10,2)
        );

        FunctionCoef functionCoef = new FunctionCoef(1,-1,0);

        Function<Point, Boolean> fct = (p) ->
                functionCoef.getA() * p.getX() + functionCoef.getB() * p.getY() + functionCoef.getC() < 0;

        System.out.println("C1: " + points.stream().filter(p -> fct.apply(p)).map(Point::toString).collect(Collectors.joining(", ")));
        System.out.println("C2: " + points.stream().filter(p -> !fct.apply(p)).map(Point::toString).collect(Collectors.joining(", ")));


    }
}
