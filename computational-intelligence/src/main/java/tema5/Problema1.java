package tema5;

import java.util.Scanner;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema1 {

    static int n,m;
    static double [][]A;

    public static void citMultimeFuzzy(int i){
        Scanner scan = new Scanner(System.in);
        System.out.print("Citire k = (nr de numere din multime care au gradul de apartenenta la F nenul)");
        int k = scan.nextInt();
        System.out.print("\n Citim numerele si gradul de apartenenta la F: \n");
        int j;
        A[k][0] = new Double(k);


        for(j=1;j<=k;j++){
            A[k][scan.nextInt()] = scan.nextDouble();
        }
    }

    public static double maxim(double a, double b){
        double max = 0;
        if(a > b){
            max  = a;
        }else if(b > a){
            max  = b;
        }
        return max;
    }

    public static void reuniune(){
        //maximul pe coloane
        int i,j;
        for(j=1;j<=n;j++){
            double maxim = 0;
            for(i=1;i<=n;i++){
                maxim = maxim(maxim, A[i][j]);
            }
            if(maxim != 0){
                System.out.print(j + "  "+maxim+"\n");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("\n Citim nr de numere : n =");
        n = scan.nextInt();
        System.out.print("\n Citim nr de submultimi pe care le contine multimea F: m =");
        m = scan.nextInt();

        System.out.print("\n Citim submultimile pe rand");
        A = new double[n+1][n+1];

        for(int i=1;i<=m;i++){
            System.out.print("\n Submultimea "+i+": ");
            citMultimeFuzzy(i);
        }

        System.out.print("\n Reuniune celor "+ m + " submultimi:\n");
        reuniune();
    }
}
