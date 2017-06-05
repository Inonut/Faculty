package tema6;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("m = ");
        int m = scan.nextInt();
        System.out.print("n = ");
        int n = scan.nextInt();

        Random rand = new Random();

        for(int i = 0; i<m;i++){
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
}
