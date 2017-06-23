package old.domain;

/**
 * Created by dragos on 6/3/17.
 */
public abstract class Person {
    protected int age;
    protected double rate;

    public Person() {
    }

    public Person(int age) {
        this.age = age;
    }

    public Person(int age, double rate) {
        this.age = age;
        this.rate = rate;
    }

    public abstract Person load();

    public abstract Person growUp(int n);

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
