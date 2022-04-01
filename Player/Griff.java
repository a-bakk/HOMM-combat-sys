package player;

/**
 * A griffet, egy speciális egységet megvalósító osztály egyetlen konstruktorral, amely megfelelően inicializálja az adattagokat.
 */

public class Griff extends Egyseg {

    public Griff() {
        this.ar = 15;
        this.minSebzes = 5;
        this.maxSebzes = 10;
        this.eletero = 30;
        this.sebesseg = 7;
        this.kezdemenyezes = 15;
        this.specKepesseg = "vissza";
        this.hanyVan = 0;
        this.elhelyezett = false;
    }

}
