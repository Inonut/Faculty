package cap9;

import domain.Female;
import domain.Male;
import formula.Formula;

import java.io.IOException;

/**
 * Created by dragos on 6/1/17.
 */
public class Problema1 {

    public static void main(String[] args) throws IOException {

        Formula formula = new Formula();

        System.out.println("a) " + formula.pAll(15,
                new Male(55),
                new Female(53),
                new Female(30),
                new Male(28)));

        System.out.println("b) " + formula.pExactly(25, 2,
                new Male(55),
                new Female(53),
                new Female(30),
                new Male(28)));

        System.out.println("b) " + formula.pLeast(20, 3,
                new Male(55),
                new Female(53),
                new Female(30),
                new Male(28)));
    }
}
