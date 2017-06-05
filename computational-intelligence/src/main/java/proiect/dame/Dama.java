package proiect.dame;

import proiect.ad.Gene;

import java.util.Random;

/**
 * Created by dragos on 6/4/17.
 */
public class Dama extends Gene {

    private int pos;

    public Dama() {
    }

    public Dama(int pos) {
        this.pos = pos;
    }

    @Override
    public double compareTo(Gene gene) {
        Dama dama = (Dama) gene;
        return this.pos - dama.pos;
    }

    @Override
    public Gene randomize() {
        Random random = new Random();
        return new Dama(random.nextInt(10));
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "" + (this.pos + 1);
    }
}
