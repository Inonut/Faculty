package tema4;

import java.util.Scanner;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema3 {

    static int n;

    public static void citMultimeFuzzy(Double A[], int l){
        int i;
        Scanner scan = new Scanner(System.in);
        System.out.print("\n Citim numerele si gradul de apartenenta la F: \n");
        for(i=0;i<l;i++){
            A[scan.nextInt()] = scan.nextDouble();
        }
    }


    public static void maximDouaNr(Double a, Double b, int i){
        Double max = null;
        if(a > b){
            max =  a;
        }else if(b > a){
            max =  b;
        }
        if(max != null && max != 0){
            System.out.print("("+i+", "+max+")\n");
        }
    }

    public static void reuniune(Double []A, Double[]B){
        Double max = 0D;
        for(int i=1;i<=n;i++){
            maximDouaNr(A[i], B[i], i);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("\n Citim nr de numere pe care le contine multimea F: n =");
        n = scan.nextInt();

        Double[] A = new Double[n+1];
        Double[] B = new Double[n+1];
        for(int i=1;i<=n;i++){
            A[i] = 0D;
            B[i] = 0D;
        }
        System.out.print("\n Multimea A \n");
        System.out.print("Citire k = (nr de numere din A care au gradul de apartenenta la F nenul)");
        int k = scan.nextInt();
        citMultimeFuzzy(A, k);

        System.out.print("\n Multimea B \n");
        System.out.print("Citire h = (nr de numere din B care au gradul de apartenenta la F nenul)");
        int h = scan.nextInt();
        citMultimeFuzzy(B,h);
        reuniune(A,B);
    }
}
