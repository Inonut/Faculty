package old.domain;

import repository.Repository;
import repository.impl.ExcelRepository;

/**
 * Created by dragos on 6/3/17.
 */
public class Female extends Person {

    private Repository repository = new ExcelRepository();

    public Female() {
    }

    public Female(int age) {
        super(age);
    }

    public Female(int age, double rate) {
        super(age, rate);
    }

    @Override
    public Person load() {
        return repository.loadPerson(this);
    }

    @Override
    public Person growUp(int n) {
        return new Female(this.age + n).load();
    }
}
