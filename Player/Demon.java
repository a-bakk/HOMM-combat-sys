package Player;

/**
 * A Démon egységet megvalósító osztály, egyetlen metódusa a konsruktor, mely a követelményeknek megfelelő módon feltölti az adattagokat.
 */

public class Demon extends Egyseg {

    public Demon() {
        this.ar = 20;
        this.minSebzes = 15;
        this.maxSebzes = 25;
        this.eletero = 10;
        this.sebesseg = 3;
        this.kezdemenyezes = 12;
        this.specKepesseg = "vegtelen visszatamadas";
        this.hanyVan = 0;
        this.elhelyezett = false;
    }

}
