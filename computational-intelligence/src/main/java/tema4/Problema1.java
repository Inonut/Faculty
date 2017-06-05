package tema4;

import java.util.Scanner;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema1 {

    public static void main(String []args){
        Scanner scan = new Scanner(System.in);
        System.out.print("n = ");
        int n = scan.nextInt();
        System.out.print("k = ");
        int k = scan.nextInt();

        double cardinalitateScalara = 0;
        System.out.print("\n Citim numerele si gradul de apartenenta la F: \n");
        for (int i = 0; i < k; i++){
            scan.nextInt();
            cardinalitateScalara+=scan.nextDouble();
        }

        System.out.print("\n Cardinalitate scalara = "+cardinalitateScalara);
        System.out.print("\n Cardinalitate relativa = "+cardinalitateScalara/n);

    }
}
