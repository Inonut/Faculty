package old.domain;

import repository.Repository;
import repository.impl.ExcelRepository;

/**
 * Created by dragos on 6/3/17.
 */
public class Male extends Person {

    private Repository repository = new ExcelRepository();

    public Male() {
    }

    public Male(int age) {
        super(age);
    }

    public Male(int age, double rate) {
        super(age, rate);
    }

    @Override
    public Person load() {
        return repository.loadPerson(this);
    }

    @Override
    public Person growUp(int n) {
        return new Male(this.age + n).load();
    }
}
