package tema6;

import java.util.*;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("n = ");
        int n = scan.nextInt();

        Random rand = new Random();

        Set<Integer> cromozomi = new LinkedHashSet<>();
        while(cromozomi.size() != n) {
            int nr = rand.nextInt(n) + 1;
            cromozomi.add(nr);
        }

        System.out.print("\n Cromozomul generat: \n");
        for(Integer c: cromozomi){
            System.out.print(c + " ");
        }
    }
}
