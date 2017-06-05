package cap9;

import domain.Female;
import domain.Male;
import formula.Formula;

import java.io.IOException;

/**
 * Created by dragos on 6/4/17.
 */
public class Problema15 {

    public static void main(String[] args) throws IOException {
        Formula formula = new Formula();

        double resultProblema = 50000 * formula.akFormula(2, 8./100,
                new Male(28),
                new Female(25));
        System.out.println("Problema   " + resultProblema);
    }
}
