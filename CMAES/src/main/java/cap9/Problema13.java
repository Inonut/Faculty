package cap9;

import old.domain.Female;
import old.domain.Male;
import formula.Formula;

import java.io.IOException;

/**
 * Created by dragos on 6/4/17.
 */
public class Problema13 {

    public static void main(String[] args) throws IOException {
        Formula formula = new Formula();

        double resultExemplu = 250 * formula.aTempLeastFormula(20, 1, 10./100,
                new Male(55),
                new Female(53),
                new Male(28));

        double resultProblema = 10000 * formula.aTempLeastFormula( 30, 1, 8./100,
                new Male(60),
                new Female(54),
                new Male(35));
        System.out.println("Exemplu  " + resultExemplu);
        System.out.println("Problema   " + resultProblema);
    }
}
