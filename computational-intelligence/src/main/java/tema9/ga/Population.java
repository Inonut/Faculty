package tema9.ga;

/**
 * Created by dragos on 5/21/17.
 */
public interface Population {

    void generate();

    Individual getFittest();

    Population evolve();

    void mutate();
}
