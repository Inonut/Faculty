package tema6;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(new File("computational-intelligence/src/main/java/tema6/cromozomi2.in")));

        int n = scan.nextInt();
        int m = scan.nextInt();

        int[] c1 = new int[n];
        int[] c2 = new int[n];

        for(int i=0;i<n;i++){
            c1[i] = scan.nextInt();
        }
        for(int i=0;i<n;i++){
            c2[i] = scan.nextInt();
        }

        scan.close();

        for(int i=0; i<n/2;i++){
            int aux = c1[i];
            c1[i] = c2[n/2 + n%2 + i];
            c2[n/2 + n%2 + i] = aux;
        }

        PrintWriter pw = new PrintWriter(new FileOutputStream(new File("computational-intelligence/src/main/java/tema6/cromozomi2.out")));
        for(int i=0;i<n;i++){
            pw.print(c1[i] + " ");
        }
        pw.println();
        for(int i=0;i<n;i++){
            pw.print(c2[i] + " ");
        }

        pw.close();
    }
}
