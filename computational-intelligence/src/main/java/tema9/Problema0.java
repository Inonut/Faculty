package tema9;

/**
 * Created by dragos on 5/20/17.
 */

import java.io.*;
import java.util.*;

public class Problema0 {
    public static int m, n, A, B, NrPop = 1400, NrMutatii = 50;
    public static int[][] a = new int[101][101];
    public static int[][] pop = new int[1700][30];//retine indivizii populatiei

    //si costul traseului (coloana n+1) pe cate o linie
    public static int f(int i) {
//functia performanta - calculeaza costul total al deplasarii
//pentru un individ din populatie (adica a unui traseu)
        int s, j;
        s = 0;
        for (j = 2; j <= n; j++)
            s += a[pop[i][j - 1]][pop[i][j]];
        return s;
    }

    public static void ordonare() {
//clasificarea indivizilor dupa valorile crescatoare ale
//functiei de performanta (adica, cost traseu)
        int i, j, k, aux;
        for (i = 1; i < NrPop; i++)
            for (j = i + 1; j <= NrPop; j++)
                if (pop[i][n + 1] > pop[j][n + 1])
                    for (k = 1; k <= n + 1; k++) {
                        aux = pop[i][k];
                        pop[i][k] = pop[j][k];
                        pop[j][k] = aux;
                    }
    }

    public static void generare() {
//generarea populatiei initiale in mod aleator
        int i, j, k, x, sw;
        for (i = 1; i <= NrPop - 200; i++) {
            pop[i][1] = A;
            pop[i][n] = B;
            for (j = 2; j <= n - 1; j++) {
                do {
                    x = (int) Math.ceil(Math.random() * n);
                    sw = 1;
                    for (k = 1; k < j; k++)
                        if (x == pop[i][k])
                            sw = 0;
                    if (x == B)
                        sw = 0;
                } while (sw == 0);
                pop[i][j] = x;
            }
            pop[i][n + 1] = f(i);
        }
    }

    public static void mutatie() {
//mutatiile genetice au fost modelate prin alegerea,
//in mod aleator, a unui traseu si inversarea poz. unor
//orase, selectate tot aleator
        int i, N, M, P, aux, sw;
        for (i = 1; i <= NrMutatii; i++) {
            N = 1 + (int) Math.round(Math.random() * (NrPop - 201));
//generam nodul M
            do {
                M = 1 + (int) Math.round(Math.random() * (n - 1));
                sw = 1;
                if (M == A || M == B)
                    sw = 0;
            } while (sw == 0);
//generam nodul P
            do {
                P = 1 + (int) Math.round(Math.random() * (n - 1));
                sw = 1;
                if (P == A || P == B)
                    sw = 0;
            } while (sw == 0);
            aux = pop[N][M];
            pop[N][M] = pop[N][P];
            pop[N][P] = aux;
//se poate repeta cu inca o perechete de noduri N,P
            pop[N][n + 1] = f(N);
        }
    }

    public static void generatie() {
//generatie noua - pentru doua trasee, care se intersecteaza
//vom crea doua noi trasee, asfel:
//-primul traseu continua, de la intersectie, pe cel de-al doilea traseu
//-al doilea traseu, continua de la intersesctie, la primul traseu
//cele doua noi trasee sunt valide daca trec prin toate orasele
        int i, j, k, M, N, sw;
        for (i = NrPop - 200 + 1; i <= NrPop + 100; i++) {
            do {
                sw = 1;
                M = (int) Math.round(Math.random() * 20);
                N = (int) Math.round(Math.random() * 20);
                k = 1;
                do {
                    k++;
                } while (pop[M][k] != pop[N][k]);//k= nod de intersesctie individ M cu N
//prima noua solutie se adauga la populatie
                for (j = 1; j <= k; j++)
                    pop[i][j] = pop[m][j];
                for (j = k + 1; j <= n; j++)
                    pop[i][j] = pop[N][j];
                for (j = 2; j <= n - 1; j++)
                    for (k = 2; k <= n - 1; k++)
                        if (pop[i][j] == pop[i][k] && j != k)
                            sw = 0;
                for (j = 1; j <= k; j++)
                    pop[i + 100][j] = pop[N][j];
                for (j = k + 1; j <= n; j++)
                    pop[i + 100][j] = pop[M][j];
                for (j = 2; j <= n - 1; j++)
                    for (k = 2; k <= n - 1; k++)
                        if (pop[i + 100][j] == pop[i + 100][k] && j != k)
                            sw = 0;
            } while (sw == 0);
            pop[i][n + 1] = f(i);
            pop[i + 100][n + 1] = f(i + 100);
        }
    }

    public static void main(String[] args) throws IOException {
        FileInputStream f = new FileInputStream("computational-intelligence/src/main/java/tema9/genetic.in");
        InputStreamReader fchar = new InputStreamReader(f);
        BufferedReader buf = new BufferedReader(fchar);
        String linie = buf.readLine();
        StringTokenizer st = new StringTokenizer(linie);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        int i, j, k, cost;
//initializare matrice costuri
        for (i = 1; i <= n; i++)
            for (j = 1; j <= n; j++)
                if (i == j)
                    a[i][j] = 0;
                else
                    a[i][j] = 10000;
//citire costuri si construire matrice costuri
        for (k = 0; k < m; k++) {
            linie = buf.readLine();
            st = new StringTokenizer(linie);
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            a[i][j] = cost;
            a[j][i] = cost;
        }
//algoritm genetic pentru problema Comis-Voiajor
        generare();
        int NrGeneratii = 600;
        for (i = 1; i <= NrGeneratii; i++) {
            generatie();
            mutatie();
            ordonare();
        }
        System.out.println("Nrgeneratii=" + NrGeneratii);
        for (i = 1; i <= n; i++)
            System.out.print(pop[1][i] + " ");
        System.out.println("cost=" + pop[1][n + 1]);
    }
}