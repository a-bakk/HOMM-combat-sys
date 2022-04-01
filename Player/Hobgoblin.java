package player;

/**
 * A hobgoblint, egy speciális egységet megvalósító osztály egyetlen konstruktorral, amely megfelelően inicializálja az adattagokat.
 */

public class Hobgoblin extends Egyseg {

    public Hobgoblin() {
        this.ar = 3;
        this.minSebzes = 1;
        this.maxSebzes = 2;
        this.eletero = 1;
        this.sebesseg = 5;
        this.kezdemenyezes = 10;
        this.specKepesseg = "mergezes";
        this.hanyVan = 0;
        this.elhelyezett = false;
    }

}
