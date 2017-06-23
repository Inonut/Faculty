package repository;

import old.domain.Female;
import old.domain.Male;

/**
 * Created by dragos on 6/4/17.
 */
public interface Repository {

    Male getMaleByAge(int age);

    Female getFemaleByAge(int age);

    Male loadPerson(Male person);

    Female loadPerson(Female person);
}
