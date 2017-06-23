package repository.impl;

import old.domain.Female;
import old.domain.Male;
import repository.Repository;
import old.util.Util;

import java.util.Collections;
import java.util.List;

/**
 * Created by dragos on 6/4/17.
 */
public class ExcelRepository implements Repository {

    public static final List<Male> MALE = Util.readMalePersons("data.xlsx", 0, 1);
    public static final List<Female> FEMALE = Util.readFemalePersons("data.xlsx", 0, 2);

    static {
        Collections.reverse(MALE);
        Collections.reverse(FEMALE);
        for (int i = 0; i < 16; i++) {
            MALE.add(new Male());
            FEMALE.add(new Female());
        }
        Collections.reverse(MALE);
        Collections.reverse(FEMALE);
        MALE.add(new Male());
        FEMALE.add(new Female());
    }

    @Override
    public Male getMaleByAge(int age) {
        return MALE.get(age);
    }

    @Override
    public Female getFemaleByAge(int age) {
        return FEMALE.get(age);
    }

    @Override
    public Male loadPerson(Male person) {
        return MALE.get(person.getAge());
    }

    @Override
    public Female loadPerson(Female person) {
        return FEMALE.get(person.getAge());
    }
}
