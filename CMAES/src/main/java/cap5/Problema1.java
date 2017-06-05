package cap5;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dragos on 5/28/17.
 */
public class Problema1 {

    public static void main(String[] args) throws ParseException {


        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(2014, 2, 20);
        end.set(2014, 11, 10);
        Date startDate = start.getTime();
        Date endDate = end.getTime();
        double deposit = 1000;
        double procent = 8./100;

        System.out.println("exact interest " + exactInterest(startDate, endDate, deposit, procent));
        System.out.println("banker’s rule " + bankerRule(startDate, endDate, deposit, procent));
        System.out.println("ordinary interest " + ordinaryInterest(startDate, endDate, deposit, procent));

    }

    private static double ordinaryInterest(Date startDate, Date endDate, double deposit, double procent) {
        double h = 300 * (endDate.getYear() - startDate.getYear()) + 30 * (endDate.getMonth() - startDate.getMonth()) + endDate.getDay() - startDate.getDay();

        return deposit * h * procent / 360.;
    }

    private static double bankerRule(Date startDate, Date endDate, double deposit, double procent) {
        return (deposit * processDayBetween(startDate, endDate) * procent) / 360.;
    }

    private static long processDayBetween(Date startDate, Date endDate) {

        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long diffTime = endTime - startTime;

        return diffTime / (1000 * 60 * 60 * 24);
    }

    private static double exactInterest(Date startDate, Date endDate, double deposit, double procent) {
        return (deposit * processDayBetween(startDate, endDate) * procent) / 365.;
    }
}
