package tema7;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema3 {

    public static int[] v;
    public static int n;

    public static void back(int k, int calitate){
        if(n == k){
            if(verif2(calitate)){
                for(int i=0;i<n;i++){
                    System.out.print(v[i] + " ");
                }
                System.out.print("          - calitate " + calitate);
                System.out.println();
            }
        } else {
            for(int j=0;j<n;j++){
                v[k] = j+1;
                if(verif(k+1)){
                    back(k+1, calitate);
                }
            }
        }
    }

    private static boolean verif2(int calitate) {

        int sum = 0;
        for(int i=0; i < n; i++){
            if(v[i]==i+1){
                sum++;
            }
        }

        return sum == calitate;
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

        for(int i=0;i<=n;i++){
            back(0, i);
        }

    }
}
