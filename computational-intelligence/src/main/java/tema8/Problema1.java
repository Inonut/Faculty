package tema8;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema1 {

    public static int[] v;
    public static int n;
    public static int nr;
    public static boolean isDone;

    public static void back(int k){
        if(isDone){
            return;
        }
        if(n == k){
            if(verif2()){
                for(int i=0;i<n;i++){
                    System.out.print(v[i] + " ");
                }
                System.out.println();
                isDone = true;
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

    private static boolean verif2() {

        int sum = 0;
        for(int i=0; i < n; i++){

            if(v[i] == i+1){
                sum++;
            }
        }

        return sum == nr;
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

        System.out.print("k = ");
        nr = scan.nextInt();

        v = new int[n];

        back(0);
    }
}
