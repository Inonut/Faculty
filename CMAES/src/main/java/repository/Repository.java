package repository;

import domain.Female;
import domain.Male;
import domain.Person;

/**
 * Created by dragos on 6/4/17.
 */
public interface Repository {

    Male getMaleByAge(int age);

    Female getFemaleByAge(int age);

    Male loadPerson(Male person);

    Female loadPerson(Female person);
}
