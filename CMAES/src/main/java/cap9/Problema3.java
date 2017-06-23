package cap9;

import old.domain.Female;
import old.domain.Male;
import formula.Formula;

import java.io.IOException;

/**
 * Created by dragos on 6/4/17.
 */
public class Problema3 {
    public static void main(String[] args) throws IOException {
        Formula formula = new Formula();

        /*double result = 25000 * formula.eExactFormula(30, 1, 10./100,
                new Male(55),
                new Female(53),
                new Male(28));*/

        double result = 20000 * formula.eExactFormula(30, 1, 8./100,
                new Male(32),
                new Female(30));
        System.out.println(result);
    }
}
