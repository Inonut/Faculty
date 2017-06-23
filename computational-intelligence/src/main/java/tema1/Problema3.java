package tema1;

import old.util.FunctionCoef;
import old.util.Point;

import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by dragos on 12.03.2017.
 */
public class Problema3 {

    public static void main(String[] args) throws ScriptException {

        List<Point> points = Arrays.asList(
                new Point(-1,-1,-1),
                new Point(-1,-1,1),
                new Point(-1,1,-1),
                new Point(-1,1,1),
                new Point(1,-1,1),
                new Point(1,-1,1),
                new Point(1,1,-1),
                new Point(1,1,1)
        );

        FunctionCoef functionCoef = new FunctionCoef(1,1,1,0);

        Function<Point, Boolean> fct = (p) ->
                functionCoef.getA() * p.getX() + functionCoef.getB() * p.getY() + functionCoef.getC() * p.getZ() + functionCoef.getD() < 0;

        System.out.println("C1: " + points.stream().filter(p -> fct.apply(p)).map(Point::toString).collect(Collectors.joining(", ")));
        System.out.println("C2: " + points.stream().filter(p -> !fct.apply(p)).map(Point::toString).collect(Collectors.joining(", ")));


    }
}
