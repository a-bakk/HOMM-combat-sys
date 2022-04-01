package Player;

import GameManager.*;

/**
 * A játékost reprezentáló osztály, mely biztosít a játékosnak egységeket, varázslatokat, hőst.
 */

public class Jatekos {

    /**
     * Jelenleg mennyi arannyal rendelkezik a játékos.
     */
    /* package private */ int arany;
    /**
     * Jelenleg mennyi mannával rendelkezik a játékos.
     */
    /* package private */ int manna;
    /**
     * A tulajdonságok folyamatosan növő ára, alapértéke 5.
     */
    /* package private */ int tulajdonsagAr;
    /**
     * Számítógép-e az adott játékos, vagy valódi.
     */
    private boolean szamitoGep = false;

    /**
     * A játékoshoz tartozó hős.
     */
    public Hos jatekosHose = new Hos();
    /**
     * A játékos varázslatai, öttel rendelkezhet, viszont nem biztos, hogy ez így is lesz, viszont azt megoldja a varázslatok osztály és alosztályai.
     */
    public Varazslat[] varazslatok = new Varazslat[5];
    /**
     * A játékos egységei, ugyanolyan módon, mint a varázslatok.
     */
    public Egyseg[] egysegek = new Egyseg[5];

    public Jatekos(boolean szGep) {

        if (szGep) {
            this.szamitoGep = true;
        }

        if(!szGep) {
            switch (GameManager.getNehezseg()) {
                case "konnyu" -> this.arany = 1300;
                case "kozepes" -> this.arany = 1000;
                case "nehez" -> this.arany = 700;
            }
            this.szamitoGep = false;
        }

        this.manna = 10;
        this.tulajdonsagAr = 5;

        varazslatok[0] = new Tuzlabda();
        varazslatok[1] = new Villamcsapas();
        varazslatok[2] = new Feltamasztas();
        varazslatok[3] = new Armageddon();
        varazslatok[4] = new Varazsszarnyak();

        egysegek[0] = new Foldmuves();
        egysegek[1] = new Ijasz();
        egysegek[2] = new Griff();
        egysegek[3] = new Hobgoblin();
        egysegek[4] = new Demon();

    }

    public int getArany() { return arany; }

    public int getManna() { return manna; }

    public boolean isSzamitoGep() { return szamitoGep; }

    public void setArany(int arany) { this.arany = arany; }

    public void setManna(int manna) { this.manna = manna; }

}
