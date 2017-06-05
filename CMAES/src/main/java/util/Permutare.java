package util;

import domain.Person;

import java.util.*;

/**
 * Created by dragos on 6/3/17.
 */
public class Permutare {

    private int[] v;
    private int n;
    private List<Person[]> permutari;
    private Person[] data;

    public List<Person[]> build(int n, Person[] data){
        this.v = new int[n];
        this.n = n;
        this.data = data;
        this.permutari = new ArrayList<>();

        back(0);

        return this.permutari;
    }

    private void back(int k){
        if(n == k){
            Person[] result = new Person[n];
            for(int i=0; i<n; i++){
                result[i] = data[v[i]];
            }
            permutari.add(result);
        } else {
            for(int j = 0; j< data.length; j++){
                v[k] = j;
                if(verif(k+1)){
                    back(k+1);
                }
            }
        }
    }

    private boolean verif(int k) {
        Set<Integer> elems = new HashSet<>();

        for(int i=0; i < k; i++){

            if(i-1>=0 && v[i] < v[i-1]){
                return false;
            }

            if(!elems.add(v[i])){
                return false;
            }
        }

        return true;
    }
}
