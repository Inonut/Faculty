package tema7;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema2 {

    public static int[] v;
    public static int n;

    public static void back(int k){
        if(n == k){
            if(verif2(k)){
                for(int i=0;i<n;i++){
                    System.out.print(v[i] + " ");
                }
                System.out.println();
            }
        } else {
            for(int j=0;j<n;j++){
                v[k] = j+1;
                if(verif(k+1)){
                    back(k+1);
                }
            }
        }
    }


    private static boolean verif2(int k) {
        for (int i = 0; i < k; i++) {
            boolean okLeft = i - 1 >= 0;
            if (i - 1 >= 0 && Math.abs(v[i] - v[i - 1]) != 1) {
                okLeft = false;
            }
            boolean okRight = i + 1 < k;
            if (i + 1 < k && Math.abs(v[i] - v[i + 1]) != 1) {
                okRight = false;
            }

            if (!okLeft && !okRight) {
                return false;
            }
        }

        return true;
    }


    private static boolean verif(int k) {
        Set<Integer> elems = new HashSet<>();

        for(int i=0; i < k; i++){
            if(!elems.add(v[i])){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("n = ");
        n = scan.nextInt();

        v = new int[n];

        back(0);
    }
}
