package cap9;

import old.domain.Female;
import old.domain.Male;
import formula.Formula;

import java.io.IOException;

/**
 * Created by dragos on 6/4/17.
 */
public class Problema6 {

    public static void main(String[] args) throws IOException {
        Formula formula = new Formula();

        double resultExemplu = 250 * formula.aExactFormula( 2,10./100,
                new Male(55),
                new Female(53),
                new Male(28));

        double resultProblema = 10000 * formula.aExactFormula( 2,8./100,
                new Male(46),
                new Female(44),
                new Male(22));
        System.out.println("Exemplu  " + resultExemplu);
        System.out.println("Problema   " + resultProblema);
    }
}
