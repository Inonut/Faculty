package tema4;

import java.util.Scanner;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema2 {

    public static void main(String []args){
        Scanner scan = new Scanner(System.in);
        System.out.print("n = ");
        int n = scan.nextInt();
        System.out.print("k = ");
        int k = scan.nextInt();

        double[] comp = new double[n + 1];

        System.out.print("\n Citim numerele si gradul de apartenenta la F: \n");
        for (int i = 0; i < k; i++){
            comp[scan.nextInt()] = scan.nextDouble();
        }

        System.out.print("\n Complementara \n");
        for (int i = 1; i <= n; i++){
            if(1-comp[i] != 0){
                System.out.println(i+ " " + (1-comp[i]));
            }
        }

    }
}
