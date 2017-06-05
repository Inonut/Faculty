package tema7;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema1 {

    public static int[] v;
    public static int n;

    public static void back(int k){
        if(n == k){
            for(int i=0;i<n;i++){
                System.out.print(v[i] + " ");
            }
            System.out.println();
        } else {
            for(int j=0;j<n;j++){
                v[k] = j+1;
                if(verif(k+1)){
                    back(k+1);
                }
            }
        }
    }

    private static boolean verif(int k) {
        Set<Integer> elems = new HashSet<>();

        for(int i=0; i < k; i++){

            if(v[i] == i+1){
                return false;
            }

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
