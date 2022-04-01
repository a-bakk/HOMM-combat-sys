package jatekpalya;

import player.*;

/**
 * Az osztály tárol mindent, ami a különböző mezőkhöz kapcsolódik, amelyekből felépül a pálya.
 */
public class Mezo {

    /**
     * Foglalt-e éppen a mező.
     */
    private boolean foglalt;
    /**
     * A tartalom szöveges formában.
     */
    private String tartalom;
    /**
     * Az egység, amelyet a mező éppen tárol.
     */
    private Egyseg tartalomEgyseg;
    /**
     * Melyik játékoshoz köthető a mező.
     */
    private Jatekos kiBirtokolja;
    /**
     * Az egység, amelyet tartalmaz szöveges formában.
     */
    private String milyenEgyseg;
    /**
     * A megjelenítendő üres tartalom.
     */
    private final String defaultTartalom;

    public Mezo() {
        this.foglalt = false;
        this.tartalom = " --------- ";
        this.tartalomEgyseg = null;
        this.milyenEgyseg = null;
        this.kiBirtokolja = null;
        this.defaultTartalom = " --------- ";
    }

    public boolean isFoglalt() { return this.foglalt; }

    public String getTartalom() { return this.tartalom; }

    public Jatekos getKiBirtokolja() {
        return kiBirtokolja;
    }

    public Egyseg getTartalomEgyseg() {
        return tartalomEgyseg;
    }

    public String getMilyenEgyseg() {
        return milyenEgyseg;
    }

    public String getDefaultTartalom() {
        return defaultTartalom;
    }


    public void setMezo(String milyenEgyseg, Jatekos kinek, Egyseg melyikEgyseg) {
        this.kiBirtokolja = kinek;
        this.tartalom = Egyseg.toString(kinek, milyenEgyseg);
        this.tartalomEgyseg = melyikEgyseg;
        this.foglalt = true;
        this.milyenEgyseg = milyenEgyseg;
    }


    public void resetMezo() {
        this.kiBirtokolja = null;
        this.tartalomEgyseg = null;
        this.foglalt = false;
        this.tartalom = " --------- ";
        this.milyenEgyseg = null;
    }

}
