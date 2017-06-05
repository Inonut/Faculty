package cap5;

import java.text.ParseException;
import java.util.Map;

/**
 * Created by dragos on 5/28/17.
 */
public class Problema2 {

    public static void main(String[] args) throws ParseException {


        double deposit = 1000;
        double procent = 8./100;
        int years = 3;
        int month = 7;


        System.out.println("simple interest " + simpleInterest(deposit, procent, years, month));
        System.out.println("compound interest, the rational procedure S_t " + rationalProcedure_St(deposit, procent, years, month));
        System.out.println("compound interest, the rational procedure D " + rationalProcedure_D(deposit, procent, years, month));
        System.out.println("compound interest, the commercial procedure S_t " + commercialProcedure_St(deposit, procent, years, month));
        System.out.println("compound interest, the commercial procedure D " + commercialProcedure_D(deposit, procent, years, month));
        System.out.println("compounded monthly interest " + compounded(deposit, procent, years, month));

    }

    private static double rationalProcedure_D(double deposit, double procent, int years, int month) {
        return deposit * (Math.pow(1 + procent, years) * (1 + procent * month / 12) - 1);
    }

    private static double rationalProcedure_St(double deposit, double procent, int years, int month) {
        return deposit * Math.pow(1 + procent, years) * (1 + procent * month / 12);
    }

    private static double commercialProcedure_D(double deposit, double procent, int years, int month) {
        return deposit * (Math.pow(1 + procent, years + month / 12.) - 1);
    }

    private static double commercialProcedure_St(double deposit, double procent, int years, int month) {
        return deposit * Math.pow(1 + procent, years) * Math.pow(Math.pow(1 + procent, month), 1./12);
    }

    private static double compounded(double deposit, double procent, int years, int month) {
        return deposit * Math.pow(1 + procent / 12., 12 * years);
    }

    private static double simpleInterest(double deposit, double procent, int years, int month) {
        return deposit * procent * years + deposit * month/12 * procent + deposit;
    }
}
