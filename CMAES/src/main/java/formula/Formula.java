package formula;

import old.domain.Person;
import old.util.Permutare;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Created by dragos on 6/4/17.
 */
public class Formula {

    public double pAll(int n, Person... persons){
        double result = 1;

        for (Person person : persons){
            result *= person.growUp(n).getRate() / person.load().getRate();
        }

        return result;
    }

    public double pExactly(int n, int k, Person... persons) {
        double result = 0;
        Permutare permutare = new Permutare();

        for (int s=0; s<=persons.length - k; s++){
            double val1 = Math.pow(-1, s) * combinari(k + s, s);
            double val2 = permutare.build(k + s, persons).stream().map(v-> pAll(n, v)).reduce(0., (a, b) -> a+b);
            result += val1 * val2;
        }

        return result;
    }

    public double pLeast(int n, int k, Person... persons) {
        double result = 0;
        Permutare permutare = new Permutare();

        for (int s=0; s<=persons.length - k; s++){
            double val1 = Math.pow(-1, s) * combinari(k + s-1, s);
            double val2 = permutare.build(k+s, persons).stream().map(v-> pAll(n, v)).reduce(0., (a, b) -> a+b);
            result += val1 * val2;
        }

        return result;
    }



    public double eFormula(int n, double percent, Person... persons){
        Person[] olderPersons = new Person[persons.length];
        for(int i=0;i<persons.length;i++){
            olderPersons[i] = persons[i].growUp(n);
        }


        return dFormula(percent, olderPersons) / dFormula(percent, persons);
    }

    public double eExactFormula(int n, int k, double percent, Person... persons){
        double result = 0;
        Permutare permutare = new Permutare();

        for (int s=0; s<=persons.length - k; s++){
            double val1 = Math.pow(-1, s) * combinari(k + s, s);
            double val2 = permutare.build(k + s, persons).stream().map(v-> eFormula(n, percent, v)).reduce(0., (a, b) -> a+b);
            result += val1 * val2;
        }

        return result;
    }

    public double eLeastFormula(int n, int k, double percent, Person... persons){
        double result = 0;
        Permutare permutare = new Permutare();

        for (int s=0; s<=persons.length - k; s++){
            double val1 = Math.pow(-1, s) * combinari(k + s - 1, s);
            double val2 = permutare.build(k + s, persons).stream().map(v-> eFormula(n, percent, v)).reduce(0., (a, b) -> a+b);
            result += val1 * val2;
        }

        return result;
    }


    public double aFormula(double percent, Person... persons) {
        Person[] personGrowUp = new Person[persons.length];
        for(int j=0;j< persons.length; j++){
            personGrowUp[j] = persons[j].growUp(1);
        }

        return nFormula(percent, personGrowUp) / dFormula(percent, persons);
    }

    public double aExactFormula(int k, double percent, Person... persons) {
        double result = 0;
        Permutare permutare = new Permutare();

        for (int s=0; s<=persons.length - k; s++){
            double val1 = Math.pow(-1, s) * combinari(k + s, s);
            double val2 = permutare.build(k + s, persons).stream().map(v-> aFormula(percent, v)).reduce(0., (a, b) -> a+b);
            result += val1 * val2;
        }

        return result;
    }

    public double aLeastFormula(int k, double percent, Person... persons) {
        double result = 0;
        Permutare permutare = new Permutare();

        for (int s=0; s<=persons.length - k; s++){
            double val1 = Math.pow(-1, s) * combinari(k + s - 1, s);
            double val2 = permutare.build(k + s, persons).stream().map(v-> aFormula(percent, v)).reduce(0., (a, b) -> a+b);
            result += val1 * val2;
        }

        return result;
    }

    public double aDeferredFormula(int n, double percent, Person... persons){
        Person[] personGrowUp = new Person[persons.length];
        for(int j=0;j< persons.length; j++){
            personGrowUp[j] = persons[j].growUp(n + 1);
        }

        return nFormula(percent, personGrowUp) / dFormula(percent, persons);
    }

    public double aDeferredExactFormula(int n, int k, double percent, Person... persons){
        double result = 0;
        Permutare permutare = new Permutare();

        for (int s=0; s<=persons.length - k; s++){
            double val1 = Math.pow(-1, s) * combinari(k + s, s);
            double val2 = permutare.build(k + s, persons).stream().map(v-> aDeferredFormula(n, percent, v)).reduce(0., (a, b) -> a+b);
            result += val1 * val2;
        }

        return result;
    }

    public double aDeferredLeastFormula(int n, int k, double percent, Person... persons){
        double result = 0;
        Permutare permutare = new Permutare();

        for (int s=0; s<=persons.length - k; s++){
            double val1 = Math.pow(-1, s) * combinari(k + s - 1, s);
            double val2 = permutare.build(k + s, persons).stream().map(v-> aDeferredFormula(n, percent, v)).reduce(0., (a, b) -> a+b);
            result += val1 * val2;
        }

        return result;
    }

    public double aTempFormula(int n, double percent, Person... persons){
        Person[] personGrowUp1 = new Person[persons.length];
        for(int j=0;j< persons.length; j++){
            personGrowUp1[j] = persons[j].growUp(1);
        }

        Person[] personGrowUp2 = new Person[persons.length];
        for(int j=0;j< persons.length; j++){
            personGrowUp2[j] = persons[j].growUp(n + 1);
        }

        return (nFormula(percent, personGrowUp1) - nFormula(percent, personGrowUp2)) / dFormula(percent, persons);
    }

    public double aTempExactFormula(int n, int k, double percent, Person... persons){
        double result = 0;
        Permutare permutare = new Permutare();

        for (int s=0; s<=persons.length - k; s++){
            double val1 = Math.pow(-1, s) * combinari(k + s, s);
            double val2 = permutare.build(k + s, persons).stream().map(v-> aTempFormula(n, percent, v)).reduce(0., (a, b) -> a+b);
            result += val1 * val2;
        }

        return result;
    }

    public double aTempLeastFormula(int n, int k, double percent, Person... persons){
        double result = 0;
        Permutare permutare = new Permutare();

        for (int s=0; s<=persons.length - k; s++){
            double val1 = Math.pow(-1, s) * combinari(k + s - 1, s);
            double val2 = permutare.build(k + s, persons).stream().map(v-> aTempFormula(n, percent, v)).reduce(0., (a, b) -> a+b);
            result += val1 * val2;
        }

        return result;
    }

    public double aFirstFormula(double percent, Person... persons){
        return mFormula(percent, persons)/dFormula(percent, persons);
    }

    public double akFormula(int k, double percent, Person... persons){
        double result = 0;
        Permutare permutare = new Permutare();

        for (int s=0; s<=k-1; s++){
            double val1 = Math.pow(-1, s) * combinari(persons.length -k + s, s);
            double val2 = permutare.build(persons.length -k + s + 1, persons).stream().map(v-> aFirstFormula(percent, v)).reduce(0., (a, b) -> a+b);
            result += val1 * val2;
        }

        return result;
    }



    public double mFormula(double percent, Person... persons){
        double result = 0;

        Person oldestPerson = Stream.of(persons).max(Comparator.comparingInt(Person::getAge)).orElse(null);
        for(int i=0; i <= 100-oldestPerson.getAge();i++){
            Person[] personGrowUp = new Person[persons.length];
            for(int j=0;j< persons.length; j++){
                personGrowUp[j] = persons[j].growUp(i);
            }
            result += cFormula(percent, personGrowUp);
        }

        return result;
    }



    public double nFormula(double percent, Person... persons) {
        double result = 0;

        Person oldestPerson = Stream.of(persons).max(Comparator.comparingInt(Person::getAge)).orElse(null);
        for(int i=0; i <= 100-oldestPerson.getAge();i++){
            Person[] personGrowUp = new Person[persons.length];
            for(int j=0;j< persons.length; j++){
                personGrowUp[j] = persons[j].growUp(i);
            }
            result += dFormula(percent, personGrowUp);
        }

        return result;
    }


    public double dFormula(double percent, Person... persons){
        double result = 1;
        double sum = 0;

        for(Person person: persons){
            result *= person.load().getRate();
            sum += person.getAge();
        }

        result *= Math.pow(vFormula(percent), sum / persons.length);

        return result;
    }


    public double cFormula(double percent, Person... persons){
        double a = 1;
        double b = 1;
        double sum = 0;

        for(Person person: persons){
            a *= person.load().getRate();
            b *= person.growUp(1).getRate();
            sum += person.getAge();
        }

        return (a-b) * Math.pow(vFormula(percent), sum / persons.length + 1./2);
    }


    public double vFormula(double percent){
        return 1/(1+percent);
    }

    public double combinari(int n, int k){

        double a = 1;
        double b = 1;
        for(int i=k+1;i<=n;i++){
            a *= i;
        }
        for(int i=1;i<=n-k;i++){
            b *= i;
        }

        return a / b;
    }
}
