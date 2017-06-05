package tema6;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by dragos on 5/20/17.
 */
public class Problema3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(new File("computational-intelligence/src/main/java/tema6/cromozomi.in")));

        int n = scan.nextInt();
        int m = scan.nextInt();

        List<List<Integer>> map = new ArrayList<>();
        for(int i=0;i<m;i++){
            List<Integer> array = new ArrayList<>();
            for(int j=0;j<n;j++){
                array.add(scan.nextInt());
            }
            map.add(array);
        }

        scan.close();

        map.sort((l1, l2) ->{
            Iterator<Integer> itr1 = l1.iterator();
            Iterator<Integer> itr2 = l2.iterator();

            while(itr1.hasNext()){
                int res = itr1.next() - itr2.next();
                if(res != 0){
                    return res;
                }
            }

            return 0;
        });

        PrintWriter pw = new PrintWriter(new FileOutputStream(new File("computational-intelligence/src/main/java/tema6/cromozomi.out")));
        for(List<Integer> array: map){
            for(Integer el: array){
                pw.print(el + " ");
            }
            pw.println();
        }

        pw.close();
    }
}
