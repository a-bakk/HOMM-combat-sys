package JatekPalya;

import Player.*;

public class Mezo {

    private boolean foglalt;
    private String tartalom;
    private Egyseg tartalomEgyseg;
    private Jatekos kiBirtokolja;

    public Mezo() {
        this.foglalt = false;
        this.tartalom = " --------- ";
        this.tartalomEgyseg = null;
    }

    public void setFoglalt(boolean foglalt) {
        this.foglalt = foglalt;
    }

    public void setTartalom(String tartalom) { this.tartalom = tartalom; }

    public boolean isFoglalt() { return this. foglalt; }

    public String getTartalom() { return this.tartalom; }

    public void setMezo(String milyenEgyseg, Jatekos kinek, Egyseg melyikEgyseg) {
        this.kiBirtokolja = kinek;
        this.tartalom = Egyseg.toString(kinek, milyenEgyseg);
        this.tartalomEgyseg = melyikEgyseg;
        this.foglalt = true;
    }

    public void resetMezo() {
        this.foglalt = false;
        this.tartalom = " ------- ";
    }
}