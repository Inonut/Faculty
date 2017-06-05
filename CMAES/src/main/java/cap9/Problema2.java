package cap9;

import domain.Female;
import domain.Male;
import formula.Formula;

import java.io.IOException;

/**
 * Created by dragos on 6/3/17.
 */
public class Problema2 {

    public static void main(String[] args) throws IOException {
        Formula formula = new Formula();

        double result = 20000 * formula.eFormula(35, 8./100,
                new Male(32),
                new Male(30));
        System.out.println(result);
    }
}
