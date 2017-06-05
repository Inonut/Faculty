package tema5;

import java.util.Scanner;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema2 {

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

    public static double minim(double a, double b){
        double min = 0;
        if(a < b){
            min  = a;
        }else if(b < a){
            min  = b;
        }
        return min;
    }

    public static void intersectie(){
        //minimul pe coloane
        int i,j;
        for(j=1;j<=n;j++){
            double minim = 0;
            boolean control = true;
            for(i=1;i<=n;i++){
                if(A[i][0] != 0){
                    if(A[i][j] != 0){
                        minim = A[i][j];
                        break;
                    }else{
                        control = false;
                        break;
                    }
                }
            }
            i++;
            for(;i<=n;i++){
                if(A[i][0] != 0){
                    if(A[i][j] != 0){
                        minim = minim(minim, A[i][j]);
                        break;
                    }else{
                        control = false;
                        break;
                    }
                }
            }

            if(minim != 0 && control == true){
                System.out.print(j + "  "+minim+"\n");
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

        System.out.print("\n Intersectia celor "+ m + " submultimi:\n");
        intersectie();
    }
}
